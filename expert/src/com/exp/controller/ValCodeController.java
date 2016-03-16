package com.exp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exp.model.ValCode;
import com.exp.service.ValCodeInfoService;
import com.util.RanNumUtil;

@Controller
@RequestMapping("/valc.do")
public class ValCodeController {

	@Autowired
	ValCodeInfoService valCodeInfoService;
	
	@RequestMapping(params = "method=get")
	public String putCode(HttpServletRequest request,
			HttpServletResponse response){
		StringBuffer sbf=new StringBuffer("");
		for (int i = 0; i < 100; i++) {
			ValCode valCode = new ValCode();
			valCode.setCode(RanNumUtil.generateNumber());
			valCode.setExpName("0");
			valCode.setExpId(0);
			try {
				int r=valCodeInfoService.putValCode(valCode);
				if(r==1)
					sbf.append(valCode.getCode()).append("<br />");
			} catch (Exception e) {
				valCode.setCode(RanNumUtil.generateNumber());
				int r=valCodeInfoService.putValCode(valCode);
				if(r==1)
					sbf.append(valCode.getCode()).append("<br />");
			}
		}
		request.setAttribute("code", sbf.toString());
		return "valcode";
	}
}
