/**
 * 2008-1-29
 */
package com.trs.web2frame;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.trs.web2frame.dispatch.Dispatch;
import com.trs.web2frame.eventhandler.ICallbackCompleteHandler;
import com.trs.web2frame.eventhandler.ICallbackEventHandler;
import com.trs.web2frame.eventhandler.ICallbackFailureHandler;
import com.trs.web2frame.eventhandler.ICallbackSuccessHandler;

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

public class ServiceObject {

    private String m_sServiceId;

    private String m_sMethodName;

    private ArrayList m_lstEventHandlers = null;

    private Map m_oPostData = null;

    public ServiceObject() {
        m_lstEventHandlers = new ArrayList();
        m_oPostData = new HashMap();
    }

    public ServiceObject(String _sServiceId, String _sMethodName) {
        this();
        m_sServiceId = _sServiceId;
        m_sMethodName = _sMethodName;
    }

    public String toQueryString() {
        try {
            return WCMServiceCaller.makePostParams(this.m_sServiceId,
                    this.m_sMethodName, this.m_oPostData);
        } catch (UnsupportedEncodingException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }

    public String toPostXml() {
        return WCMServiceCaller.makePostData(this.m_sServiceId,
                this.m_sMethodName, this.m_oPostData);
    }

    /**
     * @param _sParamName
     * 参数名称
     * @param _sParamValue
     * 参数值
     */
    public void setParameter(String _sParamName, String _sParamValue) {
        m_oPostData.put(_sParamName, _sParamValue);
    }

    /**
     * @param _sParamName
     * 参数名称
     * @param _nParamValue
     * 参数值
     */
    public void setParameter(String _sParamName, int _nParamValue) {
        m_oPostData.put(_sParamName, String.valueOf(_nParamValue));
    }

    public void setParameter(String _sParamName, Object _oParamValue) {
        m_oPostData.put(_sParamName, _oParamValue.toString());
    }

    /**
     * @return the listener
     */
    public ArrayList getEventHandlers() {
        return m_lstEventHandlers;
    }

    public void addEventHandler(ICallbackEventHandler _oCallListener) {
        m_lstEventHandlers.add(_oCallListener);
    }

    public void addEventHandlers(ICallbackEventHandler[] _oCallListeners) {
        if (_oCallListeners == null)
            return;
        for (int i = 0; i < _oCallListeners.length; i++) {
            m_lstEventHandlers.add(_oCallListeners[i]);
        }
    }

    /**
     * @param _lstListener
     *            the listener to set
     */
    void setEventHandler(ArrayList _lstListener) {
        m_lstEventHandlers = _lstListener;
    }

    /**
     * @return the postData
     */
    public Map getPostData() {
        return m_oPostData;
    }

    /**
     * @param _postData
     *            the postData to set
     */
    public void setPostData(Map _postData) {
        m_oPostData = _postData;
    }

    /**
     * @return the methodName
     */
    public String getMethodName() {
        return m_sMethodName;
    }

    /**
     * @param _methodName
     *            the methodName to set
     */
    public void setMethodName(String _methodName) {
        m_sMethodName = _methodName;
    }

    /**
     * @return the serviceId
     */
    public String getServiceId() {
        return m_sServiceId;
    }

    /**
     * @param _serviceId
     *            the serviceId to set
     */
    public void setServiceId(String _serviceId) {
        m_sServiceId = _serviceId;
    }

    /**
     * @param _oServiceObject
     * @param oResponseBuddy
     */
    public void onFailure(Dispatch oDispatch) {
        boolean bHandled = false;
        List lstEventHandlers = this.getEventHandlers();
        for (Iterator iter = lstEventHandlers.iterator(); iter.hasNext();) {
            ICallbackEventHandler oHandler = (ICallbackEventHandler) iter
                    .next();
            if (oHandler instanceof ICallbackFailureHandler) {
                ((ICallbackFailureHandler) oHandler).onFailure(oDispatch);
                bHandled = true;
            }
        }
        if (!bHandled) {
            WCMServiceCaller.CALLLBACK_FAILURE_HANDLER_DEFAULT
                    .onFailure(oDispatch);
        }
    }

    /**
     * @param _oServiceObject
     * @param oResponseBuddy
     */
    public void onSuccess(Dispatch oDispatch) {
        List lstEventHandlers = this.getEventHandlers();
        for (Iterator iter = lstEventHandlers.iterator(); iter.hasNext();) {
            ICallbackEventHandler oHandler = (ICallbackEventHandler) iter
                    .next();
            if (oHandler instanceof ICallbackSuccessHandler) {
                ((ICallbackSuccessHandler) oHandler).onSuccess(oDispatch);
            }
        }
    }

    /**
     * @param _oServiceObject
     * @param oResponseBuddy
     */
    public void onComplete(Dispatch oDispatch) {
        List lstEventHandlers = this.getEventHandlers();
        for (Iterator iter = lstEventHandlers.iterator(); iter.hasNext();) {
            ICallbackEventHandler oHandler = (ICallbackEventHandler) iter
                    .next();
            if (oHandler instanceof ICallbackCompleteHandler) {
                ((ICallbackCompleteHandler) oHandler).onComplete(oDispatch);
            }
        }
    }
}
