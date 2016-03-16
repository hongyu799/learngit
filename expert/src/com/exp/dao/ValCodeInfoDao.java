package com.exp.dao;

import com.exp.model.ValCode;

public interface ValCodeInfoDao {

	public ValCode findValCode(ValCode code);
	
	public int putValCode(ValCode valCode);
}
