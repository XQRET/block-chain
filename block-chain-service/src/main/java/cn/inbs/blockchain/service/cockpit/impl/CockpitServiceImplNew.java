package cn.inbs.blockchain.service.cockpit.impl;

import cn.inbs.blockchain.common.constants.CompanyConstants;
import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.constants.ContractEnum;
import cn.inbs.blockchain.common.utils.StringUtils;
import cn.inbs.blockchain.dao.cockpit.CockpitMapper;
import cn.inbs.blockchain.dao.company.BlockCompanyMapper;
import cn.inbs.blockchain.dao.company.CompanyPhotoMapper;
import cn.inbs.blockchain.dao.po.BlockCompany;
import cn.inbs.blockchain.common.commonbean.CompanyDetail;
import cn.inbs.blockchain.dao.po.CompanyPhoto;
import cn.inbs.blockchain.dao.vo.cockpit.AssertVO;
import cn.inbs.blockchain.dao.vo.cockpit.BarDO;
import cn.inbs.blockchain.dao.vo.cockpit.IncomeVO;
import cn.inbs.blockchain.dao.vo.cockpit.NumVO;
import cn.inbs.blockchain.dao.vo.cockpit.RegionVO;
import cn.inbs.blockchain.dao.vo.cockpit.SortRegionDesc;
import cn.inbs.blockchain.dao.vo.cockpit.SummaryDataVO;
import cn.inbs.blockchain.service.cockpit.ICockpitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:驾驶舱数据业务实现类
 * @Package: cn.inbs.blockchain.service.cockpit.impl
 * @ClassName:
 * @Date: 2018年07月04-15:41
 * @Author: createBy:chenhao
 **/
@Service(value = "cockpitServiceImplNew")
public class CockpitServiceImplNew implements ICockpitService {
    private static Logger logger = LoggerFactory.getLogger(CockpitServiceImpl.class);
    @Resource
    private BlockCompanyMapper blockCompanyMapper;
    @Resource
    private CompanyPhotoMapper companyPhotoMapper;
    @Resource
    private CockpitMapper cockpitMapper;

    @Override
    public AssertVO getCapital() {
        List<BarDO> list = cockpitMapper.listCapitalTop5();

        List<String> company = new ArrayList<String>(5);
        List<BigDecimal> money = new ArrayList<BigDecimal>(5);

        for (BarDO barDO : list) {
            company.add(barDO.getCompanyName());
            money.add(getBigDecimalMoney(barDO.getNewAddAmount()));
        }
        AssertVO ao = new AssertVO();
        ao.setListAssert(company);
        ao.setListMoney(money);
        return ao;
    }

    @Override
    public AssertVO getAssets() {
        List<BarDO> list = cockpitMapper.listAssetTop5();

        List<String> company = new ArrayList<String>(5);
        List<BigDecimal> money = new ArrayList<BigDecimal>(5);

        for (BarDO barDO : list) {
            company.add(barDO.getCompanyName());
            money.add(getBigDecimalMoney(barDO.getNewAddAmount()));
        }
        AssertVO ao = new AssertVO();
        ao.setListAssert(company);
        ao.setListMoney(money);
        return ao;
    }

    @Override
    public List<RegionVO> getDistrict() {
        //1.默认数据格式 所有的省份
        Map<String, Integer> map = new HashMap<String, Integer>();
        String[] province = {"南海诸岛", "北京", "天津", "上海", "重庆", "河北", "河南",
                "云南", "辽宁", "黑龙江", "湖南", "安徽", "山东", "新疆", "江苏", "浙江", "江西", "湖北", "广西",
                "甘肃", "山西", "内蒙古", "陕西", "吉林", "福建", "贵州", "广东", "青海", "西藏",
                "四川", "宁夏", "海南", "台湾", "香港", "澳门"};
        for (String p : province) {
            map.put(p, ContractConstants.NO_CONTRACT);
        }

        //2.有数据的省份
        List<BarDO> list = cockpitMapper.listDistrict();

        for (BarDO barDO : list) {
            //3.合并
            map.put(barDO.getProvinceName(), barDO.getNewAdd());
        }

        List<RegionVO> result = new ArrayList<RegionVO>(map.size());
        // 4.构造结果
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            RegionVO ro = new RegionVO(entry.getKey(), entry.getValue());
            result.add(ro);
        }

