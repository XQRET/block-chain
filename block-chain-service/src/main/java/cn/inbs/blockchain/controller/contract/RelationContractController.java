/**  
 * Project Name:trunk  
 * File Name:RelationContractController.java  
 * Package Name:cn.inbs.blockchain.controller.contract  
 * Date:2018年6月4日下午5:04:35  
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.  
 */  
  
package cn.inbs.blockchain.controller.contract;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.inbs.blockchain.dao.po.BlockContract;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import cn.inbs.blockchain.common.constants.CommonConfigPerpertyConstants;
import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.constants.FileTypeEnum;
import cn.inbs.blockchain.common.constants.ParamTypeConstants;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.property.PropertyUtils;
import cn.inbs.blockchain.common.third.FileUpload2AliYun;
import cn.inbs.blockchain.common.utils.CookieUtils;
import cn.inbs.blockchain.common.utils.StringUtils;
import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.common.commonbean.ContractFileParam;
import cn.inbs.blockchain.common.commonbean.PagePo;
import cn.inbs.blockchain.service.contract.IContractPageService;
import cn.inbs.blockchain.service.contract.IRelationContractService;
import cn.inbs.blockchain.service.system.ISystemParamService;

/**  
 * ClassName: RelationContractController <br/>  
 * Description: 关联协议底层文件. <br/>  
 * @author anxiaoyu  
 * Date:2018年6月4日下午5:04:35  
 */
@Controller
@RequestMapping(value = "/contract")
public class RelationContractController extends BaseController {
	
    @Resource
    private ISystemParamService systemParamService;
	
    @Resource
    private IContractPageService contractPageService;
    
    @Resource
    private IRelationContractService relationContractService;
    
	/**
	 * 
	 * queryRelationContract:查询需要关联的协议. <br/>  
	 * @author anxiaoyu
	 * @param httpServletRequest
	 * @return
	 */
    @RequestMapping(value = "/queryRelationContract.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryRelationContract(HttpServletRequest httpServletRequest) {
        //参数校验
        PagePo pagePo = checkParam(httpServletRequest);
        //分页查询
        PagePo result = relationContractService.queryContractPage(pagePo);
        return retContent(result);
    }    
    
