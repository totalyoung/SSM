package com.common.exception;

import com.common.JsonResult;

public class MyException extends RuntimeException{
	
    private static final long serialVersionUID = 1L;

    private JsonResult jsonResult;
    
    public JsonResult getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(JsonResult jsonResult) {
		this.jsonResult = jsonResult;
	}


	public MyException() {
        super();
    }


    public MyException(String message) {
        super(message);
    }


    public MyException(String message, Throwable cause) {
        super(message, cause);
    }


    public MyException(Throwable cause) {
        super(cause);
    }
    
    public MyException(JsonResult jsonResult) {
    	super();
    	setJsonResult(jsonResult);
    }
	
}
