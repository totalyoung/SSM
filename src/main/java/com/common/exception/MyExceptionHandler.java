package com.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.common.JsonResult;

@ControllerAdvice
public class MyExceptionHandler{

	
	 @ExceptionHandler({Exception.class})
	 @ResponseStatus(HttpStatus.OK)
	 public ModelAndView processException(Exception exception) {
        ModelAndView m = new ModelAndView();
        m.addObject("ericException", exception.getMessage());
        m.setViewName("error/500");
        return m;
    }
	 
	 
	 @ExceptionHandler({MyException.class})
	 @ResponseStatus(HttpStatus.OK)
	 public ModelAndView processException(MyException myException) {
        ModelAndView m = new ModelAndView();
        m.addObject("ericException", myException.getMessage());
        m.setViewName("error/500");
        return m;
    }
	 
	 @ExceptionHandler({MyException.class})
	 @ResponseStatus(HttpStatus.OK)
	 @ResponseBody
	 public JsonResult ajaxException(MyException myException) {
       
        m.addObject("ericException", myException.getMessage());
        m.setViewName("error/500");
        return m;
    }
}
