package com.total.index.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.total2.Person;

@Controller
public class IndexController {

	@Resource
	private Person p;

	@ResponseBody
	@RequestMapping(value = "/index/{userId}", method = { RequestMethod.GET })
	public String SSMIndex(@PathVariable String userId, @RequestParam(value = "data", required = false) String data) {
		// requestEntity.status(HttpStatus.ACCEPTED);
		p.setAge(1);
		return "index";
	}

}