        Collections.sort(result, new SortRegionDesc());
        return result;

    }

    @Override
    public IncomeVO getForecast(int month, double rate) {
        // 保证上月
        if (month <= 0) {
            month = 1;
        }
        List<String> time = new ArrayList<String>(month);
        List<BigDecimal> money = new ArrayList<BigDecimal>(month);
        // 前几个月
        for (int currentMonth = 1; currentMonth <= month; currentMonth++) {
            // 获取当月
            BarDO barDO = cockpitMapper.getForecast(currentMonth, rate);
            time.add(barDO.getCurrentMonth());
            String newAddAmount = barDO.getNewAddAmount();
            // 默认金额
            if (newAddAmount == null) {
                newAddAmount = "0";
            }
            money.add(getBigDecimalMoney(newAddAmount));
        }
        IncomeVO incomeVO = new IncomeVO();
        incomeVO.setMoney(money);
        incomeVO.setTime(time);
        return incomeVO;
    }

    @Override
    public NumVO getNew(int monthCount) {
        return getAddSum(monthCount, ContractEnum.ADD);
    }

    @Override
    public NumVO getSum(int monthCount) {
        return getAddSum(monthCount, ContractEnum.SUM);
    }

    private NumVO getAddSum(int monthCount, ContractEnum type) {
        if (monthCount <= 0) {
            monthCount = 1;
        }
        List<String> time = new ArrayList<String>(monthCount);
        List<Integer> number = new ArrayList<Integer>(monthCount);
        List<BigDecimal> money = new ArrayList<BigDecimal>(monthCount);
        for (int currentMonth = 1; currentMonth <= monthCount; currentMonth++) {
            BarDO barDO = new BarDO();
            switch (type) {
                // 新增
                case ADD:
                    barDO = cockpitMapper.getNew(currentMonth);
                    break;
                // 总量
                case SUM:
                    barDO = cockpitMapper.getSum(currentMonth);
                    break;
                default:
                    return null;
            }
            time.add(barDO.getCurrentMonth());
            number.add(barDO.getNewAdd());
            String newAddAmount = barDO.getNewAddAmount();
            if (newAddAmount == null) {
                newAddAmount = "0";
            }
            money.add(getBigDecimalMoney(newAddAmount));
        }
        NumVO n = new NumVO();
        n.setTime(time);
        n.setNumber(number);
        n.setMoney(money);
        return n;

    }

    @Override
    public SummaryDataVO getTotalItem() {
        // 构造数据结构
        SummaryDataVO summaryDataVO = new SummaryDataVO();
        // 当月新增合约数
        int currentNewAdd = cockpitMapper.getCurrentNewAdd();
        // 当月新增合约金额
        float currentNewMoney = cockpitMapper.getCurrentNewMoney();
        // 资产端用户数（个）
        int assetNumber = cockpitMapper.getAsserNumber();
        // 资金端用户数（个）
        int capitalNumber = cockpitMapper.getCapitalNumber();
        // 平台生效合约数（个）
        int propertContractNumber = cockpitMapper.getPropertContractNumber();
        // 平台生效合约金额（万元）
        float propertContractMoney = cockpitMapper.getPropertContractMoney();
        // 平台合约总数（个）
        int contractNumber = cockpitMapper.getContractNumber();
        // 平台合约总金额（万元）
        float contractMoney = cockpitMapper.getContractMoney();
        summaryDataVO.setCurrentNewAdd(currentNewAdd);
        summaryDataVO.setCurrentNewMoney(getFormatMoney(currentNewMoney));
        summaryDataVO.setAssetNumber(assetNumber);
        summaryDataVO.setCapitalNumber(capitalNumber);
        summaryDataVO.setProperContractNumber(propertContractNumber);
        summaryDataVO.setPropertContractMoney(getFormatMoney(propertContractMoney));
        summaryDataVO.setContractNumber(contractNumber);
        summaryDataVO.setContractMoney(getFormatMoney(contractMoney));
        return summaryDataVO;
    }

    @Override
    public AssertVO getCapitalAll() {
        List<BarDO> list = cockpitMapper.listCapital();

        List<String> company = new ArrayList<String>();
        List<BigDecimal> money = new ArrayList<BigDecimal>();

        for (BarDO barDO : list) {
            company.add(barDO.getCompanyName());
            money.add(getBigDecimalMoney(barDO.getNewAddAmount()));
        }
        AssertVO ao = new AssertVO();
        ao.setListAssert(company);
        ao.setListMoney(money);
        return ao;
    }

    @Override
    public AssertVO getAssetsAll() {
        List<BarDO> list = cockpitMapper.listAsset();

        List<String> company = new ArrayList<String>();
        List<BigDecimal> money = new ArrayList<BigDecimal>();

        for (BarDO barDO : list) {
            company.add(barDO.getCompanyName());
            money.add(getBigDecimalMoney(barDO.getNewAddAmount()));
        }
        AssertVO ao = new AssertVO();
        ao.setListAssert(company);
        ao.setListMoney(money);
        return ao;
    }

    @Override
    public List<CompanyDetail> getNodeList() {
        //查询公司信息
        List<BlockCompany> blockCompanies = blockCompanyMapper.selectBlockCompanysByStatusAndType(new BlockCompany());

        //过滤公司名称为空的公司
        for (int i = 0; i < blockCompanies.size(); i++) {
            if (StringUtils.isEmpty(blockCompanies.get(i).getCompanyName())) {
                blockCompanies.remove(i);
            }
        }

        //查询公司logo
        List<CompanyDetail> returnList = new ArrayList<CompanyDetail>();
        for (BlockCompany blockCompany : blockCompanies) {
            CompanyDetail companyDetail = new CompanyDetail();
            companyDetail.setBlockCompany(blockCompany);

            CompanyPhoto companyPhoto = new CompanyPhoto();
            companyPhoto.setType(CompanyConstants.COMPANY_PHOTO_TYPE_LOGO);
            companyPhoto.setCompanyId(blockCompany.getId());
            companyPhoto = companyPhotoMapper.selectCompanyPhotoByIndex(companyPhoto);
            if (null != companyPhoto) {
                companyDetail.setCompanyPhoto(companyPhoto);
            }
            returnList.add(companyDetail);
        }

        if (logger.isInfoEnabled()) {
            logger.info("CompanyDetail info list : [{}]", returnList.toString());
        }
        return returnList;
    }

    /**
     * 格式化金额  123,123.12
     *
     * @param money
     * @return
     */
    private String getFormatMoney(float money) {
        // 格式化
        DecimalFormat df = new DecimalFormat(",###.##");
        return df.format(new BigDecimal(money).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 字符串金额转换为BigDecimal
     *
     * @param money
     * @return
     */
    private BigDecimal getBigDecimalMoney(String money) {
        return new BigDecimal(money).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
