package com.polsri.union.app.util.response;

import java.util.List;

public class GenericResponse {
	private ErrorCategory errorCategory;
	private List<String> errorMessages;
	private boolean success;

	public GenericResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenericResponse(ErrorCategory errorCategory, List<String> errorMessages, boolean success) {
		super();
		this.errorCategory = errorCategory;
		this.errorMessages = errorMessages;
		this.success = success;
	}

	public ErrorCategory getErrorCategory() {
		return errorCategory;
	}

	public void setErrorCategory(ErrorCategory errorCategory) {
		this.errorCategory = errorCategory;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "GenericResponse [errorCategory=" + errorCategory + ", errorMessages=" + errorMessages + ", success="
				+ success + "]";
	}

}
