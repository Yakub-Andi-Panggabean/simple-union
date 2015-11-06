package com.polsri.union.app.util.response;

import java.util.List;

public class GenericSingleResponse<T> extends GenericResponse {

	private T content;

	public GenericSingleResponse(T content, ErrorCategory errorCategory, List<String> errorMessages, boolean success) {
		super(errorCategory, errorMessages, success);
		this.content = content;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "GenericSingleResponse [content=" + content + ", getErrorCategory()=" + getErrorCategory()
				+ ", getErrorMessages()=" + getErrorMessages() + ", isSuccess()=" + isSuccess() + "]";
	}

}
