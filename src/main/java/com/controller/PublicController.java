package com.controller;

import com.daomain.Message;
import com.daomain.Staffinfo;
import com.daomain.Userinfo;
import com.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    Message message;
    @Autowired
    PublicService publicService;

    @RequestMapping("/getuserinfo")
    Staffinfo test1(HttpServletRequest request){
        Staffinfo staffinfo= (Staffinfo) request.getSession().getAttribute("staffinfo");
        return staffinfo;
    }
    @RequestMapping("/updateinfo")
    Message test2(Staffinfo staffinfo,HttpServletRequest request){
        String username= (String) request.getSession().getAttribute("username");
        staffinfo.setUsername(username);

        int flag=publicService.updateInfo(staffinfo);
        if(flag==1){
            Staffinfo staffinfo1= (Staffinfo) request.getSession().getAttribute("staffinfo");
            staffinfo1.setEmail(staffinfo.getEmail());
            staffinfo1.setPostcode(staffinfo.getPostcode());
            staffinfo1.setMobilephone(staffinfo.getMobilephone());
            staffinfo1.setName(staffinfo.getName());
            request.getSession().setAttribute("staffinfo",staffinfo1);
            message.setStatus(1);
            message.setMsg("修改成功!");
        }else{
            message.setMsg("修改失败!");
            message.setStatus(0);
        }
        return message;
    }

    @RequestMapping("/modifpass")
    public Message test3(String oldpassword,String newpassword,HttpServletRequest request){
        String username= (String) request.getSession().getAttribute("username");
        int flag= publicService.modifPassword(username, oldpassword,newpassword);
        if(flag==1){
            message.setStatus(1);
            message.setMsg("修改成功!");
        }else{
            message.setMsg("修改失败,请检查原密码是否正确！");
            message.setStatus(0);
        }
        return message;
    }
}
