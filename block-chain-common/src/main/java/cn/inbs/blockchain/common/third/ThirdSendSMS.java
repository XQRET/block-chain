package cn.inbs.blockchain.common.third;

import cn.inbs.blockchain.common.constants.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Description: 第三方发送短信
 * @Package: cn.inbs.blockchain.common.third
 * @ClassName:
 * @Date: 2018年04月28-17:41
 * @Author: createBy:zhangmingyang
 **/
public class ThirdSendSMS {
    private static Logger logger = LoggerFactory.getLogger(ThirdSendSMS.class);

    /**
     * 发送短信
     *
     * @param path
     * @param postContent
     * @return
     * @throws Exception
     */
    public static String sendPost(String path, String postContent) throws Exception {
        URL url;
        HttpURLConnection httpURLConnection = null;
        try {
            url = new URL(path);

            //开启链接
            httpURLConnection = (HttpURLConnection) url.openConnection();

            //组织参数
            httpURLConnection.setRequestMethod(CommonConstants.HTTP_REQUEST_MODE_POST);// 提交模式
            httpURLConnection.setConnectTimeout(60000);//连接超时 单位毫秒
            httpURLConnection.setReadTimeout(60000);//读取超时 单位毫秒
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Charset", CommonConstants.COMMON_ENCODING);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            //创建链接请求
            httpURLConnection.connect();
            OutputStream os = httpURLConnection.getOutputStream();
            os.write(postContent.getBytes(CommonConstants.COMMON_ENCODING));
            os.flush();

            StringBuilder sb = new StringBuilder();
            int httpRspCode = httpURLConnection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == httpRspCode) {
                // 开始获取数据
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), CommonConstants.COMMON_ENCODING));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                //关闭资源
                bufferedReader.close();

                if (logger.isInfoEnabled()) {
                    logger.info("调用第三方发短信返回参数:[{}]", sb.toString());
                }

                return sb.toString();
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("短信发送异常", e);
            }
            throw new Exception(e);
        } finally {
            if (null != httpURLConnection) {
                httpURLConnection.disconnect();
            }
        }
        return null;
    }

}
