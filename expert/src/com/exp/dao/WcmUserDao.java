package com.exp.dao;

import java.util.List;

import com.exp.model.WcmUser;

public interface WcmUserDao {

	public List<WcmUser> login(WcmUser wcmUser);
}
