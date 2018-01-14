package com.mvc.index.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.total2.Person;

@Controller
public class IndexController {

	@ResponseBody
	@RequestMapping(value = "/index/{userId}", method = { RequestMethod.GET })
	public ResponseEntity<Person> SSMIndex(@PathVariable String userId,
			@RequestParam(value = "data", required = false) String data) {
		// requestEntity.status(HttpStatus.ACCEPTED);
		Person p = new Person();
		p.setAge(1);
		p.setName("adfkjh");
		// RedisUtil.set(CodeRedisKey.ORDER_SERVICE_NO + userId, "test", 3600);
		return new ResponseEntity<Person>(p, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/index2/{userId}", method = { RequestMethod.POST })
	public Person SSMIndex2(@PathVariable String userId, @ModelAttribute Person p) {
		// requestEntity.status(HttpStatus.ACCEPTED);
		// RedisUtil.set(CodeRedisKey.ORDER_SERVICE_NO + userId, "test", 3600);
		return p;
	}

}
