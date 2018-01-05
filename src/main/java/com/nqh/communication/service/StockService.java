package com.nqh.communication.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.nqh.communication.dubbo.api.dto.StockParam;
import com.taobao.txc.client.aop.annotation.TxcTransaction;

/**
 * @author zhehan
 * @date 2018/1/5
 */
@Service
public class StockService {


    @TxcTransaction
    public void addStock(StockParam param){

    }
}
