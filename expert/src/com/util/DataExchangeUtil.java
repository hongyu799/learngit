package com.util;

import java.util.HashMap;
import java.util.Map;

import com.trs.web2frame.WCMServiceCaller;
import com.trs.web2frame.dispatch.Dispatch;

public class DataExchangeUtil {

	@SuppressWarnings("unchecked")
	public void setDocument(Map<String, Object> params) {

		String serviceId = "wcm61_infoviewdoc";
		String methodName = "save";

		Map postData = new HashMap();
		if (null != params) {
			for (String StrKey : params.keySet()) {
				postData.put(StrKey, params.get(StrKey));
			}

			Dispatch dispatch = WCMServiceCaller.Call(serviceId, methodName, postData, true);

			System.out.println(dispatch.getResponseXML());
			System.out.println(dispatch.getResult());
		}
	}

}
