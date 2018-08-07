package cn.inbs.blockchain.controller.product.inputparam;

import cn.inbs.blockchain.common.advice.BaseControllerInput;
import cn.inbs.blockchain.common.annotation.param.IsNotNull;
import com.alibaba.fastjson.JSON;

/**
 * @Description: 查询预售产品详细信息入参
 * @Package: cn.inbs.blockchain.controller.product.inputparam
 * @ClassName: QueryProductSaleDetailInput
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/31 18:27
 * @Version: 1.0
 */
public class QueryProductSaleDetailInput extends BaseControllerInput {
    @IsNotNull(fieldDescription = "产品ID")
    private Long id;//产品ID

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
