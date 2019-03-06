package com.java.fiance.service.serviceImpl;

import com.java.fiance.entity.Stock;
import com.java.fiance.entity.StockChosen;
import com.java.fiance.entity.StockPredict;
import com.java.fiance.entity.StockSpell;
import com.java.fiance.mapper.StockMapper;
import com.java.fiance.service.StockService;
import com.java.fiance.utils.Utils;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * @author wuxincheng
 */
@Service("StockService")
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    private static final Logger logger = LoggerFactory.getLogger("StockServiceImpl");

    @Override
    public Object getStockByUserId(String userId) {
        List<Stock> stocks = stockMapper.getStockById(userId);
        if (stocks == null) {
            logger.error("getStockByUserId.getStockById.fail");
            return "暂无自选股";
        }
        return stocks;
    }

    @Override
    public boolean addStock(String userId, String code, String name) {
        int result = stockMapper.addStock(userId, code, name);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteStock(String userId, String code) {
        int i = stockMapper.deleteStock(userId, code);
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object getStocksTop20(String userId) {
        String updateTime = "update_last_time";
        //当天如果是大于10号
        int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        logger.debug("getStocksTop20.today->{}", today);
        int ten = 10;
        if (today > ten) {
            updateTime = "update_this_time";
        }
        //获得前20的股票，默认全部非自选
        List<StockChosen> stocksTop20 = stockMapper.getStocksTop20(updateTime);
        logger.debug("getStocksTop20.stocksTop20->{}", stocksTop20);
        if (stocksTop20 == null) {
            logger.error("getStocksTop20.getStocksTop20.Fail");
            return "获取股票数据出错，请重试!";
        }

        //获得用户自选股
        List<Stock> stocks = stockMapper.getStockById(userId);
        logger.debug("getStocksTop20.stocks->{}", stocks);
        if (stocks == null) {
            logger.error("getStocksTop.getStocksByUserId.Fail");
            return "获取用户自选股失败!";
        }
        //使用map和循环获得top20的自选股，不需要修改排列顺序
        HashMap<Integer, StockChosen> hashMap = new HashMap<>(20);
        int i = 0;
        for (Stock stock : stocks) {
            StockChosen stockChosen = new StockChosen();
            BeanUtils.copyProperties(stock, stockChosen);
            hashMap.put(i++, stockChosen);
        }
        for (StockChosen stockChosen : stocksTop20) {
            if (hashMap.containsValue(stockChosen)) {
                stockChosen.setChosen(true);
            }
        }
        return stocksTop20;
    }

    @Override
    public Object getStockPredict(String code) {
        String dataTable = "predict_" + code;
        StockPredict stockPredict;
        try {
            stockPredict = stockMapper.getStockPredict(dataTable);
        }catch (Exception e) {
            logger.error("getStockPredict.getStockPredict.fail");
            return "";
        }
        String now = stockPredict.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String[] nextDay = new String[5];
        try {
            Date date = simpleDateFormat.parse(now);
            Calendar cld = Calendar.getInstance();
            cld.setTime(date);
            boolean isJudge = false;

            for (int i = 0; i < 5; i++) {
                if (!isJudge && cld.get(Calendar.DAY_OF_WEEK) == 6) {
                    isJudge = true;
                    cld.add(Calendar.DATE, 3);
                } else {
                    cld.add(Calendar.DATE, 1);
                }
                date = cld.getTime();
                nextDay[i] = simpleDateFormat.format(date);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        dataSet.setValue(stockPredict.getHigh1(), "Highest", nextDay[0]);
        dataSet.setValue(stockPredict.getHigh2(), "Highest", nextDay[1]);
        dataSet.setValue(stockPredict.getHigh3(), "Highest", nextDay[2]);
        dataSet.setValue(stockPredict.getHigh4(), "Highest", nextDay[3]);
        dataSet.setValue(stockPredict.getHigh5(), "Highest", nextDay[4]);

        dataSet.setValue(stockPredict.getLow1(), "Lowest", nextDay[0]);
        dataSet.setValue(stockPredict.getLow2(), "Lowest", nextDay[1]);
        dataSet.setValue(stockPredict.getLow3(), "Lowest", nextDay[2]);
        dataSet.setValue(stockPredict.getLow4(), "Lowest", nextDay[3]);
        dataSet.setValue(stockPredict.getLow5(), "Lowest", nextDay[4]);
        JFreeChart chart = ChartFactory.createLineChart(
                "", "", "", dataSet,
                PlotOrientation.VERTICAL, true, false, false);
        Color color = new Color(16, 19, 24);
        chart.setBackgroundPaint(color);
        chart.setBorderVisible(false);
        CategoryPlot plot = chart.getCategoryPlot();
        //图片背景透明
        plot.setBackgroundAlpha(0.0f);
        //y轴标记线不可见
        plot.setRangeGridlinesVisible(false);
        //竖轴对象
        ValueAxis valueAxis = plot.getRangeAxis();
        valueAxis.setTickLabelPaint(ChartColor.WHITE);
        //横轴对象
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        categoryAxis.setTickLabelPaint(ChartColor.white);
        //获得折线对象
        LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer) plot.getRenderer();
        //加粗
        lineAndShapeRenderer.setSeriesStroke(0, new BasicStroke(5F));
        lineAndShapeRenderer.setSeriesStroke(1, new BasicStroke(5F));
        //设置颜色
        color = new Color(0, 128, 0);
        lineAndShapeRenderer.setSeriesFillPaint(0, color);

        try {
            NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
            numberAxis.setAutoRangeIncludesZero(false);
            String picture = "picture/" + stockPredict.getCode() + ".png";
            OutputStream os = new FileOutputStream(picture);
            ChartUtilities.writeChartAsPNG(os, chart, 640, 480);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockPredict;
    }

    @Override
    public Object getAllStocks() {
        List<StockSpell> list = stockMapper.getAllStocks();
        if (list == null) {
            logger.error("getAllStocks.getAllStocks.fail");
            return "查询股票数据失败!";
        }
        return list;
    }

    @Override
    public boolean checkStock(String userId, String code) {
        Stock stock = stockMapper.checkStock(userId, code);
        if (stock == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int updateAllStocks() {
        //获得修改前的股票
        List<StockSpell> stocks = stockMapper.getAllStocks();
        List<StockSpell> stockSpells = new ArrayList<>();
        //逐个获得拼音首字母
        for (StockSpell stock : stocks) {
            StockSpell stockSpell = new StockSpell();

            BeanUtils.copyProperties(stock, stockSpell);
            if (Objects.isNull(stock.getSpell())) {
                logger.info("updateAllStocks.updateStock.spell -> " + stock);
            }
            String spell = stock.getSpell() == null ? Utils.getSpellUpper(stock.getName()) : stock.getSpell();
            stockSpell.setSpell(spell);
            stockSpells.add(stockSpell);
        }
        return stockMapper.updateAllStocks(stockSpells);
    }


}

