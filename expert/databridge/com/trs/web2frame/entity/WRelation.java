/**
 * 2008-2-2
 */
package com.trs.web2frame.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.trs.infra.util.CMyString;

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

public class WRelation {

    private Map m_mpRelation = null;

    public WRelation() {
        m_mpRelation = new HashMap();
    }

    public void setFieldValue(String sFieldName, String sFieldValue) {
        m_mpRelation.put(sFieldName, sFieldValue);
    }

    public String getFieldValue(String sFieldName) {
        return m_mpRelation.get(sFieldName).toString();
    }

    /**
     * @return
     */
    public String toObjectXml() {
        StringBuffer sbResult = new StringBuffer();
        sbResult.append("<OBJECT ");
        for (Iterator iter = m_mpRelation.keySet().iterator(); iter.hasNext();) {
            String sFieldName = (String) iter.next();
            String sFieldValue = CMyString.filterForXML(m_mpRelation.get(
                    sFieldName).toString());
            sbResult.append(sFieldName);
            sbResult.append("=\"");
            sbResult.append(sFieldValue);
            sbResult.append("\" ");
        }
        sbResult.append("/>");
        return sbResult.toString();
    }

    /**
     * @param _relation
     */
    public void setRelationMap(Map _relation) {
        m_mpRelation = _relation;
    }

}
