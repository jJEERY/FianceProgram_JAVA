package com.java.fiance.mapper;

import com.java.fiance.entity.Stock;
import com.java.fiance.entity.StockChosen;
import com.java.fiance.entity.StockPredict;
import com.java.fiance.entity.StockSpell;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuxincheng
 */
@Mapper
public interface StockMapper {

    /**
     * 通过用户openid获得自选股列表
     *
     * @param userId
     * @return
     */
    List<Stock> getStockById(@Param("userId") String userId);

    /**
     * 获得访问前20的股票数据
     *
     * @param updateTime
     * @return
     */
    List<StockChosen> getStocksTop20(@Param("updateTime") String updateTime);

    /**
     * 用户添加自选股
     *
     * @param userId
     * @param code
     * @param name
     * @return
     */
    int addStock(@Param("userId") String userId,
                 @Param("code") String code,
                 @Param("name") String name);

    /**
     * 用户取消自选股
     *
     * @param userId
     * @param code
     * @return
     */
    int deleteStock(@Param("userId") String userId,
                    @Param("code") String code);

    /**
     * 获取指定股票的预测结果
     *
     * @param dataTable
     * @return
     */
    StockPredict getStockPredict(@Param("dataTable") String dataTable);

    /**
     * 获得所有股票信息
     *
     * @return
     */
    List<StockSpell> getAllStocks();


    /**
     * 更新所有股票的拼音
     * @param stockSpells
     * @return
     */
    int updateAllStocks(List<StockSpell> stockSpells);

    /**
     * 判断股票是否为用户自选股
     * @param userId
     * @param code
     * @return
     */
    Stock checkStock(@Param("userId") String userId, @Param("code")String code);
}
