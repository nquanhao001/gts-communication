package com.nqh.communication.dubbo.api.impl;

import com.nqh.communication.dubbo.api.StockAPI;
import com.nqh.communication.dubbo.api.dto.StockParam;
import com.nqh.communication.service.StockService;

import javax.annotation.Resource;

/**
 * @author zhehan
 * @date 2018/1/5
 */
public class StockAPIImpl implements StockAPI {


    @Resource
    private StockService stockService;

    @Override
    public void addStock(StockParam param) {
        stockService.addStock(param);
    }
}
