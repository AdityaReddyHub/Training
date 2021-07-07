package com.bank.reigister.customer.pojos;

import java.util.List;

public class ErrorResponse {
	
	private String errorCode;
	private List<String> errorMsg;
	
	public ErrorResponse(String errorCode,List<String> errorMsg2) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg2;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public List<String> getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(List<String> errorMsg) {
		this.errorMsg = errorMsg;
	}
}
