package com.mvc.file.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.common.JsonResult;
import com.common.ResponseCode;
import com.total2.Person;

/**
 * 
 * @author yangst
 * @date 2018年1月10日 下午3:08:01
 * 
 */
@Controller
public class FileHanlder {

	
	@ResponseBody
	@RequestMapping(value = "/File/upload", method = { RequestMethod.POST })
	public JsonResult File(@ModelAttribute Person person, MultipartHttpServletRequest fileRequset) throws IOException {
		Map<String, MultipartFile> fileMap = fileRequset.getFileMap();
		for (Entry<String, MultipartFile> entry: fileMap.entrySet()) {
			MultipartFile value = entry.getValue();
			InputStream inputStream = value.getInputStream();
		}
		JsonResult jsonResult = new JsonResult();
		jsonResult.setResponseCode(ResponseCode.CODE_10001);
		return jsonResult;
	}
}
