/**  
 * Project Name:trunk  
 * File Name:ImportContractServiceImpl.java  
 * Package Name:cn.inbs.blockchain.service.contract.impl  
 * Date:2018年5月28日上午11:42:36  
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.  
 */  
  
package cn.inbs.blockchain.service.contract.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import cn.inbs.blockchain.common.commonbean.ContractImport;
import cn.inbs.blockchain.common.commonbean.ExcelReplaceDataVO;
import cn.inbs.blockchain.common.utils.*;
import cn.inbs.blockchain.dao.po.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.inbs.blockchain.common.constants.CommonConfigPerpertyConstants;
import cn.inbs.blockchain.common.constants.CommonConstants;
import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.constants.ParamTypeConstants;
import cn.inbs.blockchain.common.constants.RepaymentWayEnum;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.property.PropertyUtils;
import cn.inbs.blockchain.common.utils.DateUtils.DateFormat;
import cn.inbs.blockchain.dao.company.BlockCompanyMapper;
import cn.inbs.blockchain.dao.contract.BlockContractMapper;
import cn.inbs.blockchain.dao.contract.ContractInfoUrlMapper;
import cn.inbs.blockchain.dao.contract.ContractSerialMapper;
import cn.inbs.blockchain.service.contract.IImportContractService;
import cn.inbs.blockchain.service.system.ISystemParamService;

/**  
 * ClassName: ImportContractServiceImpl <br/>  
 * Description: 合约导入. <br/>  
 * @author anxiaoyu  
 * Date:2018年5月28日上午11:42:36  
 */
@Service("importContractService")
public class ImportContractServiceImpl implements IImportContractService {
	
	private static Logger logger = LoggerFactory.getLogger(BlockContracServiceImpl.class);
	
	@Autowired 
	private ApplicationContext context;  
	
    @Resource
    private BlockContractMapper blockContractMapper;
	
    @Resource
    private ContractSerialMapper contractSerialMapper;
    
    @Resource
    private BlockCompanyMapper blockCompanyMapper;
    
    @Resource
    private ContractInfoUrlMapper contractInfoUrlMapper;
    
    @Resource
    private ISystemParamService systemParamService;
    
