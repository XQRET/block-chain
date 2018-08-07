package cn.inbs.blockchain.service.cockpit.impl;

import cn.inbs.blockchain.common.constants.CompanyConstants;
import cn.inbs.blockchain.common.utils.StringUtils;
import cn.inbs.blockchain.dao.company.BlockCompanyMapper;
import cn.inbs.blockchain.dao.company.CompanyPhotoMapper;
import cn.inbs.blockchain.dao.po.BlockCompany;
import cn.inbs.blockchain.common.commonbean.CompanyDetail;
import cn.inbs.blockchain.dao.po.CompanyPhoto;
import cn.inbs.blockchain.dao.vo.cockpit.AssertVO;
import cn.inbs.blockchain.dao.vo.cockpit.IncomeVO;
import cn.inbs.blockchain.dao.vo.cockpit.NumVO;
import cn.inbs.blockchain.dao.vo.cockpit.RegionVO;
import cn.inbs.blockchain.dao.vo.cockpit.SortBigDecimalDesc;
import cn.inbs.blockchain.dao.vo.cockpit.SortRegionDesc;
import cn.inbs.blockchain.dao.vo.cockpit.SummaryDataVO;
import cn.inbs.blockchain.service.cockpit.ICockpitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


@Service(value = "cockpitServiceImpl")
public class CockpitServiceImpl implements ICockpitService {
    private static Logger logger = LoggerFactory.getLogger(CockpitServiceImpl.class);

    @Resource
    private BlockCompanyMapper blockCompanyMapper;
    @Resource
    private CompanyPhotoMapper companyPhotoMapper;

    // 资产端金额
    private List<BigDecimal> assetMoney = new ArrayList<BigDecimal>();
    private List<BigDecimal> assetMoneyTop5 = new ArrayList<BigDecimal>(5);

    // 资金端金额
    private List<BigDecimal> capitalMoney = new ArrayList<BigDecimal>();
    private List<BigDecimal> capitalMoneyTop5 = new ArrayList<BigDecimal>(5);

    /**
     * 返回资金端TOP5
     *
     * @return
     */
    @Override
    public AssertVO getCapital() {
        getMoney();
        List<String> list = getCapitalCompany();
        List<String> company = list.subList(0, 5);
        AssertVO a = new AssertVO();
        a.setListAssert(company);
        a.setListMoney(capitalMoneyTop5);
        return a;
    }

    /**
     * 返回资产端TOP5
     *
     * @return
     */
    @Override
    public AssertVO getAssets() {
        getMoney();
        AssertVO a = new AssertVO();
        List<String> company = getAssetsCompany().subList(0, 5);
        a.setListAssert(company);
        a.setListMoney(assetMoneyTop5);
        return a;
    }

    @Override
    public List<RegionVO> getDistrict() {
        String[] province = {"南海诸岛","北京", "天津", "上海", "重庆", "河北", "河南",
                "云南", "辽宁", "黑龙江", "湖南", "安徽", "山东", "新疆", "江苏", "浙江", "江西", "湖北", "广西",
                "甘肃", "山西", "内蒙古", "陕西", "吉林", "福建", "贵州", "广东", "青海", "西藏",
                "四川", "宁夏", "海南", "台湾", "香港", "澳门"};
        Random r = new Random();
        List<RegionVO> list = new ArrayList<RegionVO>(province.length);
        for (String s : province) {
            RegionVO regionVO = new RegionVO(s, r.nextInt(2000));
            list.add(regionVO);
        }
        Collections.sort(list, new SortRegionDesc());
        return list;
    }

    @Override
    public IncomeVO getForecast(int month, double rate) {
        String[] ts = {"201801", "201802", "201803", "201804", "201805", "201806"};
        IncomeVO a = new IncomeVO();
        Random r = new Random();
        List<BigDecimal> money = new ArrayList<BigDecimal>();
        for (int i = 0; i < ts.length; i++) {
            money.add(new BigDecimal(r.nextInt(100000)));
        }
        a.setTime(Arrays.asList(ts));
        a.setMoney(money);
        return a;
    }

