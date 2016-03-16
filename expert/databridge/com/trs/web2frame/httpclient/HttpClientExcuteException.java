/**
 * Created:         2006-3-28 10:34:42
 * Last Modified:   2006-3-28/2006-3-28
 * Description:
 *      class ResponseOutputException
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

public class HttpClientExcuteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 300638738760732809L;

	public HttpClientExcuteException() {
		super();
	}

	public HttpClientExcuteException(String message) {
		super(message);
	}

	public HttpClientExcuteException(Throwable cause) {
		super(cause);
	}

	public HttpClientExcuteException(String message, Throwable cause) {
		super(message, cause);
	}

}
