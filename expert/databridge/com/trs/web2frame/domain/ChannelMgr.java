/**
 * 2008-1-28
 */
package com.trs.web2frame.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trs.infra.util.CMyString;
import com.trs.web2frame.ServiceConfig;
import com.trs.web2frame.ServiceObject;
import com.trs.web2frame.WCMServiceCaller;
import com.trs.web2frame.Web2frameClientException;
import com.trs.web2frame.dispatch.Dispatch;
import com.trs.web2frame.entity.WChannel;
import com.trs.web2frame.eventhandler.ICallbackEventHandler;
import com.trs.web2frame.eventhandler.ICallbackSuccessHandler;
import com.trs.web2frame.util.JsonHelper;

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

public class ChannelMgr {

    public static final String FIELD_OBJECT_IDS = "ObjectIds";

    public static final String FIELD_OBJECT_ID = "ObjectId";

    public static final String FIELD_CHANNEL_ID = "ChannelId";

    public final static String ms_ServiceId = "wcm6_channel";

    public final static int ms_OBJ_TYPE = 101;

    public static WChannel findByLocalId(int nSiteId, String _sLocalId) {
        ServiceObject oServiceObject = new ServiceObject(ms_ServiceId,
                "filterChannels");
        Map oPostData = new HashMap();
        oPostData.put("SiteId", String.valueOf(nSiteId));
        oPostData.put(CMyString.showNull(ServiceConfig
                .getProperty("FIELD_NAME_CHANNELID"), "OutChannelId"),
                _sLocalId);
        oServiceObject.setPostData(oPostData);
        Dispatch oDispatch = WCMServiceCaller.Call(oServiceObject, true);
        Map oJson = oDispatch.getJson();
        if (oJson != null) {
            List lstChannels = JsonHelper.getList(oJson, "Channels.Channel");
            if (lstChannels != null && lstChannels.size() > 0) {
                Map oChannelJson = new HashMap();
                oChannelJson.put("CHANNEL", (Map) lstChannels.get(0));
                return WChannel.build(oChannelJson);
            }
        }
        return null;
    }

    public static WChannel findById(int nChannel) {
        ServiceObject oServiceObject = new ServiceObject(ms_ServiceId,
                "findById");
        Map oPostData = new HashMap();
        oPostData.put(FIELD_OBJECT_ID, String.valueOf(nChannel));
        oServiceObject.setPostData(oPostData);
        Dispatch oDispatch = WCMServiceCaller.Call(oServiceObject, true);
        return oDispatch.toChannel();
    }

    /**
     * @param _channel
     */
    public static int save(WChannel _channel) {
        return save(_channel, null);
    }

    public static int save(final WChannel _oChannel,
            ICallbackEventHandler[] _arrHandlers) {
        ServiceObject oServiceObject = new ServiceObject(ms_ServiceId, "save");
        Map oPostData = _oChannel.getChannelBody();
        oPostData.put(FIELD_OBJECT_ID, _oChannel
                .getFieldValue(FIELD_CHANNEL_ID));
        oServiceObject.setPostData(oPostData);
        oServiceObject
                .addEventHandlers(new ICallbackEventHandler[] { new ICallbackSuccessHandler() {
                    public void onSuccess(Dispatch _dispatch)
                            throws Web2frameClientException {
                        String sChannelId = JsonHelper.getValueAsString(
                                _dispatch.getJson(), "result");
                        _oChannel.setFieldValue(FIELD_CHANNEL_ID, sChannelId);
                    }
                } });
        oServiceObject.addEventHandlers(_arrHandlers);
        WCMServiceCaller.Call(oServiceObject, true);
        return Integer.parseInt(_oChannel.getFieldValue(FIELD_CHANNEL_ID));
    }

    public static boolean delete(String _sChannelIds) {
        return delete(_sChannelIds, true);
    }

    public static boolean delete(String _sChannelIds, boolean bDrop) {
        ServiceObject oServiceObject = new ServiceObject(ms_ServiceId, "delete");
        Map oPostData = new HashMap();
        oPostData.put(FIELD_OBJECT_IDS, _sChannelIds);
        oPostData.put("Drop", String.valueOf(bDrop));
        oServiceObject.setPostData(oPostData);
        Dispatch oDispatch = WCMServiceCaller.Call(oServiceObject, true);
        return !oDispatch.isFailure();
    }
}
