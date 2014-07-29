package com.bb2004.util;

import java.util.List;

public class PaginationSupport {
    
	
	public final static int PAGESIZE = 12;

	private int pageSize = PAGESIZE;
    
	private List<?> items;
	
	private int page;
	
	private int totalPage;
	
	private int totalCount;
	
	private int[] indexes = new int[0];

	private int startIndex = 0;

	public PaginationSupport(int totalCount) {
		setPageSize(PAGESIZE);
		setTotalCount(totalCount);
		setPage(0);
	}

	public PaginationSupport(int totalCount, int page) {
		setPageSize(PAGESIZE);
		setTotalCount(totalCount);
		setPage(page);
	}

	public PaginationSupport(int totalCount, int pageSize,int page) {
		setPageSize(pageSize);
		setTotalCount(totalCount);
		setPage(page);
	}

	public List<?> getItems() {
		return items;
	}
	
	public void setItems(List<?> items) {
		this.items = items;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
			int count = totalCount / pageSize;
			if (totalCount % pageSize > 0)
				count++;
			setTotalPage(count);
			indexes = new int[count];
			for (int i = 0; i < count; i++) {
				indexes[i] = pageSize * i;
			}
			
		} else {
			this.totalCount = 0;
			setTotalPage(0);
		}
	}

	public int[] getIndexes() {
		return indexes;
	}

	public void setIndexes(int[] indexes) {
		this.indexes = indexes;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		if (totalCount <= 0)
			this.startIndex = 0;
		else if (startIndex >= totalCount)
			this.startIndex = indexes[indexes.length - 1];
		else if (startIndex < 0)
			this.startIndex = 0;
		else {
			this.startIndex = indexes[startIndex / pageSize];
		}
	}
    
	/**
	 * 
	 * @return
	 */
	public int getNextIndex() {
		int nextIndex = getStartIndex() + pageSize;
		if (nextIndex >= totalCount)
			return getStartIndex();
		else
			return nextIndex;
	}
   
	/**
	 * 
	 * @return
	 */
	public int getPreviousIndex() {
		int previousIndex = getStartIndex() - pageSize;
		if (previousIndex < 0)
			return 0;
		else
			return previousIndex;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page<=0) 
		 {
		   this.page=1;
		   setStartIndex(0);
		 }
	    else if(page>this.totalPage)
		 {
		   this.page=this.totalPage;
		   setStartIndex((this.page-1)*this.pageSize);
		 }
	    else
	    {
	      this.page = page;
	      setStartIndex((this.page-1)*this.pageSize);
	    }
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
