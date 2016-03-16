/**
 * 2008-1-31
 */
package com.trs.web2frame.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

public class WDocument {
    private Map m_oDocumentBody = null;

    private String m_sQuoteToChannelIds = null;

    private List m_lstAppendix = null;

    private List m_lstRelations = null;

    public WDocument() {
        m_oDocumentBody = new HashMap();
        m_lstAppendix = new ArrayList();
        m_lstRelations = new ArrayList();
    }

    public static WDocument build(Map _oJson) {
        String sDocumentId = JsonHelper.getValueAsString(_oJson,
                "DOCUMENT.DOCID");
        if (sDocumentId == null || "".equals(sDocumentId))
            return null;
        WDocument oDocument = new WDocument();
        oDocument.setFieldValue("DocType", JsonHelper.getValueAsString(_oJson,
                "DOCUMENT.DOCTYPE.TYPE"));
        oDocument.setFieldValue("DocHtmlCon", JsonHelper.getValueAsString(
                _oJson, "DOCUMENT.DocHtmlCon"));
        oDocument.setFieldValue("DOCSOURCE", JsonHelper.getValueAsString(
                _oJson, "DOCUMENT.DOCSOURCE.ID"));
        oDocument.setFieldValue("OPERTIME", JsonHelper.getValueAsString(_oJson,
                "DOCUMENT.OPERTIME"));
        oDocument.setFieldValue("DOCUMENTID", sDocumentId);
        oDocument.setFieldValue("DOCSTATUS", JsonHelper.getValueAsString(
                _oJson, "DOCUMENT.DOCSTATUS.ID"));
        oDocument.setFieldValue("CHANNELID", JsonHelper.getValueAsString(
                _oJson, "DOCUMENT.DOCCHANNEL.ID"));
        oDocument.setFieldValue("DOCTITLE", JsonHelper.getValueAsString(_oJson,
                "DOCUMENT.DOCTITLE"));
        oDocument.setFieldValue("DOCPUBHTMLCON", JsonHelper.getValueAsString(
                _oJson, "DOCUMENT.DOCPUBHTMLCON"));
        oDocument.setFieldValue("DOCRELTIME", JsonHelper.getValueAsString(
                _oJson, "DOCUMENT.DOCRELTIME"));
        oDocument.setFieldValue("DOCFLAG", JsonHelper.getValueAsString(_oJson,
                "DOCUMENT.DOCFLAG"));
        oDocument.setFieldValue("CRUSER", JsonHelper.getValueAsString(_oJson,
                "DOCUMENT.CRUSER"));
        oDocument.setFieldValue("CRTIME", JsonHelper.getValueAsString(_oJson,
                "DOCUMENT.CRTIME"));
        oDocument.setFieldValue("DOCPUBTIME", JsonHelper.getValueAsString(
                _oJson, "DOCUMENT.DOCPUBTIME"));
        oDocument.setFieldValue("DOCCONTENT", JsonHelper.getValueAsString(
                _oJson, "DOCUMENT.DOCCONTENT"));
        return oDocument;
    }

    /**
     * @return the appendix
     */
    public List getAppendix() {
        return m_lstAppendix;
    }

    /**
     * 增加一个附件
     * 
     * @param nAppFlag
     * @param _oAppendix
     * @return
     */
    public WAppendix addAppendix(int nAppFlag, Map _oAppendix) {
        WAppendix oWAppendix = new WAppendix();
        oWAppendix.setAppFlag(nAppFlag);
        if (_oAppendix != null) {
            oWAppendix.setAppendixMap(_oAppendix);
        }
        m_lstAppendix.add(oWAppendix);
        return oWAppendix;
    }

    /**
     * 增加一个附件，需要上传文件，未指定附加信息
     * 
     * @param nAppFlag
     * @param sFileName
     * @return
     */
    public WAppendix addAppendix(int nAppFlag, String sFileName) {
        return addAppendix(nAppFlag, sFileName, null);
    }

    /**
     * 增加一个附件，需要上传文件，并指定了附件其他信息
     * 
     * @param nAppFlag
     * @param sFileName
     * @param oAppendixMore
     * @return
     */
    public WAppendix addAppendix(int nAppFlag, String sFileName,
            Map oAppendixMore) {
        WAppendix oWAppendix = new WAppendix(sFileName);
        oWAppendix.setAppFlag(nAppFlag);
        if (oAppendixMore != null) {
            oWAppendix.setAppendixMap(oAppendixMore);
        }
        m_lstAppendix.add(oWAppendix);
        return oWAppendix;
    }

    /**
     * 上传所有需要上传的附件
     * 
     */
    public void uploadAllAppendixs() {
        for (Iterator iter = m_lstAppendix.iterator(); iter.hasNext();) {
            WAppendix oAppendix = (WAppendix) iter.next();
            oAppendix.upload();
        }
    }

    public String getAppendixsXML(int nAppFlag) {
        ArrayList lstTempAppendixs = new ArrayList();
        for (Iterator iter = m_lstAppendix.iterator(); iter.hasNext();) {
            WAppendix oAppendix = (WAppendix) iter.next();
            if (oAppendix.getAppFlag() == nAppFlag) {
                lstTempAppendixs.add(oAppendix);
            }
        }
        return getAppendixsXML(lstTempAppendixs);
    }

    public static String getAppendixsXML(ArrayList _lstAppendixs) {
        StringBuffer sbResult = new StringBuffer();
        sbResult.append("<OBJECTS>");
        for (Iterator iter = _lstAppendixs.iterator(); iter.hasNext();) {
            WAppendix oAppendix = (WAppendix) iter.next();
            sbResult.append(oAppendix.toObjectXml());
        }
        sbResult.append("</OBJECTS>");
        return sbResult.toString();
    }

    public String getRelationsXML() {
        StringBuffer sbResult = new StringBuffer();
        sbResult.append("<OBJECTS>");
        for (Iterator iter = m_lstRelations.iterator(); iter.hasNext();) {
            WRelation oRelation = (WRelation) iter.next();
            sbResult.append(oRelation.toObjectXml());
        }
        sbResult.append("</OBJECTS>");
        return sbResult.toString();
    }

    /**
     * @return the relations
     */
    public List getRelations() {
        return m_lstRelations;
    }

    public WRelation addRelation(int RelDocId) {
        WRelation oRelation = new WRelation();
        oRelation.setFieldValue("RelDocId", String.valueOf(RelDocId));
        oRelation.setFieldValue("ID", "0");
        m_lstRelations.add(oRelation);
        return oRelation;
    }

    /**
     * @return the documentBody
     */
    public Map getDocumentBody() {
        return m_oDocumentBody;
    }

    public void setFieldValue(String _sFieldName, Object _oFieldValue) {
        m_oDocumentBody.put(_sFieldName.toUpperCase(), _oFieldValue.toString());
    }

    public String getFieldValue(String _sFieldName) {
        if (m_oDocumentBody.get(_sFieldName.toUpperCase()) == null)
            return null;
        return m_oDocumentBody.get(_sFieldName.toUpperCase()).toString();
    }

    /**
     * @return the quoteToChannelIds
     */
    public String getQuoteToChannelIds() {
        return m_sQuoteToChannelIds;
    }

    /**
     * @param _quoteToChannelIds
     *            the quoteToChannelIds to set
     */
    public void setQuoteToChannelIds(String _quoteToChannelIds) {
        m_sQuoteToChannelIds = _quoteToChannelIds;
    }
}
