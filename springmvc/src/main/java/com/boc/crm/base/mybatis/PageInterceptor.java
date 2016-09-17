package com.boc.crm.base.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.boc.crm.base.page.PageQuery;

/**
 * MyBatis分页拦截器
 * @author Neo
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PageInterceptor implements Interceptor {

    private static final Logger logger = LogManager.getLogger(PageInterceptor.class);

    /** 默认的对象工厂 */
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();

    /** 默认对象包装器工厂 */
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

    /**默认的反射工厂*/
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
    
    /** 默认DB方言 */
    private static final String DEFAULT_DIALECT = "mysql";

    /** DB方言 */
    private static String dialect = DEFAULT_DIALECT;
    
    public static final Pattern FROM_PATTERN = Pattern.compile("from");

    @SuppressWarnings("unchecked")
	@Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler,
                DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
        while (metaStatementHandler.hasGetter("h")) {
            Object object = metaStatementHandler.getValue("h");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY,
                    DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
        }
        // 分离最后一个代理对象的目标类
        while (metaStatementHandler.hasGetter("target")) {
            Object object = metaStatementHandler.getValue("target");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY,
                    DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
        }

        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler
                .getValue("delegate.mappedStatement");

        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
        Object parameterObject = boundSql.getParameterObject();
        if (parameterObject == null) {
            return invocation.proceed();
        } else {
            PageQuery page = null;

            Map<String, Object> paramMap = null;
            try {
            	paramMap = (Map<String, Object>) parameterObject;
			} catch (ClassCastException e) {
				boolean isDebug = false;
				if (isDebug && logger.isDebugEnabled()) {
					logger.debug(String.format("%s could not be cast to java.util.Map", parameterObject.getClass().getName()));
				}
			}
            if (paramMap != null) {
            	//查找分页参数
                for (Entry<String, Object> paramMapEntry : paramMap.entrySet()) {
    				Object param = paramMapEntry.getValue();
    				if (param == null) {
    					continue;
    				}
    				
    				if (param instanceof PageQuery) {
    					page = (PageQuery) param;
    				}
    			}
			}
            
            if (page == null) { // 如果不带有pageParameter分页参数，则直接跳出
                return invocation.proceed();
            }

            String sql = boundSql.getSql();
            // 重写sql
            String pageSql = buildPageSql(sql, page);
            metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
            // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
            metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
            metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
            Connection connection = (Connection) invocation.getArgs()[0];
            // 重设分页参数里的总页数等
            fillPageParameter(sql, connection, mappedStatement, boundSql, page);
        }
        // 将执行权交给下一个拦截器
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
    }

    /**
     * 从数据库里查询总的记录数并计算总页数，回写进分页参数<code>PageParameter</code>,这样调用者就可用通过 分页参数
     * @param sql
     * @param connection
     * @param mappedStatement
     * @param boundSql
     * @param page
     */
    private void fillPageParameter(String sql, Connection connection,
            MappedStatement mappedStatement, BoundSql boundSql, PageQuery page) {
    	if (!page.getIsQueryTotal()) {
    		logger.info("do not query total count");
			return;
		}
        // 记录总记录数
        String countSql = "SELECT COUNT(1) " + removeSelect(removeOrderBy(sql));
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            countStmt = connection.prepareStatement(countSql);
            BoundSql countBS = copyFromBoundSql(mappedStatement, boundSql, countSql);
            setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
            rs = countStmt.executeQuery();
            int totalCount = 0;
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
            page.setTotalCount(totalCount);
            // 计算总页数
            int totalPage = totalCount / page.getPageSize()
                    + ((totalCount % page.getPageSize() == 0) ? 0 : 1);
            page.setTotalPage(totalPage);
            logger.info("==> Total Count : {}", totalCount);
        } catch (SQLException e) {
            logger.error("Ignore this exception", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Ignore this exception", e);
                }
            }
            if (countStmt != null) {
                try {
                    countStmt.close();
                } catch (SQLException e) {
                    logger.error("Ignore this exception", e);
                }
            }
        }

    }

    /**
     * 复制BoundSql对象
     * @param ms
     * @param boundSql
     * @param sql
     * @return
     */
    private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql, String sql) {
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql,
                boundSql.getParameterMappings(), boundSql.getParameterObject());
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        return newBoundSql;
    }

    /**
     * 设置sql参数值
     * @param ps
     * @param mappedStatement
     * @param boundSql
     * @param parameterObject
     * @throws SQLException
     */
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement,
            BoundSql boundSql, Object parameterObject) throws SQLException {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement,
                parameterObject, boundSql);
        parameterHandler.setParameters(ps);
    }

	/**
	 * 根据数据库类型，生成特定的分页sql
	 * @param sql
	 * @param page
	 * @return
	 */
    private String buildPageSql(String sql, PageQuery page) {
        if (page != null) {
            StringBuffer pageSql = null;
            if ("mysql".equals(dialect)) {
                pageSql = buildPageSqlForMysql(sql, page);
                return pageSql.toString();
            } else {
                return sql;
            }
        } else {
            return sql;
        }
    }

    /**
     * 构建MySQL的分页语句
     * @param sql
     * @param page
     * @return
     */
    private StringBuffer buildPageSqlForMysql(String sql, PageQuery page) {
        StringBuffer pageSql = new StringBuffer();
        String beginrow = String.valueOf((page.getPageIndex() - 1) * page.getPageSize());
        pageSql.append(sql);
        pageSql.append(" LIMIT " + beginrow + "," + page.getPageSize());
        return pageSql;
    }

    /**
     * 移除sql中的order by
     * @param sql
     * @return
     */
    private String removeOrderBy(String sql) {
        Pattern pattern = Pattern
                .compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "");
        }
        matcher.appendTail(sb);
        // if (LOGGER.isDebugEnabled()) {
        // LOGGER.debug("after remove orderBy:" + sb.toString());
        // }
        return sb.toString();
    }

    /**
     * 移除SQL语句开始部分的select
     * @param sql
     * @return
     */
    private String removeSelect(String sql) {
//        int begin = sql.toLowerCase().indexOf("from");
    	int begin = -1;
//        (sql.charAt(begin - 1) + "").matches("[a-zA-Z0-9_-]")
        
        
        
        Matcher matcher = FROM_PATTERN.matcher(sql.toLowerCase());
        while (matcher.find()) {
        	int end = matcher.end();
			begin = end - 4;
//			System.out.println( begin + " qian  " + (sql.charAt(begin - 1) + "  qian ") + "  hou  "+(sql.charAt(end + 1) + "  hou  "));
			if (!(sql.charAt(begin - 1) + "").matches("[a-zA-Z0-9_-]") &&
					!(sql.charAt(end) + "").matches("[a-zA-Z0-9_-]")) {
				break;
			}
			
		}
        
        sql = sql.substring(begin);
        // if (LOGGER.isDebugEnabled()) {
        // LOGGER.debug("after remove orderBy:" + sql);
        // }
        return sql;
    }
    
    public static void main(String[] args) {
//    	`from`
		PageInterceptor pageInterceptor = new PageInterceptor();
		String sql = "SELECT t.abc, t.bcd, t.`bd_from`, `bd_from`, `from_xyz`, `xss_from`, t.abc_from, FROM_,FROM- ,t.abd, t.ssddd FROM t_mt_user WHERE id in (select * from abc) 1=1";
		
		//String sql = "SELECT";
		System.out.println(pageInterceptor.removeSelect(sql));;
	}

}
