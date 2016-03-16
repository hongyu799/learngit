/**
 * 2008-1-29
 */
package com.trs.web2frame;

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

public class Web2frameClientException extends RuntimeException {

    private static final long serialVersionUID = 5174479536070849821L;

    public Web2frameClientException() {
        super();
    }

    public Web2frameClientException(String message) {
        super(message);
    }

    public Web2frameClientException(Throwable cause) {
        super(cause);
    }

    public Web2frameClientException(String message, Throwable cause) {
        super(message, cause);
    }

}
