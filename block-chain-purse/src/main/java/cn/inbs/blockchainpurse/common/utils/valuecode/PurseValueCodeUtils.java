package cn.inbs.blockchainpurse.common.utils.valuecode;

import cn.inbs.blockchain.common.constants.CommonConstants;
import cn.inbs.blockchain.common.exception.PlatformErrorConstants;
import cn.inbs.blockchain.common.exception.PlatformException;
import cn.inbs.blockchainpurse.common.exception.PurseBusinessErrorConstants;
import cn.inbs.blockchainpurse.common.exception.PurseBusinessException;

/**
 * @Description: 码值校验工具类
 * @Package: cn.inbs.blockchainpurse.common.utils.valuecode
 * @ClassName: PurseValueCodeUtils
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/21 12:25
 * @Version: 1.0
 */
public class PurseValueCodeUtils {
    /**
     * 码值校验
     *
     * @param isAllowNull true--允许为空,false--不允许为空
     * @param val         待校验参数值
     * @param enums       定义枚举值
     */
    public static void checkValueCode(boolean isAllowNull, String val, PurseValueCodeEnums enums) {
        if (null == enums || enums.getCodeArray().length == 0) {
            throw new PurseBusinessException(PurseBusinessErrorConstants.CHECK_VALUE_CODE_0002, enums != null ? enums.getDescription() : CommonConstants.STRING_SPACE);
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
                    throw new PurseBusinessException(PurseBusinessErrorConstants.CHECK_VALUE_CODE_0001,
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
                    throw new PurseBusinessException(PurseBusinessErrorConstants.CHECK_VALUE_CODE_0001,
                            enums.getDescription(),
                            codeStr,
                            val);
                }
            }
        }
    }
}