    @RequestMapping(value = "/uploadFile.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String uploadFile(@RequestParam(name="base64Data",required=false) String base64Data,@RequestParam(name="file",required=false) MultipartFile file,
    		String houseCode,String fileName,HttpServletRequest httpServletRequest) {
		try {
			String pathUrl = null;
			if((StringUtils.isEmpty(base64Data) && file == null) || (StringUtils.isNotEmpty(base64Data) && file != null)
					|| StringUtils.isEmpty(houseCode) || StringUtils.isEmpty(fileName)) {
				throw new BusinessException(BusinessErrorConstants.CONTRACT_0032);
			}
			//文件名替换特殊符号
			fileName = fileName.replaceAll(FileUpload2AliYun.FILE_NAME_SPECIAL_SYMBOLS, FileUpload2AliYun.SPACE_STR);
            //组装待上传文件名(时间戳+文件名后缀)
			fileName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf(FileUpload2AliYun.FILE_NAME_SPLIT_SYMBOLS));
			String filePath = PropertyUtils.getStringValue(CommonConfigPerpertyConstants.ALIYUN_CONTRACT_FILE_PATH_KEY, null);
			filePath = filePath + File.separator + CookieUtils.getCompanyBlockIdInCookie(httpServletRequest)+ File.separator + houseCode + File.separator+fileName;
			if(StringUtils.isNotEmpty(base64Data)) {
				String data = "";        
	            if(base64Data != null && "".equals(base64Data)){  
	            	throw new BusinessException(BusinessErrorConstants.CONTRACT_0032);
	            }else{  
	            	base64Data = URLDecoder.decode(base64Data, "utf-8");
	                String [] d = base64Data.split("base64,");  
	                if(d != null && d.length == 2){  
	                    data = d[1];  
	                }else{  
	                	throw new BusinessException(BusinessErrorConstants.CONTRACT_0032); 
	                }  
	            }
	            byte[] bs = Base64Utils.decodeFromString(data);  
				InputStream inputStream  = new ByteArrayInputStream(bs); 
				pathUrl = FileUpload2AliYun.uploadFile(inputStream, filePath);
			}else if(file != null) {
				pathUrl = FileUpload2AliYun.photoFileUpload(file, filePath);
			}
			return retContent(pathUrl);
		} catch (Exception e) {
			e.printStackTrace();  
			throw new BusinessException(BusinessErrorConstants.CONTRACT_0032);
		}
    }
    
    /**
     * 
     * uploadFolder:单个上传文件夹. <br/>  
     * @author anxiaoyu
     * @param files
     * @param httpServletRequest
     * @param houseCode
     * @return
     */
    @RequestMapping(value = "/uploadFolder.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String uploadFolder(@RequestParam(name="files",required=false) MultipartFile[] files,HttpServletRequest httpServletRequest,
    		String houseCode) {
		try {
			Map<String,String> map = systemParamService.getEmployeeParamValues(CookieUtils.getUserIdInCookie(httpServletRequest), 
	    			ParamTypeConstants.TYPE_CONTRACT_FILE);    	
	    	List<ContractFileParam> urls = new ArrayList<ContractFileParam>();
			Map<String,Object> resultMap = new HashMap<String,Object>();
	    	List<ContractFileParam> params = createFileParam(map);
			resultMap.put("params", params);
			if(files != null) {
				String filePath = PropertyUtils.getStringValue(CommonConfigPerpertyConstants.ALIYUN_CONTRACT_FILE_PATH_KEY, null);
				filePath = filePath + File.separator + CookieUtils.getCompanyBlockIdInCookie(httpServletRequest)+ File.separator +  houseCode + File.separator;
				for(MultipartFile file : files) {
					boolean ismatch = false;
					String fileName = file.getOriginalFilename();
					String type = fileName.substring(fileName.lastIndexOf(FileUpload2AliYun.FILE_NAME_SPLIT_SYMBOLS)+1);
					for(ContractFileParam param : params) {
						String rxtensionName = FileTypeEnum.getRxtensionName(param.getType());
						if(fileName.startsWith(param.getMatch()) 
								&&  rxtensionName.contains(type)
								){
							urls.add(new ContractFileParam(param,FileUpload2AliYun.photoFileUpload(file, filePath)));
							ismatch =true;
							break;
						}
					}
					if(!ismatch) {
						ContractFileParam param = new ContractFileParam();
						param.setTitle("未关联");
						param.setType(FileTypeEnum.getType(type));
						param.setSort(99);
						param.setUrl(FileUpload2AliYun.photoFileUpload(file, filePath));
						urls.add(param);
					}
				}	
				Collections.sort(urls);
				resultMap.put("urls", urls);
			}
			return retContent(resultMap);
		}catch (Exception e) {
			e.printStackTrace();  
			throw new BusinessException(BusinessErrorConstants.CONTRACT_0032);
		}
    }
    
    /**
     * 
     * queryRelationParam:查询关联需要的参数. <br/>  
     * @author anxiaoyu
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/queryRelationParam.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryRelationParam(HttpServletRequest httpServletRequest) {
    	Map<String,String> map = systemParamService.getEmployeeParamValues(CookieUtils.getUserIdInCookie(httpServletRequest), 
    			ParamTypeConstants.TYPE_CONTRACT_FILE);    	
    	List<ContractFileParam> list = createFileParam(map);
    	return retContent(list);
    }
    
    private List<ContractFileParam> createFileParam(Map<String,String> map){
    	List<ContractFileParam> list = new ArrayList<ContractFileParam>();
    	ContractFileParam fileParam = null;
    	for (Map.Entry<String,String> entry : map.entrySet()) {  
    		fileParam = JSON.parseObject(entry.getValue(),ContractFileParam.class);
    		fileParam.setParamKey(entry.getKey());
    		list.add(fileParam);    	  
    	}  
    	Collections.sort(list);
    	return list;
    }
    
    
    /**
     * 
     * relationContract:关联协议. <br/>  
     * @author anxiaoyu
     * @param httpServletRequest
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/relationContract.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String relationContract(HttpServletRequest httpServletRequest,BlockContract blockContract) throws IOException {
    	String companyBlockId = CookieUtils.getCompanyBlockIdInCookie(httpServletRequest);
    	Map<String,String> paramMap = systemParamService.getEmployeeParamValues(CookieUtils.getUserIdInCookie(httpServletRequest), 
    			ParamTypeConstants.TYPE_CONTRACT_FILE);    	
    	relationContractService.insertRelationContract(companyBlockId,blockContract,paramMap);
    	return retContent(null);
    }
    
    /**
     * 
     * deleteImportContract:删除协议. <br/>  
     * @author anxiaoyu
     * @param httpServletRequest
     * @param contractId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/deleteImportContract.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String deleteImportContract(HttpServletRequest httpServletRequest,String contractId) throws IOException {
    	String companyBlockId = CookieUtils.getCompanyBlockIdInCookie(httpServletRequest);
    	relationContractService.deleteImportContract(companyBlockId,contractId);
    	return retContent(null);
    }

	
    /**
     * 参数校验
     *
     * @param httpServletRequest
     * @return
     */
    private PagePo checkParam(HttpServletRequest httpServletRequest) {
        PagePo pagePo = new PagePo();
        String pageCountStr = httpServletRequest.getParameter(ContractConstants.PAGE_CONDITION_PAGE_COUNT);//每页条数
        String signStr = httpServletRequest.getParameter(ContractConstants.PAGE_IMPORT_UNIQUE_SIGN);
        signStr = signStr!=null?signStr.trim().replaceAll(" ", ""):signStr;
        if (null != pageCountStr) {
            try {
                Integer pageCount = Integer.valueOf(pageCountStr);
                pagePo.setPageCount(pageCount);
            } catch (NumberFormatException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("分页获取每页条数值:{}不合法", pageCountStr, e);
                }
            }
        }
        String pageIndexStr = httpServletRequest.getParameter(ContractConstants.PAGE_CONDITION_PAGE_INDEX);//页数
        if (null != pageIndexStr) {
            try {
                Integer pageIndex = Integer.valueOf(pageIndexStr);
                pagePo.setPageIndex(pageIndex);
            } catch (NumberFormatException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("分页获取页数值:{}不合法", pageIndexStr, e);
                }
            }
        }
        //获取分页查询条件
       
        
        
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(ContractConstants.PAGE_CONDITION_START_INDEX, pagePo.getPageStartCountIndex());//也开始行数
        map.put(ContractConstants.PAGE_CONDITION_PAGE_COUNT, pagePo.getPageCount());//查询行数
        map.put(ContractConstants.PAGE_CONDITION_IMPORT_TYPE, ContractConstants.CONTRACT_TYPE_1);//导入状态
        List<String> contractStatus = new ArrayList<String>();

        contractStatus.add(ContractConstants.CONTRACT_STATUS_XH);
        map.put(ContractConstants.PAGE_CONDITION_CONTRACT_STATUS, contractStatus);//合约状态
        
        map.put(ContractConstants.PAGE_IMPORT_UNIQUE_SIGN,signStr);//唯一标示
        
        
        
        List<String> companyBlockIds = new ArrayList<String>();
        companyBlockIds.add(CookieUtils.getCompanyBlockIdInCookie(httpServletRequest));
        map.put(ContractConstants.COMPANY_BLOCK_ID, companyBlockIds);
        pagePo.setConditionParamMap(map);
        return pagePo;
    }
    
}
  
