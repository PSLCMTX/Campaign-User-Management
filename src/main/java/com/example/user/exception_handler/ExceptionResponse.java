package com.example.user.exception_handler;

public class ExceptionResponse {
	private String errorMessage;
	private String description;

	public ExceptionResponse(String throwable, String description) {
		this.errorMessage = throwable;
		this.description = description;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [errorMessage=" + errorMessage + ", description=" + description + "]";
	}

}
