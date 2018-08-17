package com.jt.common.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/resp/")
public class HandleResponseController {
	@RequestMapping("doHandleResponse01")
	@ResponseBody
	public Map<String,Object> doHandleResponse01(){//后续map中的数据来自数据库
		Map<String,Object> map=new HashMap();
		map.put("id", 100);
		map.put("title", "title A");
		return map;//map对象会转换为json格式   {"id":100,"title":"title A"}  jackson库做的
	}
}
