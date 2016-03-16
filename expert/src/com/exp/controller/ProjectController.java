package com.exp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exp.model.Expert;
import com.exp.model.PageBean;
import com.exp.model.ProExp;
import com.exp.model.Project;
import com.exp.model.WcmUser;
import com.exp.service.ExpertService;
import com.exp.service.PageService;
import com.exp.service.ProExpService;
import com.exp.service.ProjectService;
import com.exp.service.ValCodeInfoService;
import com.util.ErrMsgUtil;
import com.util.PropUtil;
import com.util.StringUtil;

@Controller
@RequestMapping("/pro.do")
public class ProjectController {

	@Autowired
	ValCodeInfoService valCodeInfoService;
	@Autowired
	ProjectService projectService;
	@Autowired
	ProExpService proExpService;
	@Autowired
	ExpertService expertService;
	@Autowired
	PageService pageService;

	/**
	 * 跳转添加项目页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=apro")
	public String addProject(HttpServletRequest request,
			HttpServletResponse response) {
		WcmUser wcmUser = (WcmUser) request.getSession().getAttribute(
				"userinfo");
		if (null == wcmUser) {
			request.setAttribute("error", ErrMsgUtil.ERR_LOGIN);
			return "redirect:/main.do?method=pagelogin";
		}
		if (wcmUser.getUgroup() == 2) {
			request.setAttribute("prourl", PropUtil.getPropValue("adminurl"));
		} else {
			request.setAttribute("prourl", PropUtil.getPropValue("prourl"));
		}
		request.setAttribute("tp", 3);
		return "newpro";
	}

	/**
	 * 跳转项目列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=prolist")
	public String getProjects(HttpServletRequest request,
			HttpServletResponse response) {

		int page = StringUtil.strToInt(request.getParameter("p"));
		if (page == 0)
			page = 1;

		WcmUser wcmUser = (WcmUser) request.getSession().getAttribute(
				"userinfo");
		if (null == wcmUser) {
			request.setAttribute("error", ErrMsgUtil.ERR_LOGIN);
			return "redirect:/main.do?method=pagelogin";
		}

		PageBean pageInfo = new PageBean();
		pageInfo.setSqlWhere(" SUBMITTER='" + wcmUser.getUserName() + "'");
		pageInfo.setPageNum(page);

		Project project = new Project();
		project.setTiJiaoRen(wcmUser.getUserName());

		pageInfo.setTableName(project.getTableName());
		pageInfo = pageService.getPageInfo(pageInfo);

		pageInfo.setPageNum(page);
		project.setPage(pageInfo);
		List<Project> pros = projectService.findProject(project);

		request.setAttribute("page", pageInfo);
		request.setAttribute("pros", pros);

		return "prolist";
	}

	/**
	 * 抽取专家/修改专家
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=pte")
	public String proToExp(HttpServletRequest request,
			HttpServletResponse response) {
		WcmUser wcmUser = (WcmUser) request.getSession().getAttribute(
				"userinfo");
		if (null == wcmUser) {
			request.setAttribute("error", ErrMsgUtil.ERR_LOGIN);
			return "redirect:/main.do?method=pagelogin";
		}
		String proId = request.getParameter("proid");
		String[] expIds = request.getParameterValues("exps");
		int pid = null == proId || proId.isEmpty() ? 0 : Integer
				.parseInt(proId);
		List<ProExp> proExps = new ArrayList<ProExp>();

		for (String exp : expIds) {
			ProExp proExp = new ProExp();
			proExp.setExp(StringUtil.strToInt(exp));
			proExp.setProId(pid);
			proExp.setStatus(2);
		}

		proExpService.addRelations(proExps);

		Project project = new Project();
		project.setWcmivTableId(pid);

		project = projectService.findProjectById(project);
		request.setAttribute("pro", project);
		return "viewpro";
	}

	/**
	 * 阅览项目信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=viewpro")
	public String viewProject(HttpServletRequest request,
			HttpServletResponse response) {
		int tp = StringUtil.strToInt(request.getParameter("tp"));

		WcmUser wcmUser = (WcmUser) request.getSession().getAttribute(
				"userinfo");
		if (null == wcmUser) {
			request.setAttribute("error", ErrMsgUtil.ERR_LOGIN);
			return "redirect:/main.do?method=pagelogin";
		}
		String proid = request.getParameter("proid");

		Project project = new Project();
		project.setWcmivTableId(null == proid || proid.isEmpty() ? 0 : Integer
				.parseInt(proid));
		project = projectService.findProjectById(project);
		request.setAttribute("pro", project);

		Expert expert = new Expert();
		expert.setDocNo(proid);
		List<Expert> exps = expertService.findExpsByProId(expert);
		request.setAttribute("exps", exps);
		request.setAttribute("isAdmin", wcmUser.getUgroup() == 2);
		request.setAttribute("tp", tp);
		if (tp == 1)
			return "detail";
		else
			return "viewpro";
	}

	public String delProject(HttpServletRequest request,
			HttpServletResponse response) {
		WcmUser wcmUser = (WcmUser) request.getSession().getAttribute(
				"userinfo");
		if (null == wcmUser) {
			request.setAttribute("error", ErrMsgUtil.ERR_LOGIN);
			return "redirect:/main.do?method=pagelogin";
		}
		return "redirect:/pro.do?method=prolist";
	}

	@RequestMapping(params = "method=fpro")
	public String finishPro(HttpServletRequest request,
			HttpServletResponse response) {
		String proid = request.getParameter("proid");
		String wtid = request.getParameter("id");
		Project project = new Project();
		project.setDocStatus(23);
		project.setDocNo(proid);

		projectService.updateDocStatus(project);

		return "redirect:/pro.do?method=viewpro&tp=2&proid=" + wtid;
	}
}
