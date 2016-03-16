/**
 * 2008-1-28
 */
package com.trs.web2frame.domain;

import java.util.Map;

import com.trs.infra.util.CMyString;
import com.trs.web2frame.ServiceObject;
import com.trs.web2frame.WCMServiceCaller;
import com.trs.web2frame.dispatch.Dispatch;
import com.trs.web2frame.entity.WGovInfo;

/**
 * Title: TRS 内容协作平台（TRS WCM） <BR>
 * Description: <BR>
 * TODO <BR>
 * Copyright: Copyright (c) 2004-2005 TRS信息技术有限公司 <BR>
 * Company: TRS信息技术有限公司(www.trs.com.cn) <BR>
 * 
 * @author TRS信息技术有限公司 LY
 * @version 1.0
 */

public class GovInfoMgr {
    public static final String FIELD_OBJECT_IDS = "ObjectIds";

    public static final String FIELD_OBJECT_ID = "ObjectId";

    public static final String FIELD_GOVINFO_ID = "GovInfoId";

    public final static String ms_ServiceId = "wcm6_MetaDataCenter";

    public static Dispatch save(final WGovInfo _oGovInfo) {
        // wenyh@2008-6-25 8:42:18 add comment:saveMetaViewDataOfGov --> saveMetaViewData
        ServiceObject oServiceObject = new ServiceObject(ms_ServiceId,
                "saveMetaViewData");
        Map oPostData = _oGovInfo.getContentBody();
        oPostData.put(FIELD_OBJECT_ID, CMyString.showNull(_oGovInfo
                .getFieldValue(FIELD_GOVINFO_ID), "0"));
        oServiceObject.setPostData(oPostData);
        return WCMServiceCaller.Call(oServiceObject, true);
    }
}