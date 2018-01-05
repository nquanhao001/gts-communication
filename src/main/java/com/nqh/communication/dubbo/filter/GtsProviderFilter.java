package com.nqh.communication.dubbo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.taobao.txc.common.TxcContext;

@Activate(group = Constants.PROVIDER)
public class GtsProviderFilter
{
    private static final Logger log = LoggerFactory.getLogger(GtsProviderFilter.class);

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException
    {
        String xid = invocation.getAttachment("xid");
        if (null == xid || xid.length() == 0)
        {
            return invoker.invoke(invocation);
        }

        try
        {
            log.debug("xid: {}", xid);
            try
            {
                TxcContext.bind(xid, null); //注意在这里就设置了xid
            }
            catch (Throwable e)
            {
            }
            return invoker.invoke(invocation);
        }
        finally
        {
            TxcContext.unbind();
        }
    }
}
