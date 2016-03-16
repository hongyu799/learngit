/**
 * 2008-2-3
 */
package com.trs.web2frame.entity;

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

public class WInfoViewDocument extends WDocument {
    private int m_nInfoViewId = 0;

    private Map m_mpInfoViewData = null;

    public WInfoViewDocument(int _nInfoViewId) {
        super();
        m_nInfoViewId = _nInfoViewId;
    }

    public void setInfoViewFieldValue(String sFieldName, String sFieldValue) {
        m_mpInfoViewData.put(sFieldName, sFieldValue);
    }

    public String getInfoViewFieldValue(String sFieldName) {
        return m_mpInfoViewData.get(sFieldName).toString();
    }

    public void prepare() {
        this.setFieldValue("_INFOVIEWID_", String.valueOf(m_nInfoViewId));
        this.setFieldValue("_INFOVIEW_DATA_", this.toObjectXml());
    }

    public String toObjectXml() {
        StringBuffer sbResult = new StringBuffer();
        sbResult.append("<OBJECT ");
        for (Iterator iter = m_mpInfoViewData.keySet().iterator(); iter
                .hasNext();) {
            String sFieldName = (String) iter.next();
            String sFieldValue = CMyString.filterForXML(m_mpInfoViewData.get(
                    sFieldName).toString());
            sbResult.append(sFieldName);
            sbResult.append("=\"");
            sbResult.append(sFieldValue);
            sbResult.append("\" ");
        }
        sbResult.append("/>");
        return sbResult.toString();
    }
}
