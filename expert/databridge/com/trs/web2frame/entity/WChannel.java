/**
 * 2008-2-3
 */
package com.trs.web2frame.entity;

import java.util.HashMap;
import java.util.Map;

import com.trs.web2frame.ServiceConfig;
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

public class WChannel {
    private Map m_oChannelBody = null;

    public WChannel() {
        m_oChannelBody = new HashMap();
    }

    /**
     * @return the documentBody
     */
    public Map getChannelBody() {
        return m_oChannelBody;
    }

    public void setFieldValue(String _sFieldName, Object _oFieldValue) {
        m_oChannelBody.put(_sFieldName.toUpperCase(), _oFieldValue.toString());
    }

    public String getFieldValue(String _sFieldName) {
        if (m_oChannelBody.get(_sFieldName.toUpperCase()) == null)
            return null;
        return m_oChannelBody.get(_sFieldName.toUpperCase()).toString();
    }

    /**
     * @param _json
     * @return
     */
    public static WChannel build(Map _oJson) {
        String sChannelId = JsonHelper.getValueAsString(_oJson,
                "CHANNEL.CHANNELID");
        if (sChannelId == null || "".equals(sChannelId))
            return null;
        WChannel oChannel = new WChannel();
        oChannel.setFieldValue("CHANNELID", sChannelId);
        oChannel.setFieldValue("CHNLNAME", JsonHelper.getValueAsString(_oJson,
                "CHANNEL.CHNLNAME"));
        oChannel.setFieldValue("CRTIME", JsonHelper.getValueAsString(_oJson,
                "CHANNEL.CRTIME"));
        oChannel.setFieldValue("CHNLTYPE", JsonHelper.getValueAsString(_oJson,
                "CHANNEL.CHNLTYPE.ID"));
        oChannel.setFieldValue("PARENTID", JsonHelper.getValueAsString(_oJson,
                "CHANNEL.PARENT.ID"));
        oChannel.setFieldValue("CHNLDESC", JsonHelper.getValueAsString(_oJson,
                "CHANNEL.CHNLDESC"));
        oChannel.setFieldValue("SITEID", JsonHelper.getValueAsString(_oJson,
                "CHANNEL.SITE.ID"));
        oChannel.setFieldValue("CHNLDATAPATH", JsonHelper.getValueAsString(
                _oJson, "CHANNEL.CHNLDATAPATH"));
        return oChannel;
    }

    /**
     * 
     */
    public void setLocalChannelId(String _sLocalChannelId) {
        setFieldValue(ServiceConfig.getProperty("FIELD_NAME_CHANNELID"), _sLocalChannelId);
    }

}
