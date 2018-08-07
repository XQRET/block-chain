package cn.inbs.blockchain.service.cdo;

import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.exception.PlatformException;
import cn.inbs.blockchain.common.utils.ExceptionUtils;
import cn.inbs.blockchain.common.utils.StringUtils;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.TransService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.service.cdo
 * @ClassName:
 * @Date: 2018年05月11-17:14
 * @Author: createBy:zhangmingyang
 **/
public abstract class BaseCdoService extends TransService {
    private static Logger logger = LoggerFactory.getLogger(BaseCdoService.class);

    private static final String METHOD_NAME_KEY = "strTransName";
    private static final int REQUEST_SUCCESS = 0;
    private static final int REQUEST_FAILED = -1;
    private static final String REQUEST_SUCCESS_MESSAGE = "成功";


    @Override
    public Return handleTrans(CDO cdoRequest, CDO cdoResponse) {
        Return result;
        try {
            result = doHandleTrans(cdoRequest, cdoResponse);
        } catch (Throwable e) {
            if (e instanceof BusinessException) {
                logger.warn("业务异常,cause by:", e);
            } else if (e instanceof PlatformException) {
                logger.error("平台异常,cause by:", e);
            } else {
                logger.error("系统异常,cause by:", e);
            }
            result = new Return(REQUEST_FAILED, ExceptionUtils.getResultMessage(e));
        }

        return result;
    }

    /**
     * 获取cdo调用方法名称
     *
     * @param cdoRequest
     * @return
     */
    public String getInvokeMethodName(String methodKey, CDO cdoRequest) {
        if (StringUtils.isEmpty(methodKey)) {
            methodKey = METHOD_NAME_KEY;
        }

        String invokeMethodName = cdoRequest.getStringValue(methodKey);
        if (invokeMethodName != null) {
            return invokeMethodName.trim();
        } else {
            if (logger.isErrorEnabled()) {
                logger.error("通过:{}获取方法名为空", methodKey);
            }
            throw new RuntimeException("获取方法名失败");
        }
    }

    /**
     * 初始化返回信息
     *
     * @return
     */
    public Return initReturn() {
        return new Return(REQUEST_SUCCESS, REQUEST_SUCCESS_MESSAGE);
    }

    /**
     * 业务实现
     *
     * @param cdoRequest
     * @param cdoResponse
     * @return
     */
    protected abstract Return doHandleTrans(CDO cdoRequest, CDO cdoResponse) throws Exception;
}
