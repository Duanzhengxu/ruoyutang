package com.jt.common.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jt.pojo.Message;

@Controller
@RequestMapping("/req/")
public class HandleRequestController {
	/*
	 * 请求路径的普通Url映射
	 */
	@RequestMapping(value= {"doHandleRequest01","doUrl01"})
	@ResponseBody
	public String doHandleRequest01() {
		
		return "doHandleRequest01";
	}
	//rest（表述性架构风格）风格的Url映射 其中{}为rest表达式  类似于一个通配符 当有精确匹配和模糊匹配 精确匹配优先  注意404异常
	@RequestMapping("{ddd }")
	@ResponseBody
	public String doHandleRequest02() {
		
		return "doHandleRequest02--REST";
	}
	/*表示此方法只能处理get请求  @GetMapping注解也可以实现   请求方式注意405异常*/
	@RequestMapping(value="doHandleRequest03",method=RequestMethod.GET)
	@ResponseBody//不写会报404错误
    public String doHandleRequest03() {
		
		return "doHandleRequest03--GET";
	}
	//servlet原生api处理请求参数
	@RequestMapping("doHandleRequest04")
	@ResponseBody
	public String doHandleRequest04(HttpServletRequest request) {
		String oc = request.getParameter("PageCurrent");
		return "doHandleRequest04 PageCurrent="+oc;
	}
	//使用直接量接收或者处理 请求参数  8种对象类型加字符串  布尔类型数据可以是true和false  0（false）和1（true）
	@RequestMapping("doHandleRequest05")
	@ResponseBody
	public String doHandleRequest05(Integer PageCurrent) {
		return "doHandleRequest05 PageCurrent="+PageCurrent;
	}
	//使用@RequestParam 表示必须传值 不然会报400异常（客户端传的数据不符合服务端数据格式要求）
	@RequestMapping("doHandleRequest06")
	@ResponseBody
	public String doHandleRequest06(@RequestParam(required=true)Integer PageCurrent,String msg,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date date) {
		return "doHandleRequest05 PageCurrent="+PageCurrent+"date="+date.toLocaleString();
	}
	//使用javaBean对象接收请求参数  请求参数名与bean对象的set方法保持一致
	@RequestMapping("doHandleRequest07")
	@ResponseBody
	public String doHandleRequest07(Message s) {
		return "doHandleRequest07 msg="+s;
	}
	/*当使用参数接收rest风格url中的数据时 使用@PathVariable 注解对参数进行修饰*/
	@RequestMapping("doHandleRequest08/{id}")
	@ResponseBody
	public String doHandleRequest08(@PathVariable Integer id) {
		return "doHandleRequest08 id="+id;
	}
	@RequestMapping("doHandleRequest09")
	@ResponseBody
	public String doHandleRequest09(@RequestHeader("Accept-Encoding") String AcceptEncoding,
			HttpSession session) {
		return "doHandleRequest09;Accept-Encoding="+AcceptEncoding;
	}
	@RequestMapping(value="doHandleRequest10",produces="text/html;charset=utf-8")
	@ResponseBody
	public String doHandleRequest10(@CookieValue String JSESSIONID) {
		return "doHandleRequest10;JSESSIONID是"+JSESSIONID;
	}
}
