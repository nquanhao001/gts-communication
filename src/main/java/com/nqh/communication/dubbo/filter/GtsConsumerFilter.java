package com.nqh.communication.dubbo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.taobao.txc.common.TxcContext;

@Activate(group = Constants.CONSUMER)
public class GtsConsumerFilter
{
    private static final Logger log = LoggerFactory.getLogger(GtsConsumerFilter.class);

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException
    {
        Boolean isNeedTransGtsId = false;
        String gtsId = null;
        try
        {
            gtsId = TxcContext.getCurrentXid();
            if (null != gtsId && gtsId.length() > 0)
            {
                isNeedTransGtsId = true;
            }
        }
        catch (Throwable e)
        {
            // 防止没有引用gts jar
        }

        if (!isNeedTransGtsId)
        {
            return invoker.invoke(invocation);
        }
        else
        {
            log.debug("gtsId: {}", gtsId);
            invocation.getAttachments().put("xid", gtsId);
            return invoker.invoke(invocation);
        }
    }
}
