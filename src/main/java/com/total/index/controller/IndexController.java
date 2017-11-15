package com.total.index.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@ResponseBody
	@RequestMapping(value="/index/{userId}",method={ RequestMethod.GET})
	public String SSMIndex(@PathVariable String userId,@RequestParam(value="data",required=false)String data){
		//requestEntity.status(HttpStatus.ACCEPTED);
		return "index";
	}
	
	
}
