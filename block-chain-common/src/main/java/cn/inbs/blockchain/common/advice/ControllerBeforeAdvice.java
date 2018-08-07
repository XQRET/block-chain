package cn.inbs.blockchain.common.advice;

import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import cn.inbs.blockchain.common.annotation.param.LengthCheck;
import cn.inbs.blockchain.common.annotation.param.RegexCheck;
import cn.inbs.blockchain.common.annotation.param.ValueCodeCheck;
import cn.inbs.blockchain.common.constants.CommonConstants;
import cn.inbs.blockchain.common.exception.PlatformErrorConstants;
import cn.inbs.blockchain.common.exception.PlatformException;
import cn.inbs.blockchain.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: controller前置方法
 * @Package: cn.inbs.blockchain.common.advice
 * @ClassName: ControllerBeforeAdvice
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/21 11:10
 * @Version: 1.0
 */
public class ControllerBeforeAdvice implements MethodBeforeAdvice {
    private static Logger logger = LoggerFactory.getLogger(ControllerBeforeAdvice.class);

    @Override
    public void before(Method method, Object[] inputParams, Object o) throws Throwable {
        if (method.isAnnotationPresent(RequestMapping.class)) {
            //日志输出请求参数
            printInputParamLog(method, inputParams);

            //注解参数校验
            if (null != inputParams && inputParams.length > 0) {
                for (Object inputParam : inputParams) {
                    paramCheck(inputParam);
                }
            }
        }

        //执行扩展方法
        doBefore(method, inputParams, o);
    }

    /**
     * 扩展方法
     *
     * @param method
     * @param inputParams
     * @param o
     */
    public void doBefore(Method method, Object[] inputParams, Object o) {

    }

    /**
     * 日志输出请求参数
     *
     * @param method
     * @param inputParams
     */
    private void printInputParamLog(Method method, Object[] inputParams) {
        //记录方法名及参数值
        String methodName = method.getName();
        if (logger.isInfoEnabled()) {
            logger.info("[{}] method input params :[{}]",
                    methodName,
                    (null != inputParams && inputParams.length > 0)
                            ? Arrays.toString(inputParams)
                            : CommonConstants.STRING_SPACE);
        }
    }

    /**
     * 注解参数校验
     *
     * @param inputParam
     */
    private void paramCheck(Object inputParam) throws IllegalAccessException {
        if (inputParam instanceof BaseControllerInput) {
            Class<?> paramClass = inputParam.getClass();
            Field[] declaredFields = paramClass.getDeclaredFields();
            if (declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    //取值开关
                    field.setAccessible(Boolean.TRUE);

                    //获取字段值
                    Object fieldValue = field.get(inputParam);

                    //字段类型
                    Class<?> fieldType = field.getType();

                    //非空校验
                    nullCheck(field, fieldValue, fieldType);

                    //长度校验
                    lengthCheck(field, fieldValue, fieldType);

                    //参数码值校验
                    valueCodeCheck(field, fieldValue, fieldType);

                    //正则校验
                    regexCheck(field, fieldValue, fieldType);

                    //子结构
                    paramCheck(fieldValue);
                }
            }
        }
    }

    /**
     * 参数非空校验
     *
     * @param field
     * @param fieldValue
     * @param fieldType
     */
    private void nullCheck(Field field, Object fieldValue, Class<?> fieldType) {
        if (field.isAnnotationPresent(IsNotNull.class)) {
            //获取字段描述
            IsNotNull annotation = field.getAnnotation(IsNotNull.class);
            String fieldDescription = annotation.fieldDescription();

            if (null == fieldValue) {
                throw new PlatformException(PlatformErrorConstants.PLATFORM_P_N_C_0001, (fieldDescription));
            } else {
                if (fieldType == String.class) {
                    String strValue = ((String) fieldValue).trim();
                    if (CommonConstants.STRING_SPACE.equals(strValue)) {
                        throw new PlatformException(PlatformErrorConstants.PLATFORM_P_N_C_0001, (fieldDescription));
                    }
                }
            }
        }
    }

    /**
     * 参数长度校验
     *
     * @param field
     * @param fieldValue
     * @param fieldType
     */
    private void lengthCheck(Field field, Object fieldValue, Class<?> fieldType) {
        if (field.isAnnotationPresent(LengthCheck.class) && fieldType == String.class) {
            //获取字段最大长度和最小长度及字段描述
            LengthCheck annotation = field.getAnnotation(LengthCheck.class);
            int minLength = annotation.minLength();
            int maxLength = annotation.maxLength();
            String fieldDescription = annotation.fieldDescription();
            if (null != fieldValue) {
                String value = (String) fieldValue;
                int length = value.length();
                if (length > maxLength || length < minLength) {
                    throw new PlatformException(PlatformErrorConstants.PLATFORM_P_L_C_0001,
                            fieldDescription,
                            value,
                            String.valueOf(minLength),
                            String.valueOf(maxLength));
                }
            }
        }
    }

    /**
     * 参数码值校验
     *
     * @param field
     * @param fieldValue
     * @param fieldType
     */
    private void valueCodeCheck(Field field, Object fieldValue, Class<?> fieldType) {
        if (field.isAnnotationPresent(ValueCodeCheck.class) && fieldType == String.class) {
            ValueCodeCheck annotation = field.getAnnotation(ValueCodeCheck.class);
            String codeValue = annotation.codeValue();
            String[] codeDatas = codeValue.split(",");
            String fieldDescription = annotation.fieldDescription();
            if (null != fieldValue) {
                String value = (String) fieldValue;
                if (StringUtils.isNotEmpty(value)) {
                    adviceCheckValueCode(codeDatas, fieldDescription, value);
                }
            }
        }
    }


    /**
     * 码值校验方法
     *
     * @param codeDatas        码值列表
     * @param fieldDescription 字段描述
     * @param value            待校验字段值
     */
    private void adviceCheckValueCode(String[] codeDatas, String fieldDescription, String value) {

        //组装码值
        StringBuilder codes = new StringBuilder();
        for (int i = 0; i < codeDatas.length; i++) {
            codes.append(codeDatas[i]);
            if (i != codeDatas.length - 1) {
                codes.append(",");
            }
        }
        String codeStr = codes.toString();

        boolean check = false;
        for (String codeData : codeDatas) {
            if (value.equals(codeData)) {
                check = true;
                break;
            }
        }

        if (!check) {
            throw new PlatformException(PlatformErrorConstants.PLATFORM_P_C_C_0001,
                    fieldDescription,
                    codeStr,
                    value);
        }
    }


    /**
     * 正则校验
     *
     * @param field
     * @param fieldValue
     * @param fieldType
     */
    private void regexCheck(Field field, Object fieldValue, Class<?> fieldType) {
        if (field.isAnnotationPresent(RegexCheck.class) && fieldType == String.class) {
            RegexCheck annotation = field.getAnnotation(RegexCheck.class);
            String fieldDescription = annotation.fieldDescription();
            String regex = annotation.regex();
            if (null != fieldValue) {
                String value = (String) fieldValue;
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(value);
                if (!matcher.matches()) {
                    throw new PlatformException(PlatformErrorConstants.PLATFORM_P_R_C_0001, fieldDescription);
                }
            }
        }
    }
}
