package cn.inbs.blockchain.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.web
 * @ClassName: CallBackServlet
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/31 19:59
 * @Version: 1.0
 */
@SuppressWarnings("serial")
public class CallBackServlet extends HttpServlet {

    public CallBackServlet() {
        super();
    }


    @Override
    public void init() {

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String callBack = request.getParameter("backData");

        //https://www.leavesongs.com/chrome-xss-auditor-bypass-collection.html?utm_source=tuicool&utm_medium=referral
        //chrome xxs安全机制绕过
        //<script type="text/javascript">window.parent.CKEDITOR.tools.callFunction(0, 'http://file.inbs.cn/img/201709/e142987666ab453aa49065db6a827acf.png', '');</script>
        out.print(callBack);
        out.flush();
        out.close();
    }

    @Override
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
    }


}
