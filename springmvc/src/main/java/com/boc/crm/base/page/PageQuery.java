package com.boc.crm.base.page;


/**
 * 分页查询类
 * @author Neo
 * 
 */
public class PageQuery {

	/**默认的分页大小 */
	public static final int DEFAULT_PAGE_SIZE = 10;
	
	/**分页大小的最大值*/
	public static final int MAX_PAGE_SIZE = 100000;
	
	/**当前页码*/
	private int pageIndex = 1;
	
	/**分页大小*/
	private int pageSize = DEFAULT_PAGE_SIZE;
	/**
	 * 是否查询总记录数，默认为false
	 */
	private Boolean isQueryTotal = false;
	
	/**总页数(供Mybatis分页拦截器使用)*/
    private int totalPage;
    
    /**总记录数(供Mybatis分页拦截器使用)*/
    private int totalCount;

	public PageQuery() {
		
	}
	
	/**
	 * 
	 * @param pageIndexStr
	 * @param pageSizeStr
	 */
	public PageQuery(String pageIndexStr, String pageSizeStr) {
		int pageIndex = 1;
		int pageSize = DEFAULT_PAGE_SIZE;
		try{
			pageIndex = Integer.parseInt(pageIndexStr);
		}catch(Exception e){
		}
		try{
			pageSize = Integer.parseInt(pageSizeStr);
		}catch(Exception e){
		}
		
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		
		if (pageSize < 1) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		if (pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.pageIndex = pageIndex;
		setPageSize(pageSize);
	}
	
	public PageQuery(int pageIndex, int pageSize) {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		if (pageSize < 1) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		if (pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.pageIndex = pageIndex;
		setPageSize(pageSize);
	}
	
	/**
	 * 获取起始行
	 * @return
	 */
	public int getOffset() {
		return (pageIndex - 1) * pageSize;
	}
	
	/**
	 * 获取每页大小
	 * @return
	 */
	public int getLimit() {
		return pageSize;
	}
	
	/**
	 * 把offset转换为pageIndex
	 * @param offset offset
	 * @param pageSize 每页显示的记录数
	 * @return pageIndex
	 */
	public static int convertOffsetToPageIndex(int offset, int pageSize) {
		return offset / pageSize + 1;
	}
	
	//-------------------------------------------------
	
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		if (pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}
	
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Boolean getIsQueryTotal() {
		return isQueryTotal;
	}

	public void setIsQueryTotal(Boolean isQueryTotal) {
		this.isQueryTotal = isQueryTotal;
	}

	public static void main(String[] args) {
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageIndex(3);
		pageQuery.setPageSize(25);
		System.out.println("getPageIndex:" + pageQuery.getPageIndex());
		System.out.println("getPageSize:" + pageQuery.getPageSize());
		System.out.println("getFirstResult:" + pageQuery.getOffset());
		System.out.println("getMaxResult:" + pageQuery.getLimit());
		
		System.out.println(String.format("convertOffsetToPageIndex(%s, %s) : %s", 
				0, 10, convertOffsetToPageIndex(0, 10)));
		System.out.println(String.format("convertOffsetToPageIndex(%s, %s) : %s", 
				1, 10, convertOffsetToPageIndex(1, 10)));
		System.out.println(String.format("convertOffsetToPageIndex(%s, %s) : %s", 
				9, 10, convertOffsetToPageIndex(9, 10)));
		System.out.println(String.format("convertOffsetToPageIndex(%s, %s) : %s", 
				10, 10, convertOffsetToPageIndex(10, 10)));
		System.out.println(String.format("convertOffsetToPageIndex(%s, %s) : %s", 
				11, 10, convertOffsetToPageIndex(11, 10)));
		System.out.println(String.format("convertOffsetToPageIndex(%s, %s) : %s", 
				19, 10, convertOffsetToPageIndex(19, 10)));
		System.out.println(String.format("convertOffsetToPageIndex(%s, %s) : %s", 
				20, 10, convertOffsetToPageIndex(20, 10)));
	}
	
}
