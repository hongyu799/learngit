package com.exp.dao;

import java.util.List;

import com.exp.model.Expert;

public interface ExpertDao {

	public List<Expert> findExps(Expert expert);
	
	public List<Expert> findExpsByProId(Expert expert);
	
	public List<Expert> queryExps(Expert expert);
}
