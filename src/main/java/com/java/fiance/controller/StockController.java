package com.java.fiance.controller;

import com.java.fiance.service.StockService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author wuxincheng
 */
@RestController
public class StockController {

    @Resource
    private StockService stockService;

    /**
     * 通过用户openid获得自选股列表
     * @param userId
     * @return
     */
    @GetMapping("/getStockById")
    public Object getStockById(@RequestParam("userId") String userId) {
        Object stockList = stockService.getStockByUserId(userId);
        return stockList;
    }

    /**
     * 通过用户userId以及股票代码取消自选
     * @param userId
     * @param code
     * @return
     */
    @GetMapping("/deleteStock")
    public Object deleteStock(@RequestParam("userId") String userId,
                              @RequestParam("code") String code) {

        boolean fa = stockService.deleteStock(userId, code);
        String result = "取消自选成功";
        if (!fa) {
            result = "取消自选失败";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return jsonObject;
    }

    /**
     * 用户添加自选股
     * @param userId
     * @param code
     * @param name
     * @return
     */
    @GetMapping("/addStock")
    public Object addStock(@RequestParam("userId") String userId,
                           @RequestParam("code") String code,
                           @RequestParam("name") String name) {
        String result = "添加自选成功";
        boolean fa = stockService.addStock(userId, code, name);
        if (!fa) {
            result = "添加自选失败!";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return jsonObject;

    }

    /**
     * 获得访问次数top20的股票并判断是否为用户自选股
     * @param userId
     * @return
     */
    @GetMapping("/getStockTop20")
    public Object getStockTop20(@RequestParam("userId") String userId) {
        Object list =  stockService.getStocksTop20(userId);
        return list;
    }

    /**
     * 获得股票预测数据
     * @param code
     * @return
     */
    @GetMapping("/getStockPredict")
    public Object getStockPredict(@RequestParam("code")String code) {
        return stockService.getStockPredict(code);
    }

    /**
     * 获取所有股票的列表
     * @return
     */
    @GetMapping("/getAllStocks")
    public Object getAllStocks() {
        Object stocks =  stockService.getAllStocks();
        return stocks;
    }

    /**
     * 判断股票是否为用户自选股
     * @param userId
     * @param code
     * @return
     */
    @GetMapping("/checkStock")
    public Object checkStock(@RequestParam("userId") String userId,
                             @RequestParam("code") String code) {
        boolean result = stockService.checkStock(userId, code);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return jsonObject;
    }

    /**
     * 更新数据库拼音字段
     * @return
     */
    @GetMapping("/updateAllStocks")
    public Object updateAllStocks() {
        return stockService.updateAllStocks();
    }



}