    @Override
    public NumVO getNew(int monthCount) {
        Random r = new Random();
        List<String> time = new ArrayList<String>(12);
        List<Integer> number = new ArrayList<Integer>(12);
        List<BigDecimal> money = new ArrayList<BigDecimal>(12);
        time.add("201707");
        time.add("201708");
        time.add("201709");
        time.add("201710");
        time.add("201711");
        time.add("201712");
        time.add("201801");
        time.add("201802");
        time.add("201803");
        time.add("201804");
        time.add("201805");
        time.add("201806");

        for (int i = 1; i <= 12; i++) {
            number.add(r.nextInt(1000));
            money.add(new BigDecimal(r.nextInt(1000)));
        }
        NumVO n = new NumVO();
        n.setTime(time);
        n.setNumber(number);
        n.setMoney(money);
        return n;
    }

    @Override
    public NumVO getSum(int monthCount) {
        return getNew(12);
    }

    @Override
    public SummaryDataVO getTotalItem() {
        SummaryDataVO s = new SummaryDataVO();
        s.setCurrentNewAdd(200);
        s.setCurrentNewMoney("200.65");
        s.setAssetNumber(20);
        s.setCapitalNumber(20);
        s.setProperContractNumber(1000);
        s.setPropertContractMoney("1000.95");
        s.setContractNumber(1200);
        s.setContractMoney("2000.42");
        return s;
    }

    @Override
    public AssertVO getCapitalAll() {
        AssertVO a = new AssertVO();
        List<String> company = getCapitalCompany();
        a.setListAssert(company);
        a.setListMoney(capitalMoney);
        return a;
    }

    @Override
    /**
     * 资产方
     */
    public AssertVO getAssetsAll() {
        AssertVO a = new AssertVO();
        List<String> company = getAssetsCompany();
        a.setListAssert(company);
        a.setListMoney(assetMoney);
        return a;
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

        if (logger.isDebugEnabled()) {
            logger.debug("CompanyDetail info list : [{}]", returnList.toString());
        }
        return returnList;
    }

    /**
     * 返回资产方公司
     *
     * @return
     */
    private List<String> getAssetsCompany() {
        String[] asserts = {"深圳市爱租商业管理有限公司", "深圳市邦融实业有限公司", "深圳市澳信投资顾问有限公司",
                "广州腾达资产管理有限公司", "广州泰达资产管理有限公司", "南京益达资产管理有限公司",
                "深圳益康资产管理有限公司", "上海银瑞资产管理有限公司"};
        List<String> list = Arrays.asList(asserts);
        return list;
    }

    /**
     * 返回金额
     *
     * @return
     */
    private void getMoney() {
        Random r = new Random();
        List<BigDecimal> assetMoneyList = new ArrayList<BigDecimal>();
        List<BigDecimal> capitalMoneyList = new ArrayList<BigDecimal>();

        for (int i = 0; i < 8; i++) {
            assetMoneyList.add(new BigDecimal(r.nextInt(300000)));
            capitalMoneyList.add(new BigDecimal(r.nextInt(250000)));
        }
        Collections.sort(assetMoneyList, new SortBigDecimalDesc());
        assetMoneyTop5 = assetMoneyList.subList(0, 5);
        assetMoney = assetMoneyList;

        Collections.sort(capitalMoneyList, new SortBigDecimalDesc());
        capitalMoneyTop5 = capitalMoneyList.subList(0, 5);
        capitalMoney = capitalMoneyList;
    }

    /**
     * 返回资金方公司
     *
     * @return
     */
    private List<String> getCapitalCompany() {
        String[] asserts = {"深圳鸿冠资本管理有限公司", "广东壹宝资产管理有限公司", "深圳星桥数据",
                "康润商业保理（深圳）有限公司", "上海中汇金投资集团股份有限公司", "北京高益资金管理有限公司",
                "广东银鑫资金管理有限公司", "深圳金瑞资金管理有限公司"};
        List<String> list = Arrays.asList(asserts);
        return list;
    }

}
