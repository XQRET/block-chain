/**  
 * Project Name:trunk  
 * File Name:ExcelUtils.java  
 * Package Name:cn.inbs.blockchain.common.utils  
 * Date:2018年6月11日下午3:34:53  
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.  
 */  
  
package cn.inbs.blockchain.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.commonbean.ExcelReplaceDataVO;

/**  
 * ClassName: ExcelUtils <br/>  
 * Description: TODO. <br/>  
 * @author anxiaoyu  
 * Date:2018年6月11日下午3:34:53  
 */
public class ExcelUtils {
	
	/**
	 * 
	 * replaceModel:execl替换. <br/>  
	 * @author anxiaoyu
	 * @param datas 替换集合
	 * @param sourceFilePath 读取路径
	 * @return
	 */
	public static byte[] replaceModel(List<ExcelReplaceDataVO> datas, String sourceFilePath) {  
        try {  
        	File file = new File(sourceFilePath);
    		Workbook wb = null;
    		try {
    			if(file.getName().indexOf(".xlsx")>-1){  
    				wb = new XSSFWorkbook(new FileInputStream(file));
                } else {  
                	wb = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(file)));  
                } 
    		}catch (Exception e) {
    			throw new BusinessException(BusinessErrorConstants.CONTRACT_0002);
    		}
        	
            Sheet sheet = wb.getSheetAt(0);  
            CreationHelper createHelper = wb.getCreationHelper();
           // CellStyle hlinkstyle = wb.createCellStyle();

            for (ExcelReplaceDataVO data : datas) {  
                //获取单元格内容  
                Row row = sheet.getRow(data.getRow());     
                Cell cell = row.getCell((short)data.getColumn());  
                String str = cell.getStringCellValue(); 
                
                if(ExcelReplaceDataVO.REPLACE_TYPE_STRING.equals(data.getType())) {
                	if(!StringUtils.isEmpty(data.getKey())) {
                		 str = str.replace(data.getKey(), data.getValue()); 
                		 data.setValue(str);
                	}
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
                    cell.setCellValue(data.getValue());
                }else if(ExcelReplaceDataVO.REPLACE_TYPE_URL.equals(data.getType())) {
                    XSSFHyperlink link = (XSSFHyperlink)createHelper.createHyperlink(Hyperlink.LINK_URL);
                    	      link.setAddress(data.getValue());
                    	      cell.setHyperlink((XSSFHyperlink) link);
//                    	      hlinkstyle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
//                    	      hlinkstyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
//                    	      hlinkstyle.set
//                    	      cell.setCellStyle(hlinkstyle); 

                }
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();  
            wb.write(out);
            return out.toByteArray();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return null;  
    }  
	
	public static boolean isRowEmpty(Row row) {
	   for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
	       Cell cell = row.getCell(c);
	       if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
	           return false;
	   }
	   return true;
	}
	
}
  
