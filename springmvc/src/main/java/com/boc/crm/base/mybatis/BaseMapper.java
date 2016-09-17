package com.boc.crm.base.mybatis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.boc.crm.base.page.PageQuery;

/**
 * MyBatis Mapper基类
 * @author Neo
 *
 * @param <T> 实体类型
 * @param <C> Criteria类型
 */
public interface BaseMapper<T, C> {

	/**mapper参数，example key*/
	public static final String PARAM_KEY_EXAMPLE = "example";
	/**mapper参数，entity key*/
	public static final String PARAM_KEY_ENTITY = "entity";
	/**mapper参数，实体属性名数组*/
	public static final String PARAM_KEY_PROPS = "props";
	
	/**新增一条记录*/
	int insert(T entity);
	
	/**新增一条记录*/
    int insertSelective(T entity);
    
	/**删除一条记录*/
	int deleteByPrimaryKey(Serializable id);
	
	/**通过Example删除记录*/
	int deleteByExample(@Param(PARAM_KEY_EXAMPLE)Example<C> example);
	
	/**修改一条记录*/
    int updateByPrimaryKey(T entity);
	
	/**修改一条记录*/
    int updateByPrimaryKeySelective(T entity);
    
    /**通过Example修改记录*/
    int updateByExample(@Param(PARAM_KEY_EXAMPLE)Example<C> example,
    		@Param(PARAM_KEY_ENTITY)T entity);
    
    /**通过Example修改记录*/
    int updateByExampleSelective(@Param(PARAM_KEY_EXAMPLE)Example<C> example,
    		@Param(PARAM_KEY_ENTITY)T entity);
    
	/**
	 * 通过主键查询一条记录
	 */
    T getById(@Param("id")Serializable id);
    
    /**
     * 通过example查询单条记录
     * @param example
     * @return
     */
    T getObjectByExample(@Param(PARAM_KEY_EXAMPLE)Example<C> example);
    
    /**
     * 通过example查询记录list
     * @param example
     * @return
     */
    List<T> getListByExample(@Param(PARAM_KEY_EXAMPLE)Example<C> example);
    
    /**
     * 通过example查询记录list（分页）
     * @param example
     * @param pageQuery 分页类
     * @return
     */
    List<T> getListByExample(@Param(PARAM_KEY_EXAMPLE)Example<C> example, PageQuery pageQuery);
    
    /**
     * 通过example查询总记录数
     * @param example
     * @return
     */
    long getCountByExample(@Param(PARAM_KEY_EXAMPLE)Example<C> example);
    
    /**
     * 通过example获取List<Map>
     * @param props 实体属性名数组
     * @param example
     * @return
     */
    List<Map<String, Object>> getMapListByExample(@Param(PARAM_KEY_PROPS)String[] props, 
    		@Param(PARAM_KEY_EXAMPLE)Example<C> example);
    
    /**
     * 通过example获取List<Map>（分页）
     * @param props 实体属性名数组
     * @param example
     * @param pageQuery 分页类
     * @return
     */
    List<Map<String, Object>> getMapListByExample(@Param(PARAM_KEY_PROPS)String[] props, 
    		@Param(PARAM_KEY_EXAMPLE)Example<C> example, PageQuery pageQuery);
    
}
