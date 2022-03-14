package com.controller;

import com.daomain.Message;
import com.daomain.Projectinfo;
import com.daomain.Staffinfo;
import com.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    Message message;
    @Autowired
    AdminService adminService;

    @RequestMapping("/userlist")
    public JSONObject test1(@Param("rolename") String rolename,@Param("staffinfo") Staffinfo staffinfo, Integer limit, Integer page, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        System.out.println(rolename);
      //  String name,String username,String dept,String mobilephone
//        Staffinfo staffinfo=new Staffinfo();
//        staffinfo.setName(name);
//        staffinfo.setUsername(username);
//        staffinfo.setMobilephone(mobilephone);
//        staffinfo.setDept(dept);
        List<Staffinfo> list =adminService.userList(rolename, staffinfo);
        List<Staffinfo> list1=new ArrayList<>();

        for(int i=(page-1)*limit,j=1;i<list.size()&&j<=limit;++i,++j){

            list1.add(list.get(i));
        }
        //PageInfo<News> data = new PageInfo<News>(list1);
        jsonObject.put("data",list1);
        jsonObject.put("code",0);
        jsonObject.put("count",list.size());
        jsonObject.put("msg","数据请求成功！");

        return jsonObject;

    }

    @RequestMapping("/adduser")
    public  Message test2(Staffinfo staffinfo,String rolename){
        int flag= adminService.addUser(staffinfo, rolename);
        if(flag==3){
            message.setStatus(0);
            message.setMsg("账号已经存在!");
        }
       else if(flag==1){
            message.setStatus(1);
            message.setMsg("success!");
        }
        else {
            message.setStatus(0);
            message.setMsg("fail!");
        }
        return message;
    }

    @RequestMapping("/deleteuser")
    public Message test3(String username[]){
        for (String s : username) {
            int flag= adminService.deleteUser(s);
            if(flag==-1){
                message.setStatus(0);
                message.setMsg("fail!");
            }
        }
        message.setStatus(1);
        message.setMsg("success!");
        return message;
    }

    @RequestMapping("resetpassword")
    public Message test4(String username){
        int flag= adminService.resetPassword(username);
        if(flag==1){
            message.setStatus(1);
            message.setMsg("密码重置为:888888");
        }else{
            message.setStatus(0);
            message.setMsg("fail!");
        }
        return message;
    }

    @RequestMapping("/updateinfo")
    public Message test5(Staffinfo staffinfo,String rolename){
        int flag= adminService.updateuser(staffinfo, rolename);
        if(flag==1){
            message.setStatus(1);
            message.setMsg("success!");
        }
        else{
            message.setStatus(0);
            message.setMsg("fail!");
        }
        return message;
    }

    @RequestMapping("/prolist")
    public JSONObject test6(Projectinfo projectinfo, Integer limit, Integer page, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        List<Projectinfo> list =adminService.proList(projectinfo);
        List<Projectinfo> list1=new ArrayList<>();
        for(int i=(page-1)*limit,j=1;i<list.size()&&j<=limit;++i,++j){

            list1.add(list.get(i));
        }
        //PageInfo<News> data = new PageInfo<News>(list1);
        jsonObject.put("data",list1);
        jsonObject.put("code",0);
        jsonObject.put("count",list.size());
        jsonObject.put("msg","数据请求成功！");

        return jsonObject;

    }

    @RequestMapping("/deletepro")
    public Message test7(String id[]){
        for (String s : id) {
            int flag= adminService.deletePro(s);
            if(flag==-1){
                message.setStatus(0);
                message.setMsg("fail!");
            }

        }
        message.setStatus(1);
        message.setMsg("success!");
        return message;
    }
}
