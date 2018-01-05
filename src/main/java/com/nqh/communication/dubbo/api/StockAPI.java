package com.nqh.communication.dubbo.api;

import com.nqh.communication.dubbo.api.dto.StockParam;

/**
 * @author zhehan
 * @date 2018/1/5
 */
public interface StockAPI {



    void addStock(StockParam param);
}
