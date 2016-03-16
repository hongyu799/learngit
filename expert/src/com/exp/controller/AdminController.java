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
import com.util.PropUtil;

@Controller
@RequestMapping("/adm.do")
public class AdminController {

	@Autowired
	ProjectService projectService;
	@Autowired
	WcmUserService wcmUserService;

	@RequestMapping(params = "method=admp")
	public String toAdminPage(HttpServletRequest request,
			HttpServletResponse response) {
		WcmUser wcmUser = new WcmUser();
		wcmUser.setUserName(PropUtil.getPropValue("admname"));
		wcmUser.setPassword(PropUtil.getPropValue("admpass"));

		wcmUser = wcmUserService.login(wcmUser);
		request.getSession().setAttribute("userinfo", wcmUser);

		request.setAttribute("username", wcmUser.getUserName());
		request.setAttribute("adminurl", PropUtil.getPropValue("adminurl"));
		return "admin";
	}

	@RequestMapping(params = "method=view")
	public String viewProject(HttpServletRequest request,
			HttpServletResponse response) {

		Project project = new Project();
		project = projectService.findNewProject(project);
		request.setAttribute("pro", project);

		request.setAttribute("exps", null);

		return "detail";
	}

	@ResponseBody
	@RequestMapping(params = "method=getn")
	public HashedMap getNewPro(HttpServletRequest request,
			HttpServletResponse response) {
		Project project = new Project();
		project = projectService.findNewProject(project);

		HashedMap map = new HashedMap();
		map.put("success", true);
		map.put("pid", project.getWcmivTableId());
		return map;
	}
}
