package cn.inbs.blockchain.common.utils;

import cn.inbs.blockchain.common.constants.CommonConstants;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.common.utils
 * @ClassName:
 * @Date: 2018年05月07-16:09
 * @Author: createBy:zhangmingyang
 **/
public class HttpClientUtil {
    /**
     * post 请求
     *
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static HttpResponse doPostRequest(String url, List<NameValuePair> nvps) throws IOException {
        CloseableHttpClient httpClient = getHttpClient();
        HttpPost post = new HttpPost(url);

        //设置配置参数
        post.setConfig(setRequestConfig());

        //设置请求业务参数
        if (null != nvps) {
            post.setEntity(new UrlEncodedFormEntity(nvps, CommonConstants.COMMON_ENCODING));
        }

        return httpClient.execute(post);
    }


    /**
     * 获取httpclient
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        //创建http
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        //连接池设置
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(20); //每个主机的最大并行链接数
        connectionManager.setMaxTotal(100);          //客户端总并行链接最大数
        httpClientBuilder.setConnectionManager(connectionManager);
        return httpClientBuilder.build();
    }

    /**
     * 设置请求配置参数
     *
     * @return
     */
    private static RequestConfig setRequestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(20 * 1000)//创建链接时间
                .setConnectionRequestTimeout(20 * 1000)//获取链接时间
                .setSocketTimeout(10 * 1000) // 数据传输的最长时间
                .build();
    }
}
