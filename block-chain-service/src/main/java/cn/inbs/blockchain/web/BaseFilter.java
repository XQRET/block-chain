package cn.inbs.blockchain.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * @Description:
 * @Package: cn.inbs.blockchain.web
 * @ClassName: BaseFilter
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/31 19:22
 * @Version: 1.0
 */
public class BaseFilter implements Filter {

    protected ServletContext application;

    @Override
    public void init(FilterConfig filterConfig) {
        this.application = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
