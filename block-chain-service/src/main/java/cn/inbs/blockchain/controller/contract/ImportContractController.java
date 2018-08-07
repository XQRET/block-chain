package cn.inbs.blockchain.controller.contract;

import cn.inbs.blockchain.common.constants.ParamTypeConstants;
import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.utils.CookieUtils;
import cn.inbs.blockchain.common.web.BaseController;
import cn.inbs.blockchain.dao.po.BlockContract;
import cn.inbs.blockchain.service.contract.IImportContractService;
import cn.inbs.blockchain.service.system.ISystemParamService;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * ClassName: ImportContractController <br/>  
 * Description: 合约导入. <br/>  
 * @author anxiaoyu  
 * Date:2018年5月28日下午2:04:50
 */
@Controller
@RequestMapping(value = "/contract")
public class ImportContractController extends BaseController {
	
    private static Logger logger = LoggerFactory.getLogger(ImportContractController.class);

    @Resource
    private IImportContractService importContractService;
    
    @Resource
    private ISystemParamService systemParamService;
    
    /**
     * 
     * importContract:合约导入. <br/>  
     * @author anxiaoyu
     * @param file
     * @param lEmployeeId
     * @return
     */
    @RequestMapping(value = "/importContract.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String importContract(@RequestParam(value="file") MultipartFile file,HttpServletRequest request) {
    	Long employeeId = CookieUtils.getUserIdInCookie(request);
        if(file.getOriginalFilename().lastIndexOf(".xlsx") ==-1 && file.getOriginalFilename().lastIndexOf(".xlx")==-1){  
    		throw new BusinessException(BusinessErrorConstants.CONTRACT_0010);
        } 
    	String resuslt = importContractService.insertImportContract(file,employeeId);
    	return retContent(resuslt);
    }
    
    /**
     * 
     * queryImportParamList:查询导入配置. <br/>  
     * @author anxiaoyu
     * @param lEmployeeId
     * @return
     */
    @RequestMapping(value = "/queryImportParam.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryImportParamList(HttpServletRequest request) {    	
    	Long employeeId = CookieUtils.getUserIdInCookie(request);
    	Map<String,String> colunm = systemParamService.getEmployeeParamValues(employeeId,ParamTypeConstants.TYPE_CONTRACT_EXECL_COLUNM);
    	String mapping = systemParamService.getEmployeeParamValue(employeeId,ParamTypeConstants.TYPE_CONTRACT_EXECL_MAPPING,
    			ParamTypeConstants.KEY_EXECL_NAME_MAPPING);
    	Map<String,Object> map = new HashMap<String, Object>();
    	map.put("colunm", colunm);
    	map.put("mapping", mapping);    	
        return retContent(map);
    }
    
    /**
     * 
     * insertImportParam:编辑导入参数. <br/>  
     * @author anxiaoyu
     * @param request
     * @param employeeId
     * @return
     */
    @RequestMapping(value = "/insertImportParam.do",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String insertImportParam(HttpServletRequest request) {
    	Long employeeId = CookieUtils.getUserIdInCookie(request);
    	Map<String, String[]> paramterMap = request.getParameterMap();
    	Map<String,String> colunm = systemParamService.insertSystemParam(employeeId, paramterMap,ParamTypeConstants.TYPE_CONTRACT_EXECL_COLUNM);
    	String mapping = systemParamService.getEmployeeParamValue(employeeId,ParamTypeConstants.TYPE_CONTRACT_EXECL_MAPPING,
    			ParamTypeConstants.KEY_EXECL_NAME_MAPPING);
    	Map<String,Object> map = new HashMap<String, Object>();
    	map.put("colunm", colunm);
    	map.put("mapping", mapping); 
    	return retContent(map);
    }
    
    /**
     * 
     * downloadContractTemplate:下载导入模板. <br/>  
     * @author anxiaoyu
     * @param request
     * @return
     */
    @RequestMapping(value = "/downloadContractTemplate.do")
    public ResponseEntity<byte[]> downloadContractTemplate(HttpServletRequest request) {
    	ResponseEntity<byte[]> entity = null;
    	String path = request.getServletContext().getRealPath("/execlTemplate/合约导入模板.xlsx");
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();  
    	try {
            String downloadFielName = new String(file.getName().getBytes("UTF-8"),"iso-8859-1");
            headers.setContentDispositionFormData("attachment", downloadFielName); 
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			entity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
			        headers, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
            throw new BusinessException("系统异常");
		}  
        return entity;
    }
   
    
    @RequestMapping(value = "/downloadContractInfo.do")
    public ResponseEntity<byte[]> downloadContractInfo(BlockContract bo, @RequestParam(defaultValue="false",required=false) boolean isThird,
                                                       HttpServletRequest request) {
    	ResponseEntity<byte[]> entity = null;
    	String path = request.getServletContext().getRealPath("/execlTemplate/房产租赁合约标的信息.xlsx");
    	byte[] bytes = importContractService.createContractExecel(bo, isThird,path);
        HttpHeaders headers = new HttpHeaders();  
    	try {
            String downloadFielName = new String("房产租赁合约标的信息.xlsx".getBytes("UTF-8"),"iso-8859-1");
            headers.setContentDispositionFormData("attachment", downloadFielName); 
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			entity = new ResponseEntity<byte[]>(bytes,    
			        headers, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
            throw new BusinessException("系统异常");
		}  
        return entity;
    }
    

    
}
