package com.celestabank.celestabankapi.advice;

import java.io.Serializable;
import java.util.Date;


public class ErrorMapper implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
    private String error_message;
    private Date error_time;
    
	public ErrorMapper() {
		super();
	}
	
	public ErrorMapper(String url, String error_message, Date error_time) {
		super();
		this.url = url;
		this.error_message = error_message;
		this.error_time = error_time;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	public Date getError_time() {
		return error_time;
	}
	public void setError_time(Date error_time) {
		this.error_time = error_time;
	}
    
    
}
