package test;

import java.util.HashMap;
import java.util.Map;

import com.trs.web2frame.WCMServiceCaller;
import com.trs.web2frame.dispatch.Dispatch;

@SuppressWarnings("unchecked")
public class DataExchangeUtil {

	public void setDocument(Map<String, Object> params) {

		String serviceId = "wcm6_MetaDataCenter";
		String methodName = "importMetaViewDatas";

		Map postData = new HashMap();

		postData.put("ObjectId", 0);
		postData.put("ViewId", 10);
		postData.put("ChannelId", 74);
		postData.put("Content", "测试测试测试测试测试测试测试");
		postData.put("Title", "测试");
		postData.put("IDXID", "000014348/2015-00");
		postData.put("PUBLISHER", "测试");
		postData.put("CRUSER", "admin");
		postData.put("DESCRIPTION", "11111111111111111");
		postData.put("DOCTITLE", "22222222222222");
		postData.put("CHNLNAME", "jtt");
		
		Dispatch dispatch = WCMServiceCaller.Call(serviceId, methodName,
				postData, true);

		System.out.println(dispatch.getResponseXML());
		System.out.println(dispatch.getResult());
	}

	public static void main(String[] args) {
		new DataExchangeUtil().setDocument(null);
	}
}
