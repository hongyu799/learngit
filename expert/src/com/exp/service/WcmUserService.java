package com.exp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exp.dao.WcmUserDao;
import com.exp.model.WcmUser;
import com.util.ErrMsgUtil;
import com.util.MD5Util;

@Service
public class WcmUserService {

	@Autowired
	WcmUserDao wcmUserDao;

	public WcmUser login(WcmUser wcmUser) {
		List<WcmUser> temps = wcmUserDao.login(wcmUser);
		if (null == temps || temps.size() == 0) {
			WcmUser usr = new WcmUser();
			usr.setMsg(ErrMsgUtil.ERR_USERNAME);
			return usr;
		}
		for (WcmUser usr : temps) {
			if (usr.getPassword().equals(
					MD5Util.GetBaseMD5Code(wcmUser.getPassword()).substring(0,
							15)))
				return usr;
		}
		WcmUser usr = new WcmUser();
		usr.setMsg(ErrMsgUtil.ERR_PASSWORD);
		return usr;
	}
}
