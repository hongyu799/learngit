/**
 * Created:         2006-10-12 15:24:30
 * Last Modified:   2006-10-12/2006-10-12
 * Description:
 *      class MySSLSocketFactory
 */
package com.trs.web2frame.httpclient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpClientError;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;

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

public class MySSLSocketFactory implements ProtocolSocketFactory {
	private SSLContext sslcontext = null;

	public MySSLSocketFactory() {
	}

	private static SSLContext createEasySSLContext() {
		try {
			SSLContext context = SSLContext.getInstance("SSL");
			context.init(null, new TrustManager[] { new MyX509TrustManager() },
					new java.security.SecureRandom());
			return context;
		} catch (Exception e) {
			throw new HttpClientError(e.toString());
		}
	}

	private SSLContext getSSLContext() {
		if (this.sslcontext == null)
			this.sslcontext = createEasySSLContext();
		return this.sslcontext;
	}

	public Socket createSocket(String host, int port, InetAddress clientHost,
			int clientPort) throws IOException, UnknownHostException {
		return getSSLContext().getSocketFactory().createSocket(host, port,
				clientHost, clientPort);
	}

	public Socket createSocket(String host, int port, InetAddress localAddress,
			int localPort, HttpConnectionParams params) throws IOException,
			UnknownHostException {
		return createSocket(host, port, localAddress, localPort);
	}

	public Socket createSocket(String host, int port) throws IOException,
			UnknownHostException {
		return getSSLContext().getSocketFactory().createSocket(host, port);
	}

	public boolean equals(Object obj) {
		return ((obj != null) && obj.getClass()
				.equals(MySSLSocketFactory.class));
	}

	public int hashCode() {
		return MySSLSocketFactory.class.hashCode();
	}

	public static class MyX509TrustManager implements X509TrustManager {
		public MyX509TrustManager() {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType) {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) {
		}
	}
}
