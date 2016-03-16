package com.exp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exp.dao.ExpertDao;
import com.exp.model.Expert;

@Service
public class ExpertService {

	@Autowired
	ExpertDao expertDao;

	public List<Expert> findExps(Expert expert) {
		return expertDao.findExps(expert);
	}
	
	public List<Expert> findExpsByProId(Expert expert){
		return expertDao.findExpsByProId(expert);
	}
	
	public List<Expert> queryExps(Expert expert){
		return expertDao.queryExps(expert);
	}
}
