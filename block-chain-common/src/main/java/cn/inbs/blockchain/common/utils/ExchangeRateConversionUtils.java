package cn.inbs.blockchain.common.utils;

import cn.inbs.blockchain.common.constants.CurrencyConstants;
import cn.inbs.blockchain.common.exception.PlatformException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @Description: 汇率转化工具类
 * @Package: cn.inbs.blockchain.common.utils
 * @ClassName: ExchangeRateConversionUtils
 * @Author: zhangmingyang
 * @CreateDate: 2018/8/2 14:23
 * @Version: 1.0
 */
public class ExchangeRateConversionUtils {
    private static Logger logger = LoggerFactory.getLogger(ExchangeRateConversionUtils.class);

    private static final String PARAM_APPEND_SYM = "?";
    private static final String PARAM_AND_SYM = "&";
    private static final String PARAM_EQUAL_SYM = "=";
    private static final String QUERY_EXCHANGE_RATE_URL = "https://min-api.cryptocompare.com/data/histominute";
    private static final int RESERVED_NUM = 10;

    public static BigDecimal getExchangeRate(String fromCurrency, String toCurrency, BigDecimal ret2Eth) {
        BigDecimal eth2From = getExchangeRateByThird(CurrencyConstants.CURRENCY_ETH, fromCurrency, ret2Eth);
        BigDecimal eth2to = getExchangeRateByThird(CurrencyConstants.CURRENCY_ETH, toCurrency, ret2Eth);

        if (null != eth2From && null != eth2to) {
            return eth2to.divide(eth2From, RESERVED_NUM, BigDecimal.ROUND_HALF_UP);
        } else {
            throw new PlatformException("币种转换失败");
        }

    }

    /**
     * 调用第三方查询
     *
     * @param fromCurrency
     * @param toCurrency
     * @return
     */
    private static BigDecimal getExchangeRateByThird(String fromCurrency, String toCurrency, BigDecimal ret2Eth) {
        if (CurrencyConstants.CURRENCY_ETH.equals(fromCurrency) && CurrencyConstants.CURRENCY_RET.equals(toCurrency)) {
            return new BigDecimal(1).divide(ret2Eth, RESERVED_NUM, BigDecimal.ROUND_HALF_UP);
        } else if (CurrencyConstants.CURRENCY_ETH.equals(toCurrency)) {
            return new BigDecimal(1);
        } else {
            int redoTime = 0;
            JSONObject returnJSONObject = null;
            Exception thirdException = null;
            do {
                try {
                    //组装请求URL
                    StringBuilder stringBuilder = new StringBuilder(QUERY_EXCHANGE_RATE_URL);
                    stringBuilder
                            .append(PARAM_APPEND_SYM).append("aggregate").append(PARAM_EQUAL_SYM).append("1")
                            .append(PARAM_AND_SYM).append("e").append(PARAM_EQUAL_SYM).append("CCCAGG")
                            .append(PARAM_AND_SYM).append("extraParams").append(PARAM_EQUAL_SYM).append("CryptoCompare")
                            .append(PARAM_AND_SYM).append("limit").append(PARAM_EQUAL_SYM).append("0")
                            .append(PARAM_AND_SYM).append("tryConversion").append(PARAM_EQUAL_SYM).append("false")
                            .append(PARAM_AND_SYM).append("fsym").append(PARAM_EQUAL_SYM).append(fromCurrency)
                            .append(PARAM_AND_SYM).append("tsym").append(PARAM_EQUAL_SYM).append(toCurrency);

                    //发送请求
                    System.err.println(stringBuilder.toString());
                    HttpResponse httpResponse = HttpClientUtil.doPostRequest(stringBuilder.toString(), null);
                    String result = EntityUtils.toString(httpResponse.getEntity());

                    //将请求返回数据转换为json
                    returnJSONObject = JSONObject.parseObject(result);
                    if (null != returnJSONObject) {
                        thirdException = null;
                        break;
                    }
                } catch (Exception e) {
                    thirdException = e;
                    redoTime = redoTime + 1;
                }
            } while (redoTime < 3);

            if (null != thirdException) {
                if (logger.isErrorEnabled()) {
                    logger.error("调用第三方汇率转换[{}]-[{}]失败:", fromCurrency, toCurrency, thirdException);
                }
            }

            if (null != returnJSONObject) {
                if ("Success".equals(returnJSONObject.getString("Response"))) {
                    String data = returnJSONObject.getString("Data");
                    JSONArray dataArray = JSONObject.parseArray(data);
                    JSONObject data0 = dataArray.getJSONObject(0);
                    return new BigDecimal(data0.getString("open"));
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }
}
