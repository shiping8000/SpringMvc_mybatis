package com.boc.crm.base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.boc.crm.base.mybatis.Example;
import com.boc.crm.base.page.PageQuery;
import com.boc.crm.base.page.PageResult;


/**
 * Base Service
 * @author Neo
 *
 * @param <T> 实体类型
 * @param <C> Criteria类型
 *
 */
public interface BaseService<T, C> {

	/**新增一条记录*/
	public int insert(T entity);
	
	/**新增一条记录*/
    public int insertSelective(T entity);
    
    /**删除一条记录*/
    public int deleteByPrimaryKey(Serializable id);
    
    /**通过Example删除记录*/
    public int deleteByExample(Example<C> example);
    
    /**修改一条记录*/
    public int updateByPrimaryKey(T entity);
    
    /**修改一条记录*/
    public int updateByPrimaryKeySelective(T entity);
    
    /**通过Example修改记录*/
    public int updateByExample(Example<C> example, T entity);
    
    /**通过Example修改记录*/
    public int updateByExampleSelective(Example<C> example, T entity);
	
	/**
	 * 通过主键查询一条记录
	 */
	public T getById(Serializable id);
	
	/**
     * 通过example查询单条记录
     * @param example
     * @return
     */
    public T getObjectByExample(Example<C> example);
	
	/**
     * 通过example查询记录list
     * @param example
     * @return
     */
	public List<T> getListByExample(Example<C> example);
	
	/**
     * 通过example查询记录list（分页）
     * @param example
     * @param pageQuery
     * @return
     */
    public PageResult<T> getListByExample(Example<C> example, PageQuery pageQuery);
    
    /**
     * 通过example查询总记录数
     * @param example
     * @return
     */
    public long getCountByExample(Example<C> example);
    
    /**
     * 通过example获取List<Map>
     * @param props 实体属性名数组
     * @param example
     * @return
     */
    public List<Map<String, Object>> getMapListByExample(String[] props, Example<C> example);
    
    /**
     * 通过example获取List<Map>（分页）
     * @param props 实体属性名数组
     * @param example
     * @param pageQuery 分页类
     * @return
     */
    public PageResult<Map<String, Object>> getMapListByExample(String[] props, Example<C> example, PageQuery pageQuery);
	
}
