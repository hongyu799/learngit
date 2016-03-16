/**
 * 2008-2-14
 */
package com.trs.web2frame;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.trs.infra.util.Loader;

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

public class ServiceConfig {
    public static String WCM_HOST_URL = "http://192.9.200.97:1234/wcm";

    public static String WCM_SERVICE_URL = WCM_HOST_URL + "/govcenter.do";

    public static String WCM_UPLOAD_FILE_URL = WCM_HOST_URL
            + "/govfileuploader.do";

    public static final Map m_ServiceConfigMap = new HashMap();

    public final static String WCM_SERVICE_CHARSET = "UTF-8";

    public final static String INI_FILE_NAME = "wcmservicecaller.ini";
    static {
        init();
    }

    private static void init() {
        URL url = null;
        try {
            url = Loader.getResource(INI_FILE_NAME);
        } catch (Exception ex) {
            url = null;
        }
        if (url != null) {
            String sFileName = url.getFile();
            loadProps(sFileName, "GBK");
            WCM_HOST_URL = getProperty("WCM_HOST_URL");
            WCM_SERVICE_URL = WCM_HOST_URL + getProperty("WCM_CENTER_DO");
            WCM_UPLOAD_FILE_URL = WCM_HOST_URL
                    + getProperty("WCM_UPLOADFILE_DO");
        }
    }

    public static String getProperty(String _sPropName) {
        return (String) m_ServiceConfigMap.get(_sPropName.toUpperCase().trim());
    }

    private static Map loadProps(String _sFileName, String _sEncoding) {
        String sLine = null;
        FileInputStream fis = null;
        BufferedReader buffReader = null;
        if (_sEncoding == null) {
            _sEncoding = "GBK";
        }

        m_ServiceConfigMap.clear();
        try {
            fis = new FileInputStream(_sFileName);
            buffReader = new BufferedReader(new InputStreamReader(fis,
                    _sEncoding));
            while ((sLine = buffReader.readLine()) != null) {
                sLine = sLine.trim();
                int len = sLine.length();
                if (len == 0) {
                    continue;
                }
                char firstChar = sLine.charAt(0);
                if ((firstChar == '#') || (firstChar == '!')
                        || (firstChar == '=')) {
                    continue;
                }
                // else
                int nPos = sLine.indexOf("=");
                if (nPos != -1) {
                    String key = sLine.substring(0, nPos);
                    String val = sLine.substring(nPos + 1, len);
                    m_ServiceConfigMap.put(key.toUpperCase().trim(), val.trim());
                }
            }// end while
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return m_ServiceConfigMap;
    }

}
