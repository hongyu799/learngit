/**
 * 2008-1-30
 */
package com.trs.web2frame.util;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.trs.web2frame.dispatch.DispatchConvertException;

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

public class XmlHelper {

    /**
     * 将一个xml格式的字符串转换成dom4j的Document对象
     * 
     * @param _responseText
     * @return
     */
    public static Document parse2XML(String _responseText)
            throws DispatchConvertException {
        if (_responseText == null)
            return null;
        DispatchConvertException _ex_ = null;
        Document document = null;
        InputStreamReader reader = null;
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(_responseText.getBytes("UTF-8"));
            reader = new InputStreamReader(bis, "UTF-8");
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(reader);
        } catch (Exception e) {
            _ex_ = new DispatchConvertException(e.getMessage());
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (Exception e) {
                    // Ignore
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    // Ignore
                }
            }
        }
        if (_ex_ != null) {
            throw _ex_;
        }
        return document;
    }

    /**
     * 将一个dom4j的Document对象转换成Map对象
     * 
     * @param _oXmlDocument
     * @return
     * @throws DispatchConvertException
     */
    public static Map parseXml2Json(Document _oXmlDocument)
            throws DispatchConvertException {
        Map mpResult = new HashMap();
        Element oRootElement = _oXmlDocument.getRootElement();
        mpResult.put(oRootElement.getName().toUpperCase(),
                parseElement2Map(oRootElement));
        return mpResult;
    }

    /**
     * 将一个dom4j的Element对象转换成Map对象
     * 
     * @param _oElement
     * @return
     */
    private static Map parseElement2Map(Element _oElement) {
        Map mpResult = new HashMap();
        List oAttributes = _oElement.attributes();
        for (Iterator iter = oAttributes.iterator(); iter.hasNext();) {
            Attribute oAttr = (Attribute) iter.next();
            mpResult.put(oAttr.getName().toUpperCase(), oAttr.getValue());
        }
        List childElements = _oElement.elements();
        if (childElements.size() == 0) {
            mpResult.put("NODEVALUE", _oElement.getTextTrim());
        } else {
            for (Iterator iter = childElements.iterator(); iter.hasNext();) {
                Element oChildElement = (Element) iter.next();
                String sChildEleName = oChildElement.getName().toUpperCase();
                Object oTmpValue = mpResult.get(sChildEleName);
                if (oTmpValue != null) {
                    List lstTmpEnums = null;
                    if (oTmpValue instanceof List) {
                        lstTmpEnums = (List) oTmpValue;
                    } else {
                        lstTmpEnums = new ArrayList();
                        lstTmpEnums.add(oTmpValue);
                        mpResult.put(sChildEleName, lstTmpEnums);
                    }
                    lstTmpEnums.add(parseElement2Map(oChildElement));
                } else {
                    mpResult
                            .put(sChildEleName, parseElement2Map(oChildElement));
                }
            }
        }
        return mpResult;
    }

    public static void main(String[] args) {
        String sXml = "<aaa bbb=\"ccc\"><ddd>222</ddd><ccc>111</ccc><eee><fff>3333</fff><ggg>4444</ggg></eee></aaa>";
        Document oDocument = parse2XML(sXml);
        Map oResult = parseXml2Json(oDocument);
        System.out.println("map:" + oResult);
    }
}
