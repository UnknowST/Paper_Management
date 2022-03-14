package com.controller;

import com.daomain.Message;
import com.daomain.Staffinfo;
import com.daomain.Userinfo;
import com.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    Message message;
    @Autowired
    LoginService loginService;


    @RequestMapping("/checkcode")

    public void test1(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 服务器通知浏览器不要缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");

        // 在内存中创建一个长80，宽30的图片，默认黑色背景
        // 参数一：长
        // 参数二：宽
        // 参数三：颜色
        int width = 80;
        int height = 40;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取画笔
        Graphics g = image.getGraphics();
        // 设置画笔颜色为灰色
        g.setColor(Color.GRAY);
        // 填充图片
        g.fillRect(0, 0, width, height);

        // 产生4个随机验证码，12Ey
        String checkCode = getCheckCode();
        // 将验证码放入HttpSession中
        request.getSession().setAttribute("CHECKCODE_SERVER", checkCode);

        // 设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        // 设置字体的小大
        g.setFont(new Font("黑体", Font.BOLD, 24));
        // 向图片上写入验证码
        g.drawString(checkCode, 15, 25);

        // 将内存中的图片输出到浏览器
        // 参数一：图片对象
        // 参数二：图片的格式，如PNG,JPG,GIF
        // 参数三：图片输出到哪里去
        ImageIO.write(image, "PNG", response.getOutputStream());
    }

    /**
     * 产生4位随机字符串
     */
    private String getCheckCode() {
        String base = "0123456789ABCDEFGabcdefg";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= 4; i++) {
            // 产生0到size-1的随机值
            int index = r.nextInt(size);
            // 在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            // 将c放入到StringBuffer中去
            sb.append(c);
        }
        return sb.toString();
    }

    @RequestMapping("/user")
    public Message test1(String username,String password,String roleid,String captcha, HttpServletRequest request){
        //获取验证码session
        String code = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        /*转为小写*/
        code = code.toLowerCase();
        captcha = captcha.toLowerCase();
        if(code.compareTo(captcha)!=0) {
            message.setStatus(0);
            message.setMsg("验证码错误！");
            return message;
        }
        Userinfo userinfo=loginService.login(username, password);
        if(userinfo==null){
            message.setMsg("账号或密码错误！");
            message.setStatus(0);

        }else
        {


            request.getSession().setAttribute("userinfo",userinfo);
            //登录成功 获取用户的详细信息
            Staffinfo staffinfo=loginService.select_info(username);
            //查询用户角色类型
            message.setType(loginService.roletype(userinfo.getRoleid()));
            //摸除去密码 敏感信息
            staffinfo.setPassword("");
            request.getSession().setAttribute("staffinfo",staffinfo);
            request.getSession().setAttribute("username",userinfo.getUsername());
            userinfo.setPassword("");
            message.setObject(userinfo);
            message.setStatus(1);
            message.setMsg("登录成功！");
        }
        return message;
    }

    @RequestMapping("/exit")
    public void test2(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();

    }

    @RequestMapping("/getinfo")
    public Staffinfo test3(String username){
        return loginService.select_info(username);
    }


}
