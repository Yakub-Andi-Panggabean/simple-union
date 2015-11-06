package com.polsri.union.app.util.response;

import java.util.List;

public class GenericMultipleResponse<T> extends GenericResponse {

	private PageInformation pageInformation;
	List<T> contents;

	public GenericMultipleResponse(ErrorCategory errorCategory, List<String> errorMessages, boolean success,
			PageInformation pageInformation, List<T> contents) {
		super(errorCategory, errorMessages, success);
		this.pageInformation = pageInformation;
		this.contents = contents;
	}

	public PageInformation getPageInformation() {
		return pageInformation;
	}

	public void setPageInformation(PageInformation pageInformation) {
		this.pageInformation = pageInformation;
	}

	public List<T> getContents() {
		return contents;
	}

	public void setContents(List<T> contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "GenericMultipleResponse [pageInformation=" + pageInformation + ", contents=" + contents
				+ ", getErrorCategory()=" + getErrorCategory() + ", getErrorMessages()=" + getErrorMessages()
				+ ", isSuccess()=" + isSuccess() + "]";
	}

}
