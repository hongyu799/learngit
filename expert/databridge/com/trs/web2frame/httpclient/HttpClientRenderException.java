/**
 * Created:         2006-3-29 9:35:18
 * Last Modified:   2006-3-29/2006-3-29
 * Description:
 *      class NoSuchServiceException
 */
package com.trs.web2frame.httpclient;

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

public class HttpClientRenderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7514927287825910056L;

	/**
	 * 
	 */

	public HttpClientRenderException() {
		super();
	}

	public HttpClientRenderException(String message) {
		super(message);
	}

	public HttpClientRenderException(Throwable cause) {
		super(cause);
	}

	public HttpClientRenderException(String message, Throwable cause) {
		super(message, cause);
	}

}
