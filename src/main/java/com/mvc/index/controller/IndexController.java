package com.mvc.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.redis.CodeRedisKey;
import com.common.redis.RedisUtil;

@Controller
public class IndexController {



	@ResponseBody
	@RequestMapping(value = "/index/{userId}", method = { RequestMethod.GET })
	public String SSMIndex(@PathVariable String userId, @RequestParam(value = "data", required = false) String data) {
		// requestEntity.status(HttpStatus.ACCEPTED);
		RedisUtil.set(CodeRedisKey.ORDER_SERVICE_NO + userId, "test", 3600);

		return "index";
	}

}
