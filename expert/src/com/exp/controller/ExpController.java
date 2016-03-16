package com.exp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exp.model.Expert;
import com.exp.model.PhoneCode;
import com.exp.model.ProExp;
import com.exp.model.Project;
import com.exp.model.WcmUser;
import com.exp.service.ExpertService;
import com.exp.service.ProExpService;
import com.exp.service.ProjectService;
import com.exp.service.ValCodeInfoService;
import com.util.DataUtil;
import com.util.ErrMsgUtil;
import com.util.HttpUtil;
import com.util.PropUtil;
import com.util.RanNumUtil;
import com.util.StringUtil;

@Controller
@RequestMapping("/exp.do")
public class ExpController {

	@Autowired
	ValCodeInfoService valCodeInfoService;
	@Autowired
	ExpertService expertService;
	@Autowired
	ProExpService proExpService;
	@Autowired
	ProjectService projectService;

	/**
	 * 校验码校验
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(params = "method=vcode")
	public HashedMap valCode(HttpServletRequest request,
			HttpServletResponse response) {
		HashedMap map = new HashedMap();
		String code = request.getParameter("code");
		if (StringUtil.inNull(code)) {
			map.put("success", false);
			map.put("msg", ErrMsgUtil.NULL_EXPCODE);
			return map;

		}
		String mobile = request.getParameter("phone");
		if (StringUtil.inNull(mobile)) {
			map.put("success", false);
			map.put("msg", ErrMsgUtil.NULL_MOBILE);
			return map;
		}
		PhoneCode pcode = DataUtil.getCodeByPhone(mobile);
		if (null == pcode) {
			map.put("success", false);
			map.put("msg", ErrMsgUtil.NO_SEND_EXPCODE);
			return map;
		}
		if (!mobile.equals(pcode.getPhone())) {
			map.put("success", false);
			map.put("msg", ErrMsgUtil.ERR_MOBILE);
			return map;
		}
		if (!code.equals(pcode.getVcode())) {
			map.put("success", false);
			map.put("msg", ErrMsgUtil.ERR_EXPCODE);
			return map;
		}
		if (pcode.isTimeOut()) {
			map.put("success", false);
			map.put("msg", ErrMsgUtil.TIMEOUT_EXPCODE);
			return map;
		}

		map.put("success", true);
		return map;
	}

	/**
	 * 抽取专家列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=exps")
	public String findExps(HttpServletRequest request,
			HttpServletResponse response) {
		String tp = request.getParameter("tp");
		WcmUser wcmUser = (WcmUser) request.getSession().getAttribute(
				"userinfo");
		if (null == wcmUser) {
			request.setAttribute("error", ErrMsgUtil.ERR_LOGIN);
			return "redirect:/main.do?method=pagelogin";
		}
		String proid = request.getParameter("proid");

		Expert expert = new Expert();
		expert.setDocNo(proid);

		Project project = new Project();
		project.setWcmivTableId(StringUtil.strToInt(proid));
		project = projectService.findProjectById(project);

		ProExp pe = new ProExp();
		pe.setProId(project.getWcmivTableId());

		List<ProExp> expCount = proExpService.findRelation(pe);
		List<Integer> isNotOk = new ArrayList<Integer>();
		for (ProExp pexp : expCount) {
			if (pexp.isDel())
				isNotOk.add(pexp.getExp());
		}

		expert.setProject(project);
		int expNum = StringUtil.strToInt(project.getZhuanJiaRenShu());// StringUtil.strToInt(PropUtil.getPropValue("expnum"));

		List<Expert> exps = expertService.findExps(expert);

		if (null != expCount && expCount.size() != 0) {
			expNum = expNum - expCount.size() + isNotOk.size();
		}

		List<ProExp> proExps = new ArrayList<ProExp>();

		if (exps.size() <= expNum) {
			expNum = exps.size();
		}

		exp: for (int i = 0; i < expNum;) {
			int idx = (int) (Math.random() * exps.size());
			Expert temp = exps.get(idx);
			for (int j = 0; j < expCount.size(); j++) {
				if (expCount.get(j).getExp() == temp.getWcmivTableId()
						|| isNotOk.contains(temp.getWcmivTableId()))
					continue exp;
			}
			ProExp proExp = new ProExp();
			proExp.setProId(StringUtil.strToInt(proid));
			proExp.setExp(temp.getWcmivTableId());
			proExp.setFirstTime(new Date());
			proExp.setStatus(1);

			proExps.add(proExp);
			exps.remove(idx);
			i++;
		}

		int result = proExpService.addRelations(proExps);
		System.out.println("操作结果" + result);

		request.setAttribute("proid", proid);

		return "redirect:/pro.do?method=viewpro&tp=" + tp + "&proid=" + proid;
	}

	/**
	 * 修改抽取专家列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "method=update")
	public HashedMap updateExps(HttpServletRequest request,
			HttpServletResponse response) {
		HashedMap map = new HashedMap();
		String proid = request.getParameter("proid");
		String expnew = request.getParameter("expnew");
		String expold = request.getParameter("expold");

		String[] expolds = expold.split(",");
		for (int i = 0; i < expolds.length; i++) {
			int oldid = StringUtil.strToInt(expolds[i]);
			if (oldid == 0)
				continue;
			ProExp proExpOld = new ProExp();
			proExpOld.setProId(StringUtil.strToInt(proid));
			proExpOld.setExp(oldid);
			int dresult = proExpService.deleteRelation(proExpOld);
			System.out.println("删除关系数=>" + dresult);
		}
		String[] expnews = expnew.split(",");
		List<ProExp> proExps = new ArrayList<ProExp>();
		for (int i = 0; i < expnews.length; i++) {
			int newid = StringUtil.strToInt(expnews[i]);
			if (newid == 0)
				continue;
			ProExp proExp = new ProExp();
			proExp.setProId(StringUtil.strToInt(proid));
			proExp.setExp(newid);
			proExp.setFirstTime(new Date());
			proExp.setStatus(1);

			proExps.add(proExp);
		}
		int aresult = proExpService.addRelations(proExps);
		System.out.println("操作结果" + aresult);

		map.put("success", true);
		return map;
	}

	/**
	 * 重新抽取专家列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=rexps")
	public String reExps(HttpServletRequest request,
			HttpServletResponse response) {
		WcmUser wcmUser = (WcmUser) request.getSession().getAttribute(
				"userinfo");
		if (null == wcmUser) {
			request.setAttribute("error", ErrMsgUtil.ERR_LOGIN);
			return "redirect:/main.do?method=pagelogin";
		}
		int proId = StringUtil.strToInt(request.getParameter("proid"));

		ProExp proExp = new ProExp();
		proExp.setProId(proId);
		// int result = proExpService.deleteRelation(proExp);
		// System.out.println("删除关系数=>" + result);
		return "forward:/exp.do?method=exps";
	}

	/**
	 * 删除单个专家列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=del")
	public String delExps(HttpServletRequest request,
			HttpServletResponse response) {
		WcmUser wcmUser = (WcmUser) request.getSession().getAttribute(
				"userinfo");
		if (null == wcmUser) {
			request.setAttribute("error", ErrMsgUtil.ERR_LOGIN);
			return "redirect:/main.do?method=pagelogin";
		}
		int proId = StringUtil.strToInt(request.getParameter("proid"));
		int exp = StringUtil.strToInt(request.getParameter("exp"));
		String tp = request.getParameter("tp");
		String remark = request.getParameter("remark");

		ProExp proExp = new ProExp();
		proExp.setProId(proId);
		proExp.setExp(exp);
		proExp.setRemark(remark);
		proExp.setStatus(2);
		int result = proExpService.updateDelStatus(proExp);
		System.out.println("删除关系数=>" + result);
		return "redirect:/pro.do?method=viewpro&tp=" + tp + "&proid=" + proId;
	}

	@RequestMapping(params = "method=query")
	public String queryExps(HttpServletRequest request,
			HttpServletResponse response) {

		String proId = request.getParameter("proid");

		Expert expert = new Expert();
		expert.setDocNo(proId);

		Project project = new Project();
		project.setWcmivTableId(StringUtil.strToInt(proId));
		project = projectService.findProjectById(project);
		expert.setProject(project);

		List<Expert> exps = expertService.queryExps(expert);
		request.setAttribute("explist", exps);
		request.setAttribute("proId", proId);
		return "explist";
	}
	

	/**
	 * 发送验证码
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(params = "method=sms")
	public HashedMap sendVCode(HttpServletRequest request,
			HttpServletResponse response) {
		HashedMap map = new HashedMap();
		String mobile = request.getParameter("phone");

		if (StringUtil.inNull(mobile)) {
			map.put("success", false);
			return map;
		}
		String code = RanNumUtil.generateNumber();
		String str = "本次注册码为:"+code;
		HttpUtil.fromPost(PropUtil.getPropValue("smsurl"), mobile,StringUtil.encodeHex(str));
		map.put("success", true);

		DataUtil.setPhoneCode(mobile, code);
		return map;
	}

	@RequestMapping(params = "method=res")
	public String pageRes(HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("proid", request.getParameter("proid"));
		request.setAttribute("expid", request.getParameter("expid"));
		return "delres";
	}

	@ResponseBody
	@RequestMapping(params = "method=dele")
	public HashedMap delExp(HttpServletRequest request,
			HttpServletResponse response) {
		HashedMap map = new HashedMap();

		String proid = request.getParameter("proid");
		String expid = request.getParameter("expid");
		String res = request.getParameter("res");

		ProExp proExp = new ProExp();
		proExp.setExp(StringUtil.strToInt(expid));
		proExp.setProId(StringUtil.strToInt(proid));
		proExp.setRemark(res);

		int result = proExpService.updateDelStatus(proExp);

		if (result <= 0) {
			map.put("success", false);
			map.put("msg", ErrMsgUtil.ERR_DEL_PRO_EXP);
			return map;
		}
		map.put("success", true);
		return map;
	}
	//跳转群发短信界面
	@RequestMapping(params = "method=smsUserList")
	public String smsUserList(HttpServletRequest request,
			HttpServletResponse response) {
		
		return "smsinfo";
	}
	//群发短信
	@RequestMapping(params = "method=smsSet")
	public void smsSet(HttpServletRequest request,
			HttpServletResponse response) {
		String dx_info = request.getParameter("dx_info");
		List<Expert> exps = (List<Expert>)request.getSession().getAttribute("smsUserList");
		if(null != exps){
    		for(int i=0; i<exps.size(); i++){
    			String expPhone = exps.get(i).getYiDongDianHua();
    			//遍历专家电话发送短信
    			HttpUtil.fromPost(PropUtil.getPropValue("smsurl"), expPhone, StringUtil.encodeHex(dx_info));
    			System.out.println("成功发送专家移动手机号码   >>>>>"+expPhone);
    		}
    	}
		try {
			response.getWriter().write("{\"success\":true }");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
}
