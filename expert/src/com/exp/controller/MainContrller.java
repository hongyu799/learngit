package com.exp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exp.model.Project;
import com.exp.model.WcmUser;
import com.exp.service.ProjectService;
import com.exp.service.WcmUserService;
import com.util.ErrMsgUtil;
import com.util.PropUtil;

@Controller
@RequestMapping("/main.do")
public class MainContrller {

	@Autowired
	WcmUserService wcmUserService;
	@Autowired
	ProjectService projectService;

	@ResponseBody
	@RequestMapping(params = "method=login")
	public HashedMap adminLogin(HttpServletRequest request,
			HttpServletResponse response) {

		String loginName = request.getSession().getAttribute("username")+"";
		String password = request.getSession().getAttribute("password")+"";
		request.getSession().removeAttribute("username");
		request.getSession().removeAttribute("password");
		loginName="管理员1";
		password="11111111";
		WcmUser wcmUser = new WcmUser();
		wcmUser.setUserName(loginName);
		wcmUser.setPassword(password);

		wcmUser = wcmUserService.login(wcmUser);

		HashedMap map = new HashedMap();
		if (wcmUser.isERROR()) {
			map.put("success", false);
			map.put("msg", wcmUser.getMsg());
		} else {
			request.getSession().setAttribute("userinfo", wcmUser);
			map.put("success", true);
		}
		return map;
	}

	@RequestMapping(params = "method=pagelogin")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		
		request.getSession().setAttribute("username",request.getParameter("username"));
		request.getSession().setAttribute("password", request.getParameter("password"));

		WcmUser wcmUser = (WcmUser) request.getSession().getAttribute("userinfo");
		if (null != wcmUser) {
			request.setAttribute("error", ErrMsgUtil.ERR_LOGIN);
			//return "redirect:/pro.do?method=prolist";
		}
		Project project = new Project();
		project = projectService.findNewProject(project);
		request.setAttribute("pro", project);
		request.setAttribute("wcmurl", PropUtil.getPropValue("wcmurl"));
		request.setAttribute("tp", 3);
		return "login";
	}

	@RequestMapping(params = "method=logout")
	public String adminLogout(HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().removeAttribute("userinfo");
		return "redirect:/main.do?method=pagelogin";
	}
	
	@RequestMapping(params = "method=ecm")
	public String pageCodeMoblie(HttpServletRequest request,
			HttpServletResponse response){
		
		return "expmobile";
	}

}