    @Override
    public byte[] createContractExecel(BlockContract bo, boolean isThird, String path){
    	try {
    		bo = blockContractMapper.selectBlockContractByIndex(bo);
            ContractInfoUrl contractInfoUrl = new ContractInfoUrl();
            contractInfoUrl.setContractId(bo.getContractId());
            List<ContractInfoUrl> contractInfoUrls = contractInfoUrlMapper.selectContractInfoUrlListByContractBlockId(contractInfoUrl);
    		contractInfoUrls = assemblyContractInfoUrl(contractInfoUrls,isThird);
            BlockCompany blockCompany = new BlockCompany();
            blockCompany.setId(bo.getCompanyId());
            blockCompany =  blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);
            String introduce = systemParamService.getEmployeeParamValue(blockCompany.getEmployeeId(),ParamTypeConstants.TYPE_COMPANY_INFO,ParamTypeConstants.KEY_PROJECT_INTRODUCE);
            String personUrl = null;
            String houseUrl = null;
            for (ContractInfoUrl url : contractInfoUrls) {
            	if(ContractConstants.CONTRACT_INFO_PERSON_INFO.equals( url.getUrlType())) {
            		personUrl = url.getUrl();
            	}else if(ContractConstants.CONTRACT_INFO_HOUSE_INFO.equals( url.getUrlType())) {
            		houseUrl = url.getUrl();
            	}
            }
            Calendar calendar = Calendar.getInstance();
        	List<ExcelReplaceDataVO> datas = new ArrayList<ExcelReplaceDataVO>();
        	//合约编号
        	ExcelReplaceDataVO vo = new ExcelReplaceDataVO(1, 3,bo.getContractId(), ExcelReplaceDataVO.REPLACE_TYPE_STRING);
        	datas.add(vo);
        	//合约登记日期
        	vo =  new ExcelReplaceDataVO(2, 3,DateUtils.formatDate(bo.getRegistDate(), DateFormat.DATE_FORMAT_3), ExcelReplaceDataVO.REPLACE_TYPE_STRING);
        	datas.add(vo);
        	//合约登记有效期
        	calendar.setTime(bo.getRegistDate());
            calendar.add(Calendar.DATE,7);
        	vo =  new ExcelReplaceDataVO(3, 3,DateUtils.formatDate(calendar.getTime(), DateFormat.DATE_FORMAT_3), ExcelReplaceDataVO.REPLACE_TYPE_STRING);
        	datas.add(vo);
        	//项目名称
        	vo =  new ExcelReplaceDataVO(5, 3,blockCompany.getCompanyName()+"长租分期第"+bo.getId()+"号合约", ExcelReplaceDataVO.REPLACE_TYPE_STRING);
        	datas.add(vo);
        	//项目简介
          	vo =  new ExcelReplaceDataVO(6, 3,introduce, ExcelReplaceDataVO.REPLACE_TYPE_STRING);
        	datas.add(vo);
        	//借款金额
        	vo =  new ExcelReplaceDataVO(7, 3,bo.getAmount()+"元", ExcelReplaceDataVO.REPLACE_TYPE_STRING);
        	datas.add(vo);
        	//还款方式
        	vo =  new ExcelReplaceDataVO(10, 3,RepaymentWayEnum.getRemarkById(bo.getRepaymentWay()), ExcelReplaceDataVO.REPLACE_TYPE_STRING);
        	datas.add(vo);
        	//借款期限
        	vo =  new ExcelReplaceDataVO(8, 3,bo.getTerm()+"月", ExcelReplaceDataVO.REPLACE_TYPE_STRING);
        	datas.add(vo);
        	//年化利率
        	vo =  new ExcelReplaceDataVO(13, 3,bo.getInterestRate()+"%", ExcelReplaceDataVO.REPLACE_TYPE_STRING);
        	datas.add(vo);
        	//个人征信
        	vo =  new ExcelReplaceDataVO(17, 3,personUrl, ExcelReplaceDataVO.REPLACE_TYPE_URL);
        	datas.add(vo);
        	//房产信息
        	vo =  new ExcelReplaceDataVO(22, 3,houseUrl, ExcelReplaceDataVO.REPLACE_TYPE_URL);
        	datas.add(vo);
        	//姓名（脱敏处理）
        	String name = bo.getContractSigner();
        	if(isThird) {
        		name = name.substring(0, 1)+StringUtils.appendSign(2);
        	}
        	vo =  new ExcelReplaceDataVO(23, 3,name, ExcelReplaceDataVO.REPLACE_TYPE_STRING);
        	datas.add(vo);
        	//证件号码（脱敏处理）
        	String idNo = bo.getIdNo();
        	if(isThird) {
            	if(idNo.length()>=18) {
            		idNo = idNo.substring(0, 4)+StringUtils.appendSign(2)+ idNo.substring(7, 14)+StringUtils.appendSign(4);
            	}else{
            		if(idNo.length()>=4) {
                		idNo = idNo.substring(0, 4)+StringUtils.appendSign(2);
            		}else {
            			idNo=StringUtils.appendSign(4);
            		}
            	}
        	}
        	vo =  new ExcelReplaceDataVO(24, 3,idNo, ExcelReplaceDataVO.REPLACE_TYPE_STRING);
        	datas.add(vo);
        	//在平台逾期次数
        	vo =  new ExcelReplaceDataVO(27, 3,bo.getOverdueNum()+"次", ExcelReplaceDataVO.REPLACE_TYPE_STRING);
        	datas.add(vo);
        	//在平台逾期总金额
        	vo =  new ExcelReplaceDataVO(28, 3,bo.getOverdueAmount()+"元", ExcelReplaceDataVO.REPLACE_TYPE_STRING);
        	datas.add(vo);
        	//征信逾期次数
        	vo =  new ExcelReplaceDataVO(29, 3,bo.getCreditOverdueNum()+"次", ExcelReplaceDataVO.REPLACE_TYPE_STRING);
        	datas.add(vo);
        	//征信借贷次数
        	vo =  new ExcelReplaceDataVO(30, 3,bo.getCreditLoanNum()+"次", ExcelReplaceDataVO.REPLACE_TYPE_STRING);
        	datas.add(vo);
        	//个人征信
        	vo =  new ExcelReplaceDataVO(31, 3,personUrl, ExcelReplaceDataVO.REPLACE_TYPE_URL);
        	datas.add(vo);
        	//个人征信
        	vo =  new ExcelReplaceDataVO(32, 3,personUrl, ExcelReplaceDataVO.REPLACE_TYPE_URL);
        	datas.add(vo);
        	return ExcelUtils.replaceModel(datas, path);
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("下载文件异常:",e);
			throw new BusinessException(e,BusinessErrorConstants.CONTRACT_0034);
		}
    }
    
    
    private List<ContractInfoUrl> assemblyContractInfoUrl(List<ContractInfoUrl> contractSerialList,boolean isThird) throws UnsupportedEncodingException {
        for (ContractInfoUrl contractInfoUrl : contractSerialList) {
            String url = contractInfoUrl.getUrl();
            url = url +
                    ContractConstants.SYM_PARAM_APPEND_SYM +
                    ContractConstants.H5_REQUEST_IS_THIRD + ContractConstants.SYM_EQUAL + isThird;

            //生成签名数据
            TreeMap<String, String> treeMap = new TreeMap<String, String>();
            treeMap.put(ContractConstants.H5_PARAM_KEY_REQUEST_URL, url);
            String sign;
            try {
                sign = RSAUtils.sign(treeMap, PropertyUtils.getStringValue(CommonConfigPerpertyConstants.LOCAL_BLOCK_CHAIN_PRIVATE_KEY, null));
                sign = URLEncoder.encode(sign, CommonConstants.COMMON_ENCODING);
            } catch (Exception e) {
                throw new BusinessException(e, BusinessErrorConstants.CONTRACT_0007);
            }

            //组装访问路径
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer
                    .append(url)
                    .append(ContractConstants.SYM_PARAM_APPEND_SYM)
                    .append(ContractConstants.REGISTER_CONTRACT_SIGN_KEY).append(ContractConstants.SYM_EQUAL).append(sign)
                    .append(ContractConstants.SYM_PARAM_APPEND_SYM)
                    .append(ContractConstants.H5_PARAM_KEY_REQUEST_URL).append(ContractConstants.SYM_EQUAL).append(URLEncoder.encode(url, CommonConstants.COMMON_ENCODING));

            contractInfoUrl.setUrl(stringBuffer.toString());
        }
        return contractSerialList;
    }
    
    
	/**  
	 * 合约导入.  
	 * @see cn.inbs.blockchain.service.contract.IImportContractService#importContract(org.springframework.web.multipart.MultipartFile, java.lang.String)  
	 */
	@Override
	public String insertImportContract(MultipartFile file, Long employeeId) {
		//获取导入映射
    	String mapping = systemParamService.getEmployeeParamValue(employeeId,ParamTypeConstants.TYPE_CONTRACT_EXECL_MAPPING,
    			ParamTypeConstants.KEY_EXECL_NAME_MAPPING);
    	JSONObject jsonObject = JSONObject.parseObject(mapping);
		//查询公司信息
    	BlockCompany blockCompany = new BlockCompany();
        blockCompany.setEmployeeId(employeeId);;
        blockCompany = blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);
        //校验公司信息
    	checkCompanyInfo(employeeId, blockCompany);
        //获取导入配置参数
    	Map<String,Integer> intParamMap = getParamMap(blockCompany.getEmployeeId());
    	//查询所有在执行过程中的房屋编号
    	List<String> importUniqueSignList = blockContractMapper.selectDistinctImportUniqueSign(blockCompany.getId());
    	importUniqueSignList = importUniqueSignList==null?new ArrayList<String>():importUniqueSignList;
    	//获取导入文件
		Workbook wb = null;
		try {
			if(file.getOriginalFilename().indexOf(".xlsx")>-1){  
				wb = new XSSFWorkbook(file.getInputStream());
            } else {  
            	wb = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));  
            } 
		}catch (Exception e) {
			throw new BusinessException(BusinessErrorConstants.CONTRACT_0002);
		}
		Sheet sheet = wb.getSheetAt(0);
		StringBuffer bf = new StringBuffer();
		bf.append(ContractConstants.IMPORT_PLACEHOLDER);
        //获取第一个sheet页   
		int successNum = 0;
		int failNum = 0;
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {    
            Row row= sheet.getRow(i);    
            if(row!=null && !ExcelUtils.isRowEmpty(row)){
            	ContractImport contractImport = null;
            	try {
            		contractImport = new ContractImport(jsonObject,intParamMap, row);
            	}catch (Exception e) {
                   	e.printStackTrace();
                    if (logger.isErrorEnabled()) {
                        logger.error("导入合约:导入数据失败,失败原因{}", e.getMessage());
                    }
                    failNum++;
                	bf.append("第"+(i+1)+"行数据不合法,无法导入.错误原因："+e.getMessage()+".</br>");
                	continue;
				}
                String valiResult = contractImport.validate();
                if(valiResult != null) {
                	bf.append("第"+(i+1)+"行数据导入失败.失败原因："+valiResult+".</br>");
                	failNum++;
                }else {
                    if(importUniqueSignList.contains(contractImport.getImportUniqueSign())) {
                    	bf.append("第"+(i+1)+"行数据导入唯一标示已存在.</br>");
                    	failNum++;
                    	continue;
                    }
                    try {
                    	context.getBean(IImportContractService.class).newInsertContract(contractImport, blockCompany);
                        successNum++;
                        importUniqueSignList.add(contractImport.getImportUniqueSign());
                    }catch (Exception e) {
                    	e.printStackTrace();
                        if (logger.isErrorEnabled()) {
                            logger.error("导入合约:导入数据失败,失败原因{}", e.getMessage());
                        }
                        failNum++;
                    	bf.append("第"+(i+1)+"行数据插入数据库失败.</br>");
					}
                }
            }else {
            	return bf.toString().replace(ContractConstants.IMPORT_PLACEHOLDER, ("导入总数:"+(successNum+failNum)+";导入成功数："+successNum+";导入失败数"+failNum+".</br>"));
            }
        } 
        return bf.toString().replace(ContractConstants.IMPORT_PLACEHOLDER, ("导入总数:"+(successNum+failNum)+";导入成功数："+successNum+";导入失败数"+failNum+".</br>"));
	}

	/**  
	 * 新增合约信息.  
	 * @see cn.inbs.blockchain.service.contract.IImportContractService#insertContract()  
	 */
	@Override
	public void newInsertContract(ContractImport contractImport,BlockCompany blockCompany) {
		//创建合约
		BlockContract blockContract = contractImport.createContract(blockCompany);
		blockContract.setRemark(getContractRemark(blockContract.getRegistDate(),CommonConfigPerpertyConstants.CONTRACT_REGISTER_DESCRIPTION_KEY));
		blockContractMapper.insertBlockContract(blockContract);
		
		//注册
		blockContract.setContractStatus(ContractConstants.CONTRACT_STATUS_ZC);
        recordingoCntractSerial(blockContract, blockContract.getRegistCompanyBlockId());

		//执行
        blockContract.setRegistDate(contractImport.getRegistDate());
        blockContract.setRemark(getContractRemark(blockContract.getRegistDate(),CommonConfigPerpertyConstants.CONTRACT_EXECUTE_DESCRIPTION));
		blockContract.setContractStatus(ContractConstants.CONTRACT_STATUS_ZX);
        recordingoCntractSerial(blockContract, blockContract.getRegistCompanyBlockId());
        
        //注销
        blockContract.setRegistDate(blockContract.getContractToDate());
        blockContract.setRemark(getContractRemark(blockContract.getContractToDate(),CommonConfigPerpertyConstants.CONTRACT_DESTROY_DESCRIPTION));
		blockContract.setContractStatus(ContractConstants.CONTRACT_STATUS_XH);
        recordingoCntractSerial(blockContract, blockContract.getRegistCompanyBlockId());
	}
	
	/**
	 * 
	 * checkCompanyInfo:校验公司合法性. <br/>  
	 * @author anxiaoyu
	 * @param lEmployeeId
	 * @param resultCompany
	 */
    private void checkCompanyInfo(Long lEmployeeId, BlockCompany resultCompany) {
        //检查公司信息
        if (null == resultCompany) {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0002,
                    ContractConstants.EMPLOYEE_ID, lEmployeeId+"");
        } else {
            String status = resultCompany.getCompanyStatus();
            //认证未通过不允许注册
            if ("1".equals(status)) {
                throw new BusinessException(BusinessErrorConstants.CONTRACT_0003, lEmployeeId+"");
            }
        }
    }
	
	/**
	 * 
	 * getParamMap:获取参数map. <br/>  
	 * @author anxiaoyu
	 * @param employeeId
	 * @return
	 */
	private Map<String,Integer> getParamMap(Long employeeId) {
    	Map<String,String> strParamMap = systemParamService.getEmployeeParamValues(employeeId,ParamTypeConstants.TYPE_CONTRACT_EXECL_COLUNM);
    	Map<String,Integer> intParamMap = new HashMap<String,Integer>();
        if(MapUtils.isNotEmpty(strParamMap)) {
        	try {
        		for (Map.Entry<String, String> entry : strParamMap.entrySet()) {
        			intParamMap.put(entry.getKey(), getIntegerValue(strParamMap, entry.getKey()));
    			}
        	} catch (Exception e) {
        		e.printStackTrace();
        		throw new BusinessException(BusinessErrorConstants.CONTRACT_0009);
			}
        	return intParamMap;
        }else {
            throw new BusinessException(BusinessErrorConstants.CONTRACT_0009);
        }
    }
	
	private Integer getIntegerValue(Map<String,String> map,String type) {
		if(map.containsKey(type)) {
			return Integer.parseInt(map.get(type));
		}else {
			return -1;
		}
	}
	
	
	/**
	 * 
	 * recordingoCntractSerial:新增合约流水. <br/>  
	 * @author anxiaoyu
	 * @param blockContract
	 * @param companyBlockId
	 */
    private void recordingoCntractSerial(BlockContract blockContract, String companyBlockId) {
        ContractSerial contractSerial = new ContractSerial();
        contractSerial.setCompanyBlockId(companyBlockId);//操作机构
        contractSerial.setContractId(blockContract.getContractId());//合约ID
        contractSerial.setContractBlockId(blockContract.getContractBlockId());//合约区块链ID
        contractSerial.setContractRemark(blockContract.getRemark());//概要
        contractSerial.setContractStatus(blockContract.getContractStatus());//合约状态
        contractSerial.setCreateTime(blockContract.getRegistDate());//注册时间
        contractSerial.setUpdateTime(blockContract.getRegistDate());//修改时间
        contractSerial.setExecuteRemark(blockContract.getRemark());//操作描述
        contractSerialMapper.insertContractSerial(contractSerial);
    }
    
    /**
     * 
     * getContractRemark:生成合约备注. <br/>  
     * @author anxiaoyu
     * @param registerDate
     * @return
     */
    private String getContractRemark(Date registerDate,String remarkTemplate) {
    	if(registerDate !=null) {
            String messsgeTemplate = PropertyUtils.getStringValue(remarkTemplate, null);
            return StringUtils.assemblyStringMessageByParam(messsgeTemplate,
                    DateUtils.formatDate(registerDate, DateUtils.DateFormat.DATE_FORMAT_8));
    	}
    	return null;
    }
    
}
  
