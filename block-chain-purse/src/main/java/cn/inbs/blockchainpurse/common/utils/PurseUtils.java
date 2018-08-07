package cn.inbs.blockchainpurse.common.utils;

import cn.inbs.blockchain.common.constants.CommonConstants;
import cn.inbs.blockchain.common.property.PropertyUtils;
import cn.inbs.blockchain.common.utils.DateUtils;
import cn.inbs.blockchain.common.utils.ExchangeRateConversionUtils;
import cn.inbs.blockchainpurse.common.cache.ConversionCurrencyCache;
import cn.inbs.blockchainpurse.common.constants.PurseConfigPropertyConstants;
import cn.inbs.blockchainpurse.common.constants.PurseThesaurus;
import cn.inbs.blockchainpurse.common.constants.RegexConstants;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 钱包工具类
 * @Package: cn.inbs.blockchainpurse.common.utils
 * @ClassName: PurseUtils
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/25 18:14
 * @Version: 1.0
 */
public class PurseUtils {

    private static final String ADDRESS_PREFIX = "0X";

    /**
     * 根据公钥生成钱包地址
     *
     * @param publicKey
     * @return
     */
    public static String getPurseAddressByPublicKey(String publicKey) {
        publicKey = publicKey.substring(40, 100);
        String[] split = publicKey.split(CommonConstants.STRING_SPACE);
        Pattern pattern = Pattern.compile(RegexConstants.PURSE_NAME);
        StringBuilder stringBuilder = new StringBuilder(ADDRESS_PREFIX);
        int appendLength = 0;
        for (String value : split) {
            Matcher matcher = pattern.matcher(value);
            if (matcher.matches()) {
                appendLength++;
                stringBuilder.append(value);
                if (appendLength == 38) {
                    break;
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 从用户token中获取用户钱包名称
     *
     * @param token
     * @return
     */
    public static String getPurseNameInToken(String token) {
        if (null == token) {
            throw new NullPointerException();
        } else {
            String[] split = token.split("-");
            return split[0];
        }
    }

    /**
     * 根据币种转换金额
     *
     * @param amount       待转换金额
     * @param formCurrency 待转换币种
     * @param toCurrency   转换后币种
     * @return
     */
    public static BigDecimal getAmonutByCurrency(BigDecimal amount, String formCurrency, String toCurrency) {
        BigDecimal exchangeRate;

        //先去缓存取
        String conversionCurrencyInCache = ConversionCurrencyCache.getConversionCurrencyInCache(formCurrency + "-" + toCurrency);
        if (null != conversionCurrencyInCache) {
            exchangeRate = new BigDecimal(conversionCurrencyInCache);
        } else {
            //不存在去查询
            exchangeRate = ExchangeRateConversionUtils.getExchangeRate(formCurrency,
                    toCurrency,
                    new BigDecimal(PropertyUtils.getStringValue(PurseConfigPropertyConstants.CURRENCY_ETH_TO_RET, null)));
            ConversionCurrencyCache.createConversionCurrencyCache(formCurrency + "-" + toCurrency, exchangeRate.toString());
        }
        return amount.multiply(exchangeRate);
    }


    /**
     * 根据私钥生成助记词
     *
     * @param privateKey
     * @return
     */
    public static synchronized String getPurseUserMnemonicByPrivateKey(String privateKey) {
        //生成第一位助记词
        int privateKeyHash = privateKey.hashCode();
        int formatDateHash = DateUtils.formatDate(new Date(privateKeyHash < 0 ? -privateKeyHash : privateKeyHash), DateUtils.DateFormat.DATE_FORMAT_6).hashCode();
        int i1 = formatDateHash % PurseThesaurus.PURSE_THESAURUS.length;
        String purseThesaurus = PurseThesaurus.PURSE_THESAURUS[(i1 < 0 ? -i1 : i1)];

        StringBuilder mnemonicBuilder = new StringBuilder();
        mnemonicBuilder
                .append(purseThesaurus)
                .append("|");

        //生成2-10位助记词
        for (int i = 1; i <= 10; i++) {
            int hashCode = privateKey.hashCode();
            int i2 = (hashCode * i * (new Integer(i * i).hashCode())) % PurseThesaurus.PURSE_THESAURUS.length;
            if (i2 < 0) {
                i2 = -i2;
            }
            mnemonicBuilder
                    .append(PurseThesaurus.PURSE_THESAURUS[i2])
                    .append("|");
        }

        //生成最后一位助记词
        int i3 = privateKeyHash + privateKey.length() + mnemonicBuilder.length();
        int formatDateHash2 = DateUtils.formatDate(new Date(i3 < 0 ? -i3 : i3), DateUtils.DateFormat.DATE_FORMAT_6).hashCode();
        int i4 = formatDateHash2 % PurseThesaurus.PURSE_THESAURUS.length;
        String purseThesaurus1 = PurseThesaurus.PURSE_THESAURUS[(i4 < 0 ? -i4 : i4)];
        mnemonicBuilder.append(purseThesaurus1);

        return mnemonicBuilder.toString();
    }
}
