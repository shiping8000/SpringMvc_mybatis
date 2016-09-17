package com.boc.crm.base.service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.boc.crm.base.mybatis.BaseCriteria;
import com.boc.crm.base.mybatis.BaseMapper;
import com.boc.crm.base.mybatis.Example;
import com.boc.crm.base.mybatis.ExampleFactory;
import com.boc.crm.base.page.PageQuery;
import com.boc.crm.base.page.PageResult;
import com.boc.crm.base.util.ReflectionUtil;

/**
 * Base Service实现类
 * @author Neo
 *
 * @param <T> 实体类型
 * @param <C> Criteria类型
 */
public abstract class BaseServiceImpl<T, C> implements BaseService<T, C> {

	protected final Logger logger = LogManager.getLogger(getClass());
	
	/**
	 * 获取主类的Mapper
	 * @return
	 */
	public abstract BaseMapper<T, C> getBaseMapper();
	
	/**
	 * 创建Example对象
	 * @param criteriaClz Criteria Class
	 * @return
	 */
	protected <Criteria extends BaseCriteria> Example<Criteria> createExample(Class<Criteria> criteriaClz) {
		return ExampleFactory.createExample(criteriaClz);
	}
	
	/**
	 * 创建主类的Example
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Example<C> createBaseExample() {
		return ExampleFactory.createExample(ReflectionUtil.getSuperGenericClass(getClass(), 1));
	}
	
	/**新增一条记录*/
	@Transactional(rollbackFor = Exception.class)
	public int insert(T entity) {
		return getBaseMapper().insert(entity);
	}
	
	/**新增一条记录*/
	@Transactional(rollbackFor = Exception.class)
    public int insertSelective(T entity) {
    	return getBaseMapper().insertSelective(entity);
    }
	
	/**删除一条记录*/
	@Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Serializable id) {
		return getBaseMapper().deleteByPrimaryKey(id);
	}
	
	/**通过Example删除记录*/
	@Transactional(rollbackFor = Exception.class)
    public int deleteByExample(Example<C> example) {
    	return getBaseMapper().deleteByExample(example);
    }
	
	/**修改一条记录*/
	@Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(T entity) {
    	return getBaseMapper().updateByPrimaryKey(entity);
    }
	
	/**修改一条记录*/
	@Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(T entity) {
		return getBaseMapper().updateByPrimaryKeySelective(entity);
	}
	
	/**通过Example修改记录*/
	@Transactional(rollbackFor = Exception.class)
    public int updateByExample(Example<C> example, T entity) {
    	return getBaseMapper().updateByExample(example, entity);
    }
    
    /**通过Example修改记录*/
	@Transactional(rollbackFor = Exception.class)
    public int updateByExampleSelective(Example<C> example, T entity) {
		return getBaseMapper().updateByExampleSelective(example, entity);
	}
	
	/**
	 * 通过主键查询一条记录
	 */
	public T getById(Serializable id) {
		return getBaseMapper().getById(id);
	}
	
	/**
     * 通过example查询单条记录
     * @param example
     * @return
     */
    public T getObjectByExample(Example<C> example) {
    	return getBaseMapper().getObjectByExample(example);
    }
	
	/**
     * 通过example查询记录list
     * @param example
     * @return
     */
    public List<T> getListByExample(Example<C> example) {
    	return getBaseMapper().getListByExample(example);
    }
    
    /**
     * 通过example查询记录list（分页）
     * @param example
     * @param pageQuery
     * @return
     */
    public PageResult<T> getListByExample(Example<C> example, PageQuery pageQuery) {
    	List<T> list = getBaseMapper().getListByExample(example, pageQuery);
    	if (list == null) {
			list = Collections.emptyList();
		}
    	
    	PageResult<T> pageResult = new PageResult<>(
    			list, pageQuery.getTotalCount(), pageQuery.getPageIndex(), pageQuery.getPageSize());
    	return pageResult;
    }
    
    /**
     * 通过example查询总记录数
     * @param example
     * @return
     */
    public long getCountByExample(Example<C> example) {
    	return getBaseMapper().getCountByExample(example);
    }
    
    /**
     * 通过example获取List<Map>
     * @param props 实体属性名数组
     * @param example
     * @return
     */
    public List<Map<String, Object>> getMapListByExample(String[] props, Example<C> example) {
    	return getBaseMapper().getMapListByExample(props, example);
    }
    
    /**
     * 通过example获取List<Map>（分页）
     * @param props 实体属性名数组
     * @param example
     * @param pageQuery 分页类
     * @return
     */
    public PageResult<Map<String, Object>> getMapListByExample(String[] props, Example<C> example, PageQuery pageQuery) {
    	List<Map<String, Object>> list = getBaseMapper().getMapListByExample(props, example, pageQuery);
    	if (list == null) {
			list = Collections.emptyList();
		}
    	
    	PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>(
    			list, pageQuery.getTotalCount(), pageQuery.getPageIndex(), pageQuery.getPageSize());
    	return pageResult;
    }
	
}
