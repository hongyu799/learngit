/**
 * 2008-1-29
 */
package com.trs.web2frame.httpclient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.log4j.Logger;

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

public class ResponseBuddy {

    private static final String DEFAULT_CHARSET_XML = "UTF-8";

    private static final String DEFAULT_CHARSET_HTML = "GBK";

    static transient final Logger mLogger = Logger
            .getLogger(ResponseBuddy.class);

    static transient final Pattern PATTERM_CONTENT_TYPE = Pattern
            .compile("(?i).*charset\\s*=\\s*([^\\s]+)");

    static transient final Pattern PATTHERN_HTML_CONTENT_TYPE = Pattern
            .compile("(?i)<\\s*meta\\s*http-equiv=\\s*[\"]?\\s*content-type\\s*[\"]?\\s*content=\\s*[\"]?\\s*text/html;\\s*charset\\s*=\\s*([^\"\\s]+)[\"]?\\s*>");

    static transient final Pattern PATTHERN_XML_CONTENT_TYPE = Pattern
            .compile("(?i)<\\?xml.*encoding\\s*=\\s*\"\\s*([^\\\"\\\\s]+)\\s*\"\\s*\\?>");

    int m_nStatusCode = 0;

    byte[] m_barrBody = null;

    String m_sContentType = null;

    String m_sCharset = null;

    Map m_mpHeaders = null;

    public ResponseBuddy(HttpMethod oHttpMethod) throws IOException {
        super();
        this.setHeaders(oHttpMethod.getResponseHeaders());
        this.setStatusCode(oHttpMethod.getStatusCode());
        this.setBody(oHttpMethod.getResponseBody());
        this.m_sContentType = this.getHeader("Content-Type");
    }

    public void init() {
    }

    public InputStream getBodyAsStream() throws UnsupportedEncodingException {
        if (this.getStatusCode() > 400 || this.m_barrBody == null) {
            return null;
        }
        return new ByteArrayInputStream(this.m_barrBody);
    }

    public String getBodyAsString() {
        if (this.getStatusCode() > 400) {
            return null;
        }
        this.guessCharset();
        try {
            return new String(this.m_barrBody, this.m_sCharset);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 猜编码
     */
    private void guessCharset() {
        if (this.m_sCharset != null) {
            return;
        }
        if (this.getStatusCode() > 400) {
            this.m_sCharset = DEFAULT_CHARSET_HTML;
            return;
        }
        if (this.m_sContentType == null) {
            this.setContentType("text/HTML");
        }

        Matcher matchCharset = PATTERM_CONTENT_TYPE
                .matcher(this.m_sContentType);
        if (matchCharset.find()) {
            this.m_sCharset = matchCharset.group(1);
            mLogger.debug("charset in Content-Type:" + this.m_sCharset);
        }

        if (this.m_sCharset != null) {
            this.m_sCharset = this.m_sCharset.toUpperCase();
        } else {
            if (this.m_sContentType.trim().startsWith("text")
                    || this.m_sContentType.trim().startsWith("application/xml")) {
                String contentValue = new String(this.m_barrBody);
                if (this.m_sContentType.trim().startsWith("text/html")) {
                    matchCharset = PATTHERN_HTML_CONTENT_TYPE
                            .matcher(contentValue);
                    if (matchCharset.find()) {
                        this.m_sCharset = matchCharset.group(1);
                        mLogger.debug("charset in HTML META Content-Type:"
                                + m_sCharset);
                    }
                    if (this.m_sCharset == null) {
                        this.m_sCharset = DEFAULT_CHARSET_HTML;
                    }
                    this.m_sCharset = this.m_sCharset.toUpperCase();

                } else if (this.m_sContentType.trim().startsWith(
                        "application/xml")
                        || this.m_sContentType.trim().startsWith("text/xml")) {
                    matchCharset = PATTHERN_XML_CONTENT_TYPE
                            .matcher(contentValue);
                    if (matchCharset.find()) {
                        this.m_sCharset = matchCharset.group(1);
                        mLogger.debug("charset in XML encoding:" + m_sCharset);
                    }
                    if (this.m_sCharset == null) {
                        this.m_sCharset = DEFAULT_CHARSET_XML;
                    }
                    this.m_sCharset = this.m_sCharset.toUpperCase();
                } else {
                    this.m_sCharset = DEFAULT_CHARSET_HTML;
                }
                if (this.m_sCharset.equalsIgnoreCase("GB2312")) {
                    this.m_sCharset = DEFAULT_CHARSET_HTML;
                }
            } else {
                this.m_sCharset = "";
            }
        }
    }

    public void setBody(byte[] pContent) {
        this.m_barrBody = pContent;
    }

    public int getContentLength() {
        return Integer.parseInt(this.getHeader("Content-Length"));
    }

    public String getContentType() {
        return this.m_sContentType;
    }

    public void setContentType(String pContentType) {
        this.m_sContentType = pContentType;
    }

    public int getStatusCode() {
        return this.m_nStatusCode;
    }

    public void setStatusCode(int pResponseCode) {
        this.m_nStatusCode = pResponseCode;
    }

    public String getCharset() {
        this.guessCharset();
        return this.m_sCharset;
    }

    public Map getHeaders() {
        return this.m_mpHeaders;
    }

    public void setHeaders(Header[] headers) {
        this.m_mpHeaders = new HashMap();
        for (int i = 0; i < headers.length; i++) {
            Header oHeader = headers[i];
            String sHeaderName = oHeader.getName();
            String sHeaderValue = oHeader.getValue();
            List lstHeaders = (List) this.m_mpHeaders.get(sHeaderName);
            if (lstHeaders == null) {
                lstHeaders = new ArrayList();
                this.m_mpHeaders.put(sHeaderName, lstHeaders);
            }
            lstHeaders.add(sHeaderValue);
        }
    }

    public String getHeader(String _sName) {
        List oHeader = (List) this.m_mpHeaders.get(_sName);
        if (oHeader != null && !oHeader.isEmpty()) {
            return (String) oHeader.get(0);
        }
        return null;

    }
}
