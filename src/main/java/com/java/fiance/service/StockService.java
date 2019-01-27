package com.java.fiance.service;

import com.java.fiance.entity.Stock;
import com.java.fiance.entity.StockChosen;
import com.java.fiance.entity.StockPredict;
import com.java.fiance.entity.StockSpell;

import java.util.List;

/**
 * @author wuxincheng
 */
public interface StockService {
    /**
     * 通过用户openid获得自选股列表
     * @param userId
     * @return
     */
    List<Stock> getStockByUserId(String userId);

    /**
     * 用户添加自选股
     * @param userId
     * @param code
     * @param name
     * @return
     */
    boolean addStock(String userId, String code, String name);

    /**
     * 用户删除自选股
     * @param userId
     * @param code
     * @return
     */
    boolean deleteStock(String userId, String code);

    /**
     * 获得股票访问top20的股票
     * @param userId
     * @return
     */
    List<StockChosen> getStocksTop20(String userId);

    /**
     * 获得预测结果
     * @param code
     * @return
     */
    StockPredict getStockPredict(String code);

    /**
     * 获得所有股票信息
     * @return
     */
    List<StockSpell> getAllStocks();

    /**
     * 为所有股票配置拼音首字母
     * @return
     */
//    int updateAllStocks();

    /**
     * 判断股票是否为用户自选股
     * @param userId
     * @param code
     * @return
     */
    boolean checkStock(String userId, String code);
}