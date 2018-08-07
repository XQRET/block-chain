package cn.inbs.blockchain.common.third;

import cn.inbs.blockchain.common.constants.CommonConstants;
import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;

/**
 * @Description: 图像文件上传
 * @Package: cn.inbs.blockchain.common.utils
 * @ClassName:
 * @Date: 2018年05月16-15:01
 * @Author: createBy:zhangmingyang
 **/
public class FileUpload2AliYun {
    private static Logger logger = LoggerFactory.getLogger(FileUpload2AliYun.class);


    public static final String SPACE_STR = "";
    public static final String FILE_NAME_SPECIAL_SYMBOLS = "[\\s~·`!！@#￥$%^……&*（()）\\-——\\-_=+【\\[\\]】｛{}｝\\|、\\\\；;：:‘'“”\"，,《<。》>、/？?]";
    public static final String FILE_NAME_SPLIT_SYMBOLS = ".";//文件名分隔符

    private static final String END_POINT = "http://oss-cn-shenzhen.aliyuncs.com";
    private static final String ACCESS_KEY_ID = "LTAIpXjhaH5t7mVb";
    private static final String ACCESS_KEY_SECRET = "XzNAu8uxBOpM7IKeB7nYgn85FEhv8w";
    private static final String BUCKET_NAME = "blockoss";
    private static final String URL = "https://blockoss.oss-cn-shenzhen.aliyuncs.com/";

    /**
     * oss链接对象
     */
    private static OSSClient ossClient;

    /**
     * 初始化oss链接
     */
    private static void init() {
        // 创建ClientConfiguration实例([conf.setMaxConnections(200)--设置OSSClient使用的最大连接数，默认1024],[conf.setSocketTimeout(50)--设置请求超时时间，默认50秒],[conf.setMaxErrorRetry(5)--设置失败请求重试次数，默认3次])
        ClientConfiguration conf = new ClientConfiguration();
        ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET, conf);
    }

    /**
     * 关闭连接
     */
    private static void shutdown() {
        ossClient.shutdown();
    }

    /**
     * 上传图像
     *
     * @param file
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String photoFileUpload(MultipartFile file,
                                         String filePath) throws IOException {
        if (null == file) {
            if (logger.isWarnEnabled()) {
                logger.warn("待上传文件为空");
            }
            return null;
        } else {
            String uploadFileName = file.getOriginalFilename();//源文件名

            if (!SPACE_STR.equals(uploadFileName)) {
                //文件名替换特殊符号
                uploadFileName = uploadFileName.replaceAll(FILE_NAME_SPECIAL_SYMBOLS, SPACE_STR);

                //组装待上传文件名(时间戳+文件名后缀)
                uploadFileName = System.currentTimeMillis() + uploadFileName.substring(uploadFileName.lastIndexOf(FILE_NAME_SPLIT_SYMBOLS));

                //获取文件流
                InputStream fileInputStream = file.getInputStream();

                //上传文件
                String fileUrl = FileUpload2AliYun.uploadFile(fileInputStream, filePath + uploadFileName);
                if (logger.isInfoEnabled()) {
                    logger.info("图像文件:{}上传成功", fileUrl);
                }
                return fileUrl;
            } else {
                if (logger.isWarnEnabled()) {
                    logger.warn("待上传文件的文件名为空");
                }
                return null;
            }
        }
    }

    /**
     * 上传文件
     *
     * @param inputStream
     * @param filePath
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String uploadFile(InputStream inputStream,
                                     String filePath) throws UnsupportedEncodingException {
        //初始化链接
        init();

        //文件上传
        ossClient.putObject(BUCKET_NAME, filePath, inputStream);

        //关闭连接
        shutdown();
        return URL + URLEncoder.encode(filePath, CommonConstants.COMMON_ENCODING);
    }
    
    public static OSSClient getOSSClient(){
    	//初始化链接
        init();
    	return ossClient;
    }
    
    public static String getBucketName(){
    	return BUCKET_NAME;
    }
    
    public static String getURL(){
    	return URL;
    }
}
