/**
 * 2008-1-29
 */
package com.trs.web2frame.dispatch;

import java.util.Map;

import org.dom4j.Document;

import com.trs.web2frame.entity.WChannel;
import com.trs.web2frame.entity.WDocument;
import com.trs.web2frame.httpclient.ResponseBuddy;
import com.trs.web2frame.util.JsonHelper;
import com.trs.web2frame.util.XmlHelper;

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

public class Dispatch {
    private ResponseBuddy m_oResponseBuddy = null;

    private boolean m_bIsXml = false;

    private boolean m_bIsJson = false;

    private Document m_oResponseXML = null;

    private String m_sResponseText = null;

    private Map m_mpJson = null;

    private boolean m_bIsFailure = false;

    public Dispatch(ResponseBuddy _ResponseBuddy) {
        super();
        m_oResponseBuddy = _ResponseBuddy;
        this.m_bIsXml = Dispatch.isXmlContentType(this.m_oResponseBuddy
                .getHeader("Content-Type"));
        this.m_bIsJson = this.m_oResponseBuddy.getHeader("ReturnJson") == "true";
    }

    public ResponseBuddy getResponseBuddy() {
        return m_oResponseBuddy;
    }

    /**
     * @param sContentType
     * @return
     */
    private static boolean isXmlContentType(String sContentType) {
        return "text/xml".equalsIgnoreCase(sContentType)
                || sContentType.toLowerCase().startsWith("text/xml;")
                || (sContentType.toLowerCase().startsWith("application/") && sContentType
                        .endsWith("xml"));
    }

    public Dispatch(String _sDispatchBody) {
        this.m_oResponseXML = XmlHelper.parse2XML(this.m_sResponseText);
    }

    /**
     * @return the responseXML
     */
    public Document getResponseXML() {
        if (this.m_bIsXml) {
            if (this.m_oResponseXML == null) {
                this.m_oResponseXML = XmlHelper.parse2XML(this
                        .getResponseText());
            }
            return this.m_oResponseXML;
        }
        return null;
    }

    /**
     * @return the responseText
     */
    public String getResponseText() {
        if (this.m_sResponseText == null) {
            this.m_sResponseText = this.m_oResponseBuddy.getBodyAsString();
        }
        return this.m_sResponseText;
    }

    public Map getJson() {
        if (this.m_bIsJson) {
            if (this.m_mpJson == null) {
                this.m_mpJson = parseJson2Map(this.getResponseText());
            }
            return this.m_mpJson;
        } else {
            Document oXmlDocument = this.getResponseXML();
            if (oXmlDocument != null) {
                if (this.m_mpJson == null) {
                    this.m_mpJson = XmlHelper.parseXml2Json(oXmlDocument);
                }
                return this.m_mpJson;
            }
        }
        return null;
    }

    /**
     * 将Json格式的字符串转换成Map对象
     * 
     * @param _responseText
     * @return
     */
    public Map parseJson2Map(String _responseText) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return 上传文件的显示名
     */
    public String getUploadShowName() {
        Map oJson = this.getJson();
        return JsonHelper.getValueAsString(oJson, "Result.ShowName");
    }

    /**
     * @return
     */
    public WDocument toDocument() {
        Map oJson = this.getJson();
        if (oJson != null)
            return WDocument.build(oJson);
        return null;
    }

    /**
     * @return
     */
    public String getResult() {
        Map oJson = this.getJson();
        return JsonHelper.getValueAsString(oJson, "Result");
    }

    /**
     * @param _b
     */
    public void setFailure(boolean _b) {
        this.m_bIsFailure = _b;
    }

    public boolean isFailure() {
        return this.m_bIsFailure;
    }

    /**
     * @return
     */
    public WChannel toChannel() {
        Map oJson = this.getJson();
        if (oJson != null)
            return WChannel.build(oJson);
        return null;
    }
}
