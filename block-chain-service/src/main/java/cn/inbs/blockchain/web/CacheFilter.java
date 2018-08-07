package cn.inbs.blockchain.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdoframework.cdolib.data.cdo.CDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 缓存拦截器
 * @Package: cn.inbs.blockchain.web
 * @ClassName: CacheFilter
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/31 19:54
 * @Version: 1.0
 */
public class CacheFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(CacheFilter.class);
    private long lCacheTime;

    @Override
    public void init(FilterConfig config) {
        this.lCacheTime = 300;//默认缓存5分钟

        String strCacheTime = config.getInitParameter("CacheTime");
        try {
            this.lCacheTime = Long.parseLong(strCacheTime);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("init CacheFilter failed , cause by:", e);
            }
        }
        this.lCacheTime *= 1000;//转成毫秒
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        long lNow = System.currentTimeMillis();
        long lLastModifiedTime = req.getDateHeader("If-Modified-Since");
        long lTimeDiff = lNow - lLastModifiedTime;
        if (lTimeDiff < lCacheTime) {//缓存未过期
            long lExpireTime = lLastModifiedTime + lCacheTime;
            resp.setDateHeader("Expires", lExpireTime);
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            return;
        }

        //缓存过期
        long lExpireTime = lNow + lCacheTime;
        resp.setDateHeader("Date", lNow);
        resp.setDateHeader("Expires", lExpireTime);
        resp.setDateHeader("Retry-After", lExpireTime);
        resp.setHeader("Cache-Control", "public");
        resp.setDateHeader("Last-Modified", lNow);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    public static void main(String[] args) {
        CDO cdo = new CDO();
        CDO[] arrc = new CDO[5];
        List<String> list = new ArrayList<>();
        for (int i = 0; i < arrc.length; i++) {
            CDO c = new CDO();
            c.setStringValue(i + "", "val" + i);
            arrc[i] = c;
            list.add("val" + i);
        }

        cdo.setObjectValue("arr2", list);
        System.out.println(cdo.toXML());
    }
}
