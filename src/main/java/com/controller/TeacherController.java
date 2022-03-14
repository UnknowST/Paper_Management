package com.controller;

import com.daomain.*;
import com.service.TeacherService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private Message message;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/add_project")
    public Message test1(Projectinfo projectinfo, HttpServletRequest request){

        //获取用户信息
        Userinfo userinfo=(Userinfo) request.getSession().getAttribute("userinfo");
        if(userinfo==null){
            message.setStatus(0);
            message.setMsg("请先登录");
            return  message;
        }
        Staffinfo staffinfo=(Staffinfo) request.getSession().getAttribute("staffinfo");
        System.out.println(staffinfo);
        projectinfo.setInfowriteman(userinfo.getUsername());
        projectinfo.setManageman(staffinfo.getName());
        int flag=teacherService.insert_project(projectinfo);
        if(flag==1)
        {
            message.setStatus(1);
            message.setMsg("上传成功!");
        }
        else
        {
            message.setStatus(0);
            message.setMsg("上传失败!");
        }
        return message;
    }

    @RequestMapping("/find_all")
    public JSONObject test2(Integer limit, Integer page,HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        List<Projectinfo> list =teacherService.select_all((String) request.getSession().getAttribute("username"));
        // 开发环境下
       // List<Projectinfo> list =teacherService.select_all("321");
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
    @RequestMapping("/delete_p")
    public Message test3(String id[]){
        int flag=1;
        for (String s : id) {
            flag=teacherService.delete_project(s);
            if(flag==0){
                message.setStatus(0);
                return message;
            }
        }
        message.setStatus(1);
        return message;
    }

    @RequestMapping("/update_p")
    public Message test4(Projectinfo projectinfo){
        message.setStatus(teacherService.update_p(projectinfo));
        return message;
    }

    @RequestMapping("/confirmlist")
    public JSONObject test5(Integer limit, Integer page,HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        List<Projectinfo> list =teacherService.confirmList((String) request.getSession().getAttribute("username"));
        // 开发环境下
        //List<Projectinfo> list =teacherService.confirmList("321");
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


    @RequestMapping("/picklist")
    public JSONObject test6(Integer limit, Integer page,String pmid,HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();


        List<Staffinfo> list =teacherService.pickList(pmid);
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

    @RequestMapping("/confirmPro")
    public Message test7(String pmid,String appuser){
         int flag=teacherService.confirmPro(pmid, appuser);
         if(flag==1){
             message.setStatus(1);
             message.setMsg("确认成功!");
         }else{
             message.setStatus(0);
             message.setMsg("确认失败!");
         }
         return message;
    }

    @RequestMapping("/proconfirmlist")
    public JSONObject test8(Integer limit, Integer page,HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        List<Projectinfo> list =teacherService.ProconfirmList((String) request.getSession().getAttribute("username"));
        // 开发环境下
       // List<Projectinfo> list =teacherService.ProconfirmList("321");
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

    @RequestMapping("/getuserinfo")
    public Staffinfo test9(String pmid){
        return teacherService.userinfo(pmid);
    }

    @RequestMapping("/filelist")
    public List<Document> test10(String pmid){
        return teacherService.fileList(pmid);
    }

    /**
     * 返回待审核 列表
     * @param limit
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/projectno")
    public JSONObject test11(Integer limit, Integer page,HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        //List<Projectinfo> list =teacherService.select_all((String) request.getSession().getAttribute("username"));
         Staffinfo staffinfo= (Staffinfo) request.getSession().getAttribute("staffinfo");
         String dept=staffinfo.getDept();
        // 开发环境下
        List<Projectinfo> list =teacherService.projectinfoNo(dept);
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

    /**
     * 返回待审核 列表
     * @param limit
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/projectyes")
    public JSONObject test12(Integer limit, Integer page,HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        //List<Projectinfo> list =teacherService.select_all((String) request.getSession().getAttribute("username"));
        Staffinfo staffinfo= (Staffinfo) request.getSession().getAttribute("staffinfo");
        String dept=staffinfo.getDept();
        // 开发环境下
        List<Projectinfo> list =teacherService.projectinfoYes(dept);
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
    /**
     * 返回待审核 列表
     * @param limit
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/projectre")
    public JSONObject test13(Integer limit, Integer page,HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        //List<Projectinfo> list =teacherService.select_all((String) request.getSession().getAttribute("username"));
        Staffinfo staffinfo= (Staffinfo) request.getSession().getAttribute("staffinfo");
        String dept=staffinfo.getDept();
        // 开发环境下
        List<Projectinfo> list =teacherService.projectinfoRe(dept);
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

    @RequestMapping("/updatestate")
    public Message test14(String id,String projectstate,String contractsigndate){
        int flag=teacherService.updatState(id, projectstate, contractsigndate);
        if(flag==1){
            message.setStatus(1);
            message.setMsg("success!");
        }else{
            message.setStatus(0);
            message.setMsg("fail!");
        }
        return message;
    }

}
