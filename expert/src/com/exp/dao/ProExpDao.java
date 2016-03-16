package com.exp.dao;

import java.util.List;

import com.exp.model.ProExp;

public interface ProExpDao {

	public int addRelation(ProExp proExp);

	public List<ProExp> findRelation(ProExp proExp);
	
	public int deleteRelation(ProExp proExp);
	
	public int getRelationCount(ProExp proExp);
	
	public int updateDelStatus(ProExp proExp);
}
