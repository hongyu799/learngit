/**
 * 2008-1-28
 */
package com.trs.web2frame.domain;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trs.infra.util.CMyString;
import com.trs.web2frame.ServiceObject;
import com.trs.web2frame.WCMServiceCaller;
import com.trs.web2frame.Web2frameClientException;
import com.trs.web2frame.dispatch.Dispatch;
import com.trs.web2frame.entity.WAppendix;
import com.trs.web2frame.entity.WDocument;
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

public class DocumentMgr {
    public static final String FIELD_OBJECT_IDS = "ObjectIds";

    public static final String FIELD_DOC_ID = "DocId";

    public static final String FIELD_OBJECT_ID = "ObjectId";

    public static final String FIELD_DOCUMENT_ID = "DocumentId";

    public final static String ms_ServiceId = "wcm6_document";

    public final static int ms_OBJ_TYPE = 605;

    public static void doService(final WDocument _oDocument,
            final List _arrSequence) {
        if (_arrSequence.size() <= 0)
            return;
        String sMethodName = (String) _arrSequence.remove(0);
        try {
            Method oMethod = DocumentMgr.class.getMethod(sMethodName,
                    new Class[] { WDocument.class,
                            ICallbackEventHandler[].class });
            oMethod
                    .invoke(
                            null,
                            new Object[] {
                                    _oDocument,
                                    new ICallbackEventHandler[] { new ICallbackSuccessHandler() {
                                        public void onSuccess(Dispatch _dispatch)
                                                throws Web2frameClientException {
                                            doService(_oDocument, _arrSequence);
                                        }
                                    } } });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static WDocument[] query(int nChannelId, int nSiteId, Map _oMapQuery) {
        // TODO
        return null;
    }

    public static WDocument findById(int nDocId, int nChannelId, int nSiteId) {
        ServiceObject oServiceObject = new ServiceObject(ms_ServiceId,
                "findById");
        Map oPostData = new HashMap();
        oPostData.put(FIELD_OBJECT_ID, String.valueOf(nDocId));
        if (nChannelId > 0) {
            oPostData.put("ChannelId", String.valueOf(nChannelId));
        } else if (nSiteId > 0) {
            oPostData.put("SiteId", String.valueOf(nSiteId));
        }
        oServiceObject.setPostData(oPostData);
        Dispatch oDispatch = WCMServiceCaller.Call(oServiceObject, true);
        return oDispatch.toDocument();
    }

    public static boolean delete(String _sDocIds, int nChannelId, int nSiteId) {
        return delete(_sDocIds, nChannelId, nSiteId, true);
    }

    public static boolean delete(String _sDocIds, int nChannelId, int nSiteId,
            boolean bDrop) {
        ServiceObject oServiceObject = new ServiceObject(ms_ServiceId, "delete");
        Map oPostData = new HashMap();
        oPostData.put(FIELD_OBJECT_IDS, _sDocIds);
        if (nChannelId > 0) {
            oPostData.put("ChannelId", String.valueOf(nChannelId));
        } else if (nSiteId > 0) {
            oPostData.put("SiteId", String.valueOf(nSiteId));
        }
        oPostData.put("Drop", String.valueOf(bDrop));
        oServiceObject.setPostData(oPostData);
        Dispatch oDispatch = WCMServiceCaller.Call(oServiceObject, true);
        return !oDispatch.isFailure();
    }

    public static void saveAppendixs(WDocument _oDocument) {
        saveAppendixs(_oDocument, null);
    }

    public static void saveAppendixs(WDocument _oDocument,
            ICallbackEventHandler[] _arrHandlers) {
        _oDocument.uploadAllAppendixs();
        saveAppendixs(_oDocument, WAppendix.FLAG_DOCAPD, _arrHandlers);
        saveAppendixs(_oDocument, WAppendix.FLAG_DOCPIC, _arrHandlers);
        saveAppendixs(_oDocument, WAppendix.FLAG_LINK, _arrHandlers);
    }

    private static void saveAppendixs(WDocument _oDocument, int nAppendixType,
            ICallbackEventHandler[] _arrHandlers) {
        ServiceObject oServiceObject = new ServiceObject(ms_ServiceId,
                "saveAppendixes");
        Map oPostData = new HashMap();
        oPostData
                .put(FIELD_DOC_ID, _oDocument.getFieldValue(FIELD_DOCUMENT_ID));
        oPostData.put("AppendixType", String.valueOf(nAppendixType));
        oPostData.put("AppendixesXML", _oDocument
                .getAppendixsXML(nAppendixType));
        oServiceObject.setPostData(oPostData);
        oServiceObject.addEventHandlers(_arrHandlers);
        WCMServiceCaller.Call(oServiceObject, true);
    }

    public static void saveRelations(WDocument _oDocument) {
        saveRelations(_oDocument, null);
    }

    public static void saveRelations(WDocument _oDocument,
            ICallbackEventHandler[] _arrHandlers) {
        ServiceObject oServiceObject = new ServiceObject(ms_ServiceId,
                "saveRelation");
        Map oPostData = new HashMap();
        oPostData
                .put(FIELD_DOC_ID, _oDocument.getFieldValue(FIELD_DOCUMENT_ID));
        oPostData.put("RelationsXML", _oDocument.getRelationsXML());
        oServiceObject.setPostData(oPostData);
        oServiceObject.addEventHandlers(_arrHandlers);
        WCMServiceCaller.Call(oServiceObject, true);
    }

    public static int save(final WDocument _oDocument) {
        return save(_oDocument, null);
    }

    public static int save(final WDocument _oDocument,
            ICallbackEventHandler[] _arrHandlers) {
        ServiceObject oServiceObject = new ServiceObject(ms_ServiceId, "save");
        Map oPostData = _oDocument.getDocumentBody();
        oPostData.put(FIELD_OBJECT_ID, CMyString.showNull(_oDocument
                .getFieldValue(FIELD_DOCUMENT_ID), "0"));
        oServiceObject.setPostData(oPostData);
        oServiceObject
                .addEventHandlers(new ICallbackEventHandler[] { new ICallbackSuccessHandler() {
                    public void onSuccess(Dispatch _dispatch)
                            throws Web2frameClientException {
                        String sDocumentId = JsonHelper.getValueAsString(
                                _dispatch.getJson(), "result");
                        _oDocument
                                .setFieldValue(FIELD_DOCUMENT_ID, sDocumentId);
                    }
                } });
        oServiceObject.addEventHandlers(_arrHandlers);
        WCMServiceCaller.Call(oServiceObject, true);
        return Integer.parseInt(_oDocument.getFieldValue(FIELD_DOCUMENT_ID));
    }

    public static void quoteTo(final WDocument _oDocument) {
        quoteTo(_oDocument, null);
    }

    public static void quoteTo(final WDocument _oDocument,
            ICallbackEventHandler[] _arrHandlers) {
        ServiceObject oServiceObject = new ServiceObject(ms_ServiceId,
                "setQuote");
        Map oPostData = new HashMap();
        oPostData.put(FIELD_DOCUMENT_ID, _oDocument
                .getFieldValue(FIELD_DOCUMENT_ID));
        oPostData.put("FromChannelId", _oDocument.getFieldValue("ChannelId"));
        oPostData.put("ToChannelIds", _oDocument.getQuoteToChannelIds());
        oServiceObject.setPostData(oPostData);
        oServiceObject.addEventHandlers(_arrHandlers);
        if (_oDocument.getQuoteToChannelIds() == null) {
            oServiceObject.onSuccess(null);
        } else {
            WCMServiceCaller.Call(oServiceObject, true);
        }
    }
}
