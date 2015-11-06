package com.polsri.union.app.util.response;

import java.util.List;

public class GenericMultipleResponse<T> extends GenericResponse {

	List<T> contents;
	private PageInformation pageInformation;

	public GenericMultipleResponse(ErrorCategory errorCategory, List<String> errorMessages, boolean success,
			List<T> contents, PageInformation pageInformation) {
		super(errorCategory, errorMessages, success);
		this.contents = contents;
		this.pageInformation = pageInformation;
	}

	public List<T> getContents() {
		return contents;
	}

	public void setContents(List<T> contents) {
		this.contents = contents;
	}

	public PageInformation getPageInformation() {
		return pageInformation;
	}

	public void setPageInformation(PageInformation pageInformation) {
		this.pageInformation = pageInformation;
	}

	@Override
	public String toString() {
		return "GenericMultipleResponse [pageInformation=" + pageInformation + ", contents=" + contents
				+ ", getErrorCategory()=" + getErrorCategory() + ", getErrorMessages()=" + getErrorMessages()
				+ ", isSuccess()=" + isSuccess() + "]";
	}

}
