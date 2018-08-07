package cn.inbs.blockchain.controller.contract;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;

import cn.inbs.blockchain.common.third.FileUpload2AliYun;
import cn.inbs.blockchain.common.web.BaseController;

/**
 * 
 * @author chenhao 合同文件下载
 */
@Controller
@RequestMapping(value = "/contract")
public class ContractController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(ContractController.class);

	@RequestMapping(value = "/download.do", method = RequestMethod.POST)
	public void downLoadFile(String urls, HttpServletRequest request, HttpServletResponse response) {
		try {
			// 1.获取url
			if (StringUtils.isBlank(urls)) {
				if (LOGGER.isDebugEnabled()) {
					logger.debug("下载地址不存在{}", urls);
				}
				return;
			}

			// 创建临时文件
			String tempFilename = "download.zip";
			// 创建临时文件
			File zipFile = File.createTempFile("temp", ".zip");
			FileOutputStream f = new FileOutputStream(zipFile);
			CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
			ZipOutputStream zos = new ZipOutputStream(csum);
			// 2.遍历读取文件
			String[] strings = StringUtils.split(urls, ",");
			InputStream inputStream = null;
			// 获取链接
			OSSClient ossClient = FileUpload2AliYun.getOSSClient();
			for (String s : strings) {
				String fileID = URLDecoder.decode(s, "utf-8");
				OSSObject ossObject = ossClient.getObject("creditoss", fileID);
				inputStream = ossObject.getObjectContent();
				int lastSymbol = StringUtils.lastIndexOf(fileID, "\\");
				// 获取文件名
				// 从sit-upload/head_photo/\深房地字第2000344251号\1528276370325.jpg
				// 得到1528276370325.jpg
				String fileName = StringUtils.substring(fileID, lastSymbol + 1);
				zos.putNextEntry(new ZipEntry(fileName));

				int bytesRead = 0;
				// 向压缩文件中输出数据
				while ((bytesRead = inputStream.read()) != -1) {
					zos.write(bytesRead);
				}
				zos.closeEntry();
			}
			inputStream.close();
			// 3.将文件存储到zip中
			zos.close();

			// 4.返回
			ossClient.shutdown();

			// 将zip刷出来
			FileInputStream fis = new FileInputStream(zipFile);
			BufferedInputStream buff = new BufferedInputStream(fis);
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			// 通知浏览器以附件形式下载
			response.setHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(tempFilename, "utf-8"));
			byte[] car = new byte[1024];
			int l = 0;
			while (l < zipFile.length()) {
				int j = buff.read(car, 0, 1024);
				l += j;
				out.write(car, 0, j);
			}
			fis.close();
			buff.close();
			out.close();

			// 删除临时文件
			zipFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/displayPDF.do", method = RequestMethod.GET)
	public void displayPDF(@RequestParam(value = "id", required = false) String id, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (StringUtils.isBlank(id)) {
				return;
			}
			// 46是https://blockoss.oss-cn-shenzhen.aliyuncs.com/长度 截取掉
			String fileID = StringUtils.substring(id, 46);
			// 获取链接
			OSSClient ossClient = FileUpload2AliYun.getOSSClient();
			// 读取文件
			OSSObject ossObject = ossClient.getObject(FileUpload2AliYun.getBucketName(), fileID);
			InputStream inputStream = null;
			inputStream = ossObject.getObjectContent();

			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			int length = 0;
			byte buffer[] = new byte[1024];
			while ((length = inputStream.read(buffer)) != -1) {
				out.write(buffer, 0, length);
			}
			if (out != null) {
				out.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
