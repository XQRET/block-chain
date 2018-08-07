package cn.inbs.blockchain.service.third;

import cn.inbs.blockchain.common.commonbean.DetailContractInfo;
import cn.inbs.blockchain.common.commonbean.ThirdQueryContractDetailInfo;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.service.third
 * @ClassName:
 * @Date: 2018年06月07-19:22
 * @Author: createBy:zhangmingyang
 **/
public interface IThirdContractService {
    /**
     * 第三方查询合约详细信息
     *
     * @param thirdQueryContractDetailInfo 请求参数
     * @return
     */
    DetailContractInfo queryContractDetailInfoByThird(ThirdQueryContractDetailInfo thirdQueryContractDetailInfo) throws Exception;
}
