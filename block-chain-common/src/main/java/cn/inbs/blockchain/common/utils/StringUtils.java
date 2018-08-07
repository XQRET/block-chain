package cn.inbs.blockchain.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:字符串工具类
 * @Package: cn.inbs.blockchain.common.utils
 * @ClassName: StringUtils.java
 * @Date: 16:27 2018/4/2
 * @author: create by zhangmingyang
 **/
public class StringUtils {
    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    /**
     * 空串
     */
    private static final String SPACE_STR = "";

    private static final String PARAM_PLACEHOLDER_SYM = "\\{}";//参数占位符
    
    
    private static final String SIGN_ASTERISK ="*";
    
    /**
     * 工具类错误消息
     */
    private class StringUtilsErroeMessage {
        /**
         * 切割数据参数为空异常消息
         */
        private static final String SPLIT_STR_WITH_TEMPLATE_PARAM_NULL_MES = "SPLIT TEMPLATE OR SPLIT STRINGVAULE OR SPLITTYPE IS NULL,PLEASE CHECK!!!";

        /**
         * 切割数据错误消息
         */
        private static final String SPLIT_STR_NULL_MES = "SPLIT STRING VAULE IS NULL OR SPLIT TYPE IS NULL";

        /**
         * 模板与数据不一致时异常信息
         */
        private static final String VALUE_SIZE_NOT_EQUAL_TEMPLATE_SIZE_MES = "VALUE SIZE NOT EQUAL TEMPLATE SIZE";

    }

    //===========================================================================================================================

    /**
     * 字符串空判断
     *
     * @param val 待判断数据值
     * @return
     */
    public static boolean isEmpty(String val) {
        return null == val || SPACE_STR.equals(val.trim());
    }
    
    /**
     * 
     * appendSign:添加*号. <br/>  
     * @author anxiaoyu
     * @param num 添加个数
     * @return
     */
	public static String appendSign(int num) {
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<num;i++) {
			buffer.append(SIGN_ASTERISK);
		}
		return buffer.toString();
	}
    
    /**
     * 字符串非空判断
     *
     * @param val 待判断数据值
     * @return
     */
    public static boolean isNotEmpty(String val) {
        return !isEmpty(val);
    }

    /**
     * 根据指定的数据模板切割数据
     *
     * @param template     数据模板
     * @param strVal       待切割数据
     * @param splitType    数据分割符
     * @param isCompletion 当模板与数据分割完不一致时是否自动补全(true:自动补齐)
     * @return
     */
    public static Map<String, String> splitStrWithTemplate(String template,
                                                           String strVal,
                                                           String splitType,
                                                           boolean isCompletion) {
        Map<String, String> splitMap = new HashMap<String, String>();

        //数据模板或者待切割数据或者切割符为空时,抛出运行时异常
        if (StringUtils.isEmpty(template) ||
                StringUtils.isEmpty(strVal) ||
                StringUtils.isEmpty(splitType)) {
            if (logger.isErrorEnabled()) {
                logger.error(StringUtilsErroeMessage.SPLIT_STR_WITH_TEMPLATE_PARAM_NULL_MES);
            }
            throw new NullPointerException(StringUtilsErroeMessage.SPLIT_STR_WITH_TEMPLATE_PARAM_NULL_MES);
        } else {
            //切割数据模板
            String[] templateArr = splitStr(template, splitType, false);

            //切割原数据
            String[] strValArr = splitStr(strVal, splitType, false);

            //判断是否自动补全
            if (isCompletion) {
                //自动补全
                for (int i = 0; i < templateArr.length; i++) {
                    String splitTemplate = templateArr[i];
                    String splitVal;

                    //防止索引越界
                    try {
                        splitVal = strValArr[i];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        splitVal = SPACE_STR;
                    }
                    splitMap.put(splitTemplate.trim(), splitVal.trim());
                }
            } else {
                //非自动补全
                if (templateArr.length != strValArr.length) {
                    //当切割模板与数据不一致时抛出异常
                    if (logger.isErrorEnabled()) {
                        logger.error(StringUtilsErroeMessage.VALUE_SIZE_NOT_EQUAL_TEMPLATE_SIZE_MES);
                    }
                    throw new RuntimeException(StringUtilsErroeMessage.VALUE_SIZE_NOT_EQUAL_TEMPLATE_SIZE_MES);
                } else {
                    //组装数据
                    for (int i = 0; i < templateArr.length; i++) {
                        splitMap.put(templateArr[i].trim(), strValArr[i].trim());
                    }
                }
            }
        }
        return splitMap;
    }

    /**
     * 根据给定的分割符切割数据
     *
     * @param strVal            待切割数据
     * @param splitType         切割数据分割符
     * @param isIgnoreSplitType 是否忽略分隔符(true:是;false:否)
     * @return
     */
    public static String[] splitStr(String strVal,
                                    String splitType,
                                    boolean isIgnoreSplitType) {
        strVal = strVal.trim();
        String[] splitArr;
        if (StringUtils.isEmpty(strVal) ||
                StringUtils.isEmpty(splitType)) {
            //待切割数据及分割符不能为空
            if (logger.isErrorEnabled()) {
                logger.error(StringUtilsErroeMessage.SPLIT_STR_NULL_MES);
            }
            throw new NullPointerException(StringUtilsErroeMessage.SPLIT_STR_NULL_MES);
        } else {
            //根据指定的规则切割数据
            if (isIgnoreSplitType) {
                splitArr = strVal.split(splitType);
            } else {
                splitArr = strVal.split(splitType, -1);
            }
        }
        return splitArr;
    }

    /**
     * 空串专空(null)
     *
     * @param val
     * @return
     */
    public static String space2Null(String val) {
        if (StringUtils.isEmpty(val)) {
            return null;
        } else {
            return val.trim();
        }
    }

    /**
     * null转换为空串
     *
     * @param val
     * @return
     */
    public static String null2Space(String val) {
        if (StringUtils.isEmpty(val)) {
            return SPACE_STR;
        } else {
            return val.trim();
        }
    }

    /**
     * 根据消息模板及参数组装消息
     *
     * @param messsgeTemplate
     * @param params
     * @return
     */
    public static String assemblyStringMessageByParam(String messsgeTemplate, String... params) {
        if (null == params) {
            return messsgeTemplate;
        } else {
            for (String param : params) {
                messsgeTemplate = messsgeTemplate.replaceFirst(PARAM_PLACEHOLDER_SYM, param);
            }
            return messsgeTemplate;
        }
    }
}
