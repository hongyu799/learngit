package com.exp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.util.PropUtil;
import com.util.RanNumUtil;

@Controller
@RequestMapping("/register.do")
public class ExpRegisterController {
	/**
	 * 跳转专家注册页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=reg")
	public String addProject(HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("expurl", PropUtil.getPropValue("expurl"));
		request.setAttribute("code", request.getParameter("code"));
		request.setAttribute("tp", 3);
		return "register";
	}
	
	public String sendPhoneCode(HttpServletRequest request,
			HttpServletResponse response){
		String mobile=request.getParameter("phone");
		String vcode=RanNumUtil.generateNumber();
		
		
		return "";
	}
}
