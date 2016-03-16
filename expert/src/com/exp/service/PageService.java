package com.exp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exp.dao.PageDao;
import com.exp.model.PageBean;

@Service
public class PageService {

	@Autowired
	PageDao pageDao;

	public PageBean getPageInfo(PageBean pageBean) {
		return pageDao.getPageInfo(pageBean);
	}
}
