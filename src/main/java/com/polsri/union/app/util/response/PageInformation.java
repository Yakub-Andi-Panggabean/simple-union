package com.polsri.union.app.util.response;

public class PageInformation {

	private Integer page;
	private Integer pageSize;
	private Integer totalPage;
	private Long totalRecord;

	public PageInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageInformation(Integer page, Integer pageSize, Long totalRecord) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.totalPage = getTotalPage();
		this.totalRecord = totalRecord;
	}

	public Integer getpage() {

		return page;
	}

	public void setpage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		Double totalPage = Math.ceil((double) getTotalRecord() / getPageSize());
		return totalPage.intValue();
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Long totalRecord) {
		this.totalRecord = totalRecord;
	}

	@Override
	public String toString() {
		return "PageInformation [page=" + page + ", pageSize=" + pageSize + ", totalPage=" + totalPage
				+ ", totalRecord=" + totalRecord + "]";
	}

}
