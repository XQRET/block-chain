package cn.inbs.blockchain.common.utils.valuecode;

import cn.inbs.blockchain.common.constants.CommonConstants;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.exception.PlatformErrorConstants;
import cn.inbs.blockchain.common.exception.PlatformException;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.common.utils
 * @ClassName:
 * @Date: 2018年05月21-11:58
 * @Author: createBy:zhangmingyang
 **/
public class CheckValueCodeUtils {
    /**
     * 码值校验
     *
     * @param isAllowNull true--允许为空,false--不允许为空
     * @param val         待校验参数值
     * @param enums       定义枚举值
     */
    public static void checkValueCode(boolean isAllowNull, String val, ValueCodeEnums enums) {
        if (null == enums || enums.getCodeArray().length == 0) {
            throw new BusinessException(BusinessErrorConstants.CHECK_VALUE_CODE_0002, enums != null ? enums.getDescription() : CommonConstants.STRING_SPACE);
        } else {
            String[] codeArray = enums.getCodeArray();

            //组装码值
            StringBuilder codes = new StringBuilder();
            for (int i = 0; i < codeArray.length; i++) {
                codes.append(codeArray[i]);
                if (i != codeArray.length - 1) {
                    codes.append(",");
                }
            }
            String codeStr = codes.toString();

            if (null == val) {
                //待校验参数非空校验
                if (!isAllowNull) {
                    throw new BusinessException(BusinessErrorConstants.CHECK_VALUE_CODE_0001,
                            enums.getDescription(),
                            codeStr,
                            "空");
                }
            } else {
                val = val.trim();
                boolean isCode = false;
                for (String code : enums.getCodeArray()) {
                    if (code.equals(val)) {
                        //匹配一个校验通过
                        isCode = true;
                        break;
                    }
                }

                if (!isCode) {
                    throw new BusinessException(BusinessErrorConstants.CHECK_VALUE_CODE_0001,
                            enums.getDescription(),
                            codeStr,
                            val);
                }
            }
        }
    }
}
