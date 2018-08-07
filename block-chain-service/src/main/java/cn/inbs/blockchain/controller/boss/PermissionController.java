package cn.inbs.blockchain.controller.boss;

import cn.inbs.blockchain.common.utils.Constant;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangmingyang
 */
public class PermissionController {
    public boolean hasPermission(HttpServletRequest request, String strPermissionItem) {
        //获取登录者角色信息
        CDO cdoLoginer = (CDO) request.getAttribute(Constant.Key_cdoBossUser);
        String strPermission = cdoLoginer.getStringValue(Constant.Key_Boss_strPermission);
        String[] strsPermissionItem = Utility.splitString(strPermission, '|');
        for (String item : strsPermissionItem) {
            if (item.equalsIgnoreCase(strPermissionItem)) {
                return true;
            }
        }
        return false;
    }
}
