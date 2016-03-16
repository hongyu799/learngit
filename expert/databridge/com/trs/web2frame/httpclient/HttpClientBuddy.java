/**
 * Created:         2006-6-19 17:38:28
 * Last Modified:   2006-6-19/2006-6-19
 * Description:
 *      class HttpClientBuddy
 */
package com.trs.web2frame.httpclient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;

/**
 * Title: TRS 内容协作平台（TRS WCM 6.0）<BR>
 * Description:<BR>
 * TODO<BR>
 * Copyright: Copyright (c) 2005-2006 TRS信息技术有限公司<BR>
 * Company: TRS信息技术有限公司(www.trs.com.cn)<BR>
 * 
 * @author TRS信息技术有限公司
 * @version 1.0
 */

public class HttpClientBuddy {
    public static final String DEFAULT_CONTENT_ENCODING = "UTF-8";

    /**
     * 向wcm发送数据时的流写编码
     */
    public static final String SERVICE_REQEUST_ENCODING = "ISO-8859-1";

    protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger
            .getLogger(HttpClientBuddy.class);

    private HttpClient m_oHttpClient = null;

    private String m_sEncoding = "";

    private String[][] m_arrRequestHeaders = null;

    public HttpClientBuddy() {
        this(DEFAULT_CONTENT_ENCODING);
    }

    public HttpClientBuddy(String encoding) {
        this.m_sEncoding = encoding;
        m_oHttpClient = new HttpClient();
    }

    public HttpClientBuddy(String encoding, String[][] _arrRequestHeaders) {
        this.m_sEncoding = encoding;
        m_oHttpClient = new HttpClient();
        m_arrRequestHeaders = _arrRequestHeaders;
    }

    /**
     * 以POST方式发送请求，获取返回对象
     * 
     * @param _sPostUri
     * @param sContent
     * @return
     * @throws HttpClientExcuteException
     * @throws UnsupportedEncodingException
     * @throws URIException
     * @throws HttpClientRenderException
     */
    public ResponseBuddy doPost(String _sPostUri, String sContent)
            throws HttpClientExcuteException, UnsupportedEncodingException,
            URIException, HttpClientRenderException {
        return this.doPost(_sPostUri, sContent.getBytes(this.m_sEncoding));
    }

    public ResponseBuddy updateFile(String _sPostUri, byte[] _fileContent)
            throws URIException, HttpClientExcuteException,
            HttpClientRenderException {

        validUrl(_sPostUri);

        // make post-method
        PostMethod post = new PostMethod(_sPostUri);
        prepare(post);
        post.setRequestHeader("Content-Type", "multipart/form-data");

        if (_fileContent != null) {
            ByteArrayRequestEntity entity = new ByteArrayRequestEntity(
                    _fileContent);
            post.setContentChunked(true);
            post.setRequestEntity(entity);
        }

        return renderResponse(post);
    }

