package cn.inbs.blockchain.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.inbs.blockchain.common.utils.StringUtils;
import com.cdoframework.cdolib.security.DES;

import cn.inbs.blockchain.common.utils.Constant;
import cn.inbs.blockchain.common.utils.Utility;

/**
 * @Description: 验证码
 * @Package: cn.inbs.blockchain.web
 * @ClassName: VerifyCodeServlet
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/31 20:00
 * @Version: 1.0
 */
@SuppressWarnings("serial")
public class VerifyCodeServlet extends HttpServlet {

    private ServletContext application;

    private static final char[] CODE_SEQUENCE = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'i', 'J', 'K',
            'L', 'm', 'N', 'P', 'q', 'R', 'S', 'T', 'U', 'V', 'w',
            'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'};

    private int width = 80;// 验证码图片的宽度。
    private int height = 30;// 验证码图片的高度。
    private int codeCount = 4;// 验证码字符个数
    private int x = 0;
    private int fontHeight; // 字体高度
    private int codeY;


    /**
     * 初始化验证图片属性
     */
    @Override
    public void init() {
        //初始化上下文
        this.application = this.getServletContext();

        //从web.xml中获取初始信息,将配置的信息转换成数值
        try {
            String strWidth = this.getInitParameter("width"); // 宽度
            String strHeight = this.getInitParameter("height");// 高度
            String strCodeCount = this.getInitParameter("codeCount");// 字符个数

            //宽
            if (StringUtils.isNotEmpty(strWidth)) {
                width = Integer.parseInt(strWidth);
            }

            //高
            if (StringUtils.isNotEmpty(strHeight)) {
                height = Integer.parseInt(strHeight);
            }

            //验证码个数
            if (StringUtils.isNotEmpty(strCodeCount)) {
                codeCount = Integer.parseInt(strCodeCount);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        x = width / (codeCount + 1);
        fontHeight = height - 2;
        codeY = height - 4;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws java.io.IOException {

        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();

        // 创建一个随机数生成器类
        Random random = new Random();

        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 创建字体，字体的大小应该根据图片的高度来定。

        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
        // 设置字体。
        g.setFont(font);

        // 画边框。
        g.setColor(new Color(221, 225, 233));
        g.drawRect(0, 0, width - 1, height - 1);

        // 随机产生160条干扰线，使图象中的认证码不易被其它程序探测到。
        g.setColor(Color.BLACK);
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuilder randomCode = new StringBuilder();
        int red, green, blue;

        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String strRand = String.valueOf(CODE_SEQUENCE[random.nextInt(CODE_SEQUENCE.length)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(5) * 12;
            green = random.nextInt(5) * 12;
            blue = random.nextInt(255);

            // 用随机产生的颜色将验证码绘制到图像中。
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (i + 1) * x - 8, codeY);

            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }

        // 将四位数字的验证码保存到Cookie中。
        // 先清除原有的cookie
        Utility.clearCookie(resp, req, Constant.COOKIE_VERIFYCODE_NAME);
        DES des = Utility.getDES(application, req.getSession(), Constant.VERIFYCODE_DES_KEY);
        Cookie cVerifycode = new Cookie(Constant.COOKIE_VERIFYCODE_NAME, Utility.DESEncrypt(randomCode.toString(), des));
        cVerifycode.setPath("/");
        resp.addCookie(cVerifycode);


        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setHeader("P3P", "CP=CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR");

        resp.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();
    }
}
