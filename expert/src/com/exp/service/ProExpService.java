package com.exp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exp.dao.ProExpDao;
import com.exp.model.ProExp;

@Service
public class ProExpService {

	@Autowired
	ProExpDao proExpDao;

	public int addRelation(ProExp proExp) {
		return proExpDao.addRelation(proExp);
	}

	public List<ProExp> findRelation(ProExp proExp) {
		return proExpDao.findRelation(proExp);
	}

	public int deleteRelation(ProExp proExp) {
		return proExpDao.deleteRelation(proExp);
	}

	public int addRelations(List<ProExp> proExps) {
		int result = 0;
		for (ProExp proExp : proExps) {
			result += proExpDao.addRelation(proExp);
		}
		return result;
	}

	public int getRelationCount(ProExp proExp) {
		return proExpDao.getRelationCount(proExp);
	}

	public int updateDelStatus(ProExp proExp) {
		return proExpDao.updateDelStatus(proExp);
	}

}