    /**
     * 上传文件，获取返回对象
     * 
     * @param _sPostUri
     * @param _oUpdateFile
     * @return
     * @throws HttpClientExcuteException
     * @throws UnsupportedEncodingException
     * @throws URIException
     * @throws HttpClientRenderException
     * @throws FileNotFoundException
     */
    public ResponseBuddy updateFile(String _sPostUri, File _oUpdateFile)
            throws HttpClientExcuteException, UnsupportedEncodingException,
            URIException, HttpClientRenderException, FileNotFoundException {

        validUrl(_sPostUri);
        PostMethod filePost = new PostMethod(_sPostUri);
        prepare(filePost);
        filePost.getParams().setBooleanParameter(
                HttpMethodParams.USE_EXPECT_CONTINUE, true);
        Part[] parts = { new FilePart(_oUpdateFile.getName(), _oUpdateFile) };
        filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost
                .getParams()));
        m_oHttpClient.getHttpConnectionManager().getParams()
                .setConnectionTimeout(5000);

        return renderResponse(filePost);
    }

    /**
     * 以POST方式发送请求，获取返回对象
     * 
     * @param _sPostUri
     * @param _btContent
     * @return
     * @throws HttpClientExcuteException
     * @throws URIException
     * @throws HttpClientRenderException
     */
    public ResponseBuddy doPost(String _sPostUri, byte[] _btContent)
            throws HttpClientExcuteException, URIException,
            HttpClientRenderException {

        validUrl(_sPostUri);

        // make post-method
        PostMethod post = new PostMethod(_sPostUri);
        prepare(post);

        if (_btContent != null) {
            ByteArrayRequestEntity entity = new ByteArrayRequestEntity(
                    _btContent);
            post.setContentChunked(true);
            post.setRequestEntity(entity);
        }

        return renderResponse(post);
    }

    /**
     * 以POST方式发送请求，获取返回对象
     * 
     * @param _sPostUri
     * @param sContent
     * @param _bChunked
     * @return
     * @throws HttpClientExcuteException
     * @throws UnsupportedEncodingException
     * @throws URIException
     * @throws HttpClientRenderException
     */
    public ResponseBuddy doPost(String _sPostUri, String sContent,
            boolean _bChunked) throws HttpClientExcuteException,
            UnsupportedEncodingException, URIException,
            HttpClientRenderException {
        return this.doPost(_sPostUri, sContent.getBytes(this.m_sEncoding),
                _bChunked);
    }

    /**
     * 以POST方式发送请求，获取返回对象
     * 
     * @param _sPostUri
     * @param _btContent
     * @param _bChunked
     * @return
     * @throws HttpClientExcuteException
     * @throws URIException
     * @throws HttpClientRenderException
     */
    public ResponseBuddy doPost(String _sPostUri, byte[] _btContent,
            boolean _bChunked) throws HttpClientExcuteException, URIException,
            HttpClientRenderException {

        validUrl(_sPostUri);

        // make post-method
        PostMethod post = new PostMethod(_sPostUri);
        prepare(post);

        if (_btContent != null) {
            ByteArrayRequestEntity entity = new ByteArrayRequestEntity(
                    _btContent);
            post.setContentChunked(_bChunked);
            post.setRequestEntity(entity);
        }

        return renderResponse(post);
    }

    /**
     * @param httpMethod
     */
    private void prepare(HttpMethod httpMethod) {
        httpMethod.setRequestHeader("Content-Type", "text/xml;charset="
                + this.m_sEncoding);
        if (m_arrRequestHeaders != null) {
            for (int i = 0; i < m_arrRequestHeaders.length; i++) {
                httpMethod.setRequestHeader(m_arrRequestHeaders[i][0],
                        m_arrRequestHeaders[i][1]);
            }
        }
    }

    /**
     * @param _sPostUri
     * @param httpMethod
     * @return
     * @throws URIException
     * @throws Exception
     */
    private ResponseBuddy renderResponse(HttpMethod httpMethod)
            throws HttpClientExcuteException, HttpClientRenderException,
            URIException {
        // render
        String _sMethodUri = httpMethod.getURI().getURI();
        int httpResult = 0;
        try {
            httpResult = m_oHttpClient.executeMethod(httpMethod);
            return new ResponseBuddy(httpMethod);
        } catch (Exception ex) {
            httpMethod.releaseConnection();
            if (ex instanceof HttpClientExcuteException) {
                throw (HttpClientExcuteException) ex;
            }
            // else
            throw new HttpClientExcuteException("向目标地址[" + _sMethodUri
                    + "]提交数据时失败！(response编号[" + httpResult + "])", ex);
        } finally {
            httpMethod.releaseConnection();
        }
    }

    /**
     * 以GET方式发送请求，获取返回对象
     * 
     * @param _sPostUri
     * @param _sQueryString
     * @return
     * @throws URIException
     * @throws HttpClientExcuteException
     * @throws HttpClientRenderException
     */
    public ResponseBuddy doGet(String _sPostUri, String _sQueryString)
            throws URIException, HttpClientExcuteException,
            HttpClientRenderException {

        validUrl(_sPostUri);

        // make get-method
        GetMethod get = new GetMethod(_sPostUri);
        prepare(get);
        if (_sQueryString != null) {
            get.setQueryString(_sQueryString);
        }
        // render
        return renderResponse(get);
    }

    /**
     * @param _sPostUri
     */
    private void validUrl(String _sPostUri) {
        URL oUrl = null;
        try {
            oUrl = new URL(_sPostUri);
        } catch (MalformedURLException ex) {
            throw new HttpClientRenderException("不是合法的HTTP请求的目标地址[" + _sPostUri
                    + "]", ex);
        }

        if (oUrl.getProtocol().equalsIgnoreCase("https")) {
            Protocol.registerProtocol("https", new Protocol("https",
                    new MySSLSocketFactory(), 443));
        }
    }
}
