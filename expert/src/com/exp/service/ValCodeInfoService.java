package com.exp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exp.dao.ValCodeInfoDao;
import com.exp.model.ValCode;

@Service
public class ValCodeInfoService {

	@Autowired
	ValCodeInfoDao valCodeInfoDao;

	public ValCode findValCode(ValCode code) {
		return valCodeInfoDao.findValCode(code);
	}

	public int putValCode(ValCode valCode) {
		return valCodeInfoDao.putValCode(valCode);
	}
}
