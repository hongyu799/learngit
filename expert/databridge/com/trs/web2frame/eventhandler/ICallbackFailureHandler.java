/**
 * 2008-1-29
 */
package com.trs.web2frame.eventhandler;

import com.trs.web2frame.Web2frameClientException;
import com.trs.web2frame.dispatch.Dispatch;

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

public interface ICallbackFailureHandler extends ICallbackEventHandler {
    public void onFailure(Dispatch oDispatch)
            throws Web2frameClientException;
}
