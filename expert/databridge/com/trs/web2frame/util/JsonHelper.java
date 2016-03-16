/**
 * 2008-1-30
 */
package com.trs.web2frame.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;

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

public class JsonHelper {
    public static Document parseJson2Xml(Map _oJson) {
        // TODO
        return null;
    }

    /**
     * 根据XPath返回Json对象中相应的Json对象
     * @param _oJson
     * @param _sXpath
     * @return
     */
    public static Map getJson(Map _oJson, String _sXpath) {
        String[] arrPaths = _sXpath.toUpperCase().split("\\.");
        Object tmpJson = _oJson;
        int nIndex = 0;
        for (nIndex = 0; nIndex < arrPaths.length; nIndex++) {
            if (tmpJson instanceof Map) {
                tmpJson = ((Map) tmpJson).get(arrPaths[nIndex]);
            } else {
                return null;
            }
        }
        if (tmpJson instanceof Map) {
            return (Map) tmpJson;
        }
        return null;
    }

    /**
     * 
     * @param _oJson
     * @param _sXpath
     * @return
     */
    public static String getValueAsString(Map _oJson, String _sXpath) {
        String[] arrPaths = _sXpath.toUpperCase().split("\\.");
        Object tmpJson = _oJson;
        int nIndex = 0;
        for (nIndex = 0; nIndex < arrPaths.length; nIndex++) {
            if (tmpJson instanceof Map) {
                tmpJson = ((Map) tmpJson).get(arrPaths[nIndex]);
            } else if (tmpJson instanceof List) {
                return "";
            } else {
                break;
            }
        }
        if (nIndex == arrPaths.length - 1) {
            if (tmpJson instanceof String) {
                return tmpJson.toString();
            }
        } else if (nIndex == arrPaths.length) {
            if (tmpJson instanceof Map) {
                tmpJson = ((Map) tmpJson).get("NODEVALUE");
                if (tmpJson instanceof String) {
                    return tmpJson.toString();
                }
            } else if (tmpJson instanceof String) {
                return tmpJson.toString();
            }
        }
        return "";
    }

    public static List getList(Map _oJson, String _sXpath) {
        String[] arrPaths = _sXpath.toUpperCase().split("\\.");
        Object tmpJson = _oJson;
        int nIndex = 0;
        for (nIndex = 0; nIndex < arrPaths.length; nIndex++) {
            if (tmpJson instanceof Map) {
                tmpJson = ((Map) tmpJson).get(arrPaths[nIndex]);
            } else if (tmpJson instanceof List) {
                break;
            } else {
                break;
            }
        }
        if (nIndex == arrPaths.length - 1 || nIndex == arrPaths.length) {
            if (tmpJson instanceof List) {
                return (List) tmpJson;
            } else if (tmpJson != null) {
                List lstResult = new ArrayList();
                lstResult.add(tmpJson);
                return lstResult;
            }
        }
        return null;
    }
}
