package com.boc.crm.base.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 分页返回结果
 * @author Neo
 *
 * @param <T>
 */
public class PageResult<T> implements Serializable {

	private static final long serialVersionUID = 7205828205443453181L;
	
	//------------------------ 基础属性 ------------------------------
	/**数据*/
	private List<T> data = Collections.emptyList();
	
	/**总记数*/
	private long totalCount = 0L;
	
	/**当前页码*/
	private int pageIndex = 1;
	
	/**每页大小*/
	private int pageSize = PageQuery.DEFAULT_PAGE_SIZE;
	
	//------------------------ 动态计算的属性 ------------------------------
	/**总页码*/
	private long totalPage = 0;
	
	/**
	 * PageResult构造函数
	 * @param data 数据
	 * @param totalCount 总记录数
	 * @param pageIndex 当前页码
	 * @param pageSize 每页大小
	 */
	public PageResult(List<T> data, long totalCount, int pageIndex, int pageSize) {
		super();
		this.data = data;
		this.totalCount = totalCount;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalPage = calcTotalPage(this.totalCount, this.pageSize);
	}
	
	public PageResult(List<T> data,PageQuery pageQuery){
		this(data, pageQuery.getTotalCount(), pageQuery.getPageIndex(), pageQuery.getPageSize());
	}
	
	/**
	 * PageResult构造函数
	 * @param pageIndexStr 当前页码
	 * @param pageSizeStr 每页大小
	 */
	public PageResult(String pageIndexStr, String pageSizeStr) {
		super();
		int pageIndex =1;
		int pageSize = PageQuery.DEFAULT_PAGE_SIZE;
		try{
			pageIndex = Integer.parseInt(pageSizeStr);
		}catch(Exception e){
		}
		try{
			pageSize = Integer.parseInt(pageIndexStr);
		}catch(Exception e){
		}
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalPage = calcTotalPage(this.totalCount, this.pageSize);
	}
	
	/**
	 * PageResult构造函数
	 * @param pageIndex 当前页码
	 * @param pageSize 每页大小
	 */
	public PageResult(int pageIndex, int pageSize) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalPage = calcTotalPage(this.totalCount, this.pageSize);
	}
	
	//-------------------------------
	/**
	 * 计算总页数
	 * @return
	 */
	public static long calcTotalPage(long totalCount, int pageSize) {
		if (totalCount == 0) {
			return 0;
		}
		long mod = totalCount % pageSize;
		if (mod == 0) {
			return totalCount / pageSize;
		}
		return totalCount / pageSize + 1;
	}
	
	//-----------getter setter-------
	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public long getTotalPage() {
		return totalPage;
	}
	
	public static void main(String[] args) {
		
		PageResult<Object> pageResult = new PageResult<>(new ArrayList<Object>(), 53, 2, 10);
		
		System.out.println("getTotalPage: " + pageResult.getTotalPage());
		
		System.out.println("calcTotalPage: " + PageResult.calcTotalPage(53, 10));
	}
	
}
