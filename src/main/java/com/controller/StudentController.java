package com.controller;

import com.daomain.*;
import com.service.LoginService;
import com.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private Message message;
    @Autowired
    private StudentService studentService;
    @Autowired
    private LoginService loginService;
    @RequestMapping("/find_all")
    public JSONObject test2(String projectname,String requirecontent,String manageman,Integer limit, Integer page, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        //List<Projectinfo> list =teacherService.select_all((String) request.getSession().getAttribute("username"));
        Staffinfo staffinfo= (Staffinfo) request.getSession().getAttribute("staffinfo");
        String username=(String)request.getSession().getAttribute("username");
        // 开发环境下
        List<Projectinfo> list =studentService.find_all(projectname,requirecontent,manageman,staffinfo.getDept(),username);
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
    @RequestMapping("/pick")
    public Message test3(String pmid,HttpServletRequest request){
        String appuser= (String) request.getSession().getAttribute("username");
        int flag1=studentService.checkNum(appuser);
        if(flag1>=3){
            message.setStatus(0);
            message.setMsg("选题已达上限!");
            return message;
        }
        int flag2= studentService.insertOrder(pmid,appuser);
        if(flag2==1){
            message.setStatus(1);
            message.setMsg("选题成功!");
        }
        else
        {
            message.setMsg("选题失败!");
            message.setStatus(0);
        }
        return message;
    }
    @RequestMapping("/selected")
    public JSONObject test4(Integer limit, Integer page, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        String username=(String)request.getSession().getAttribute("username");
        // 开发环境下
        List<Procurementorder> list =studentService.selectedPro(username);
        List<Procurementorder> list1=new ArrayList<>();

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

    @RequestMapping("/findOne")
    public Projectinfo test5(String id){
        return studentService.findOne(id);
    }
    @RequestMapping("/finshOrder")
    public Procurementorder test6(HttpServletRequest request){
        String username= (String) request.getSession().getAttribute("username");
        Procurementorder pro=studentService.finishOrder(username);
        if(pro==null)
            return null;
        else
            return pro;
    }

    @RequestMapping("findinfo")
    public List<Map<String,Object>> test6(String id){
        Projectinfo pro= studentService.findOne(id);
        Staffinfo info=loginService.select_info(pro.getInfowriteman());
        Map<String,Object> map=new HashMap<>();
        map.put("pro",pro);
        map.put("info",info);
        List<Map<String,Object>> list=new ArrayList<>();
        list.add(map);
        return list;
    }

    @RequestMapping("/uploadFile")
    public Message test7(MultipartFile file,@Param("filetype")String filetype, HttpServletRequest request){


         //检查文件是否为空
        if(file.isEmpty()){
            message.setStatus(0);
            message.setMsg("文件不能为空!");
            return message;
        }
        //检查登录状态
        Staffinfo staffinfo=(Staffinfo) request.getSession().getAttribute("staffinfo");
        if(staffinfo==null){
            message.setStatus(0);
            message.setMsg("请先登录");
            return message;
        }
        //检查是否已经确认选题
        Procurementorder procurementorder=studentService.finishOrder(staffinfo.getUsername());
        if(procurementorder==null){
            message.setStatus(0);
            message.setMsg("您当前还没有已经确认的选题!");
            return message;
        }
        //检查该类型文件是否已经上传
        if(studentService.checkFile(staffinfo.getUsername(),filetype)==1){
            message.setStatus(0);
            message.setMsg("该部分文件已上传,若要重新上传请先删除原来文件!");
            return message;
        }


        //下面才是开始上传文件
        Document document=new Document();
        //获取文件name
        String name=file.getOriginalFilename();
        //获取文件类型
        String type = name.substring(name.lastIndexOf(".") + 1);
        //获取文件保存目录  /WEB-INF/
        String savePath= request.getServletContext().getRealPath("/WEB-INF/upload");
       // String savePath;
        // 获取static目录 获取static就可以完成上传功能了
//        try {
//             savePath = ResourceUtils.getURL("classpath:").getPath() + "static"+File.separator+"upload";
//        } catch (FileNotFoundException e) {
//            message.setStatus(0);
//            message.setMsg("上传失败!");
//            return message;
//        }

        File filepath = new File(savePath);
        //目录不存在 创建目录
        if (!filepath.exists()) {
            filepath.mkdirs();
        }
        //文件真是存储路径
        String path = savePath + File.separator + name;
        System.out.println(path);
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            message.setStatus(0);
            message.setMsg("文件上传失败！");
            return message;
        }
        document.setCreateuserid(staffinfo.getUserid());
        document.setName(name);
        document.setCreateusername(staffinfo.getUsername());
        document.setPath(path);
        document.setFiletype(filetype);
        document.setPid(procurementorder.getPmid());
        int flag=studentService.uploadFile(document);
        if(flag==0){
            message.setMsg("文件上传失败!");
            message.setStatus(0);
        }else
        {
            message.setStatus(1);
            message.setMsg("上传成功!");
        }
        return message;

    }

    @RequestMapping("/findAll")
    public List<Document> test8(HttpServletRequest request){
        String username= (String) request.getSession().getAttribute("username");
        List<Document> list=studentService.findAll(username);
        return list;
    }

    @RequestMapping("/getfile")
    public Message test9(String path, String name, HttpServletResponse response) throws IOException {

        //System.out.println("path----"+path);
        //String testpath="/www/server/tomcat/webapps/paper_manage/WEB-INF/upload";
        //String testname="\\index.html";
        File file = new File(path);
        System.out.println("path----"+file.getPath());
        System.out.println("文件是否存在:  "+file.exists());
        Logger logger = LoggerFactory.getLogger(StudentController. class);
        logger.info("文件是否存在"+String.valueOf(file.exists()));
        logger.info("文件路径"+file.getPath());
        logger.info("文件路径传入的参数"+path);
        if (!file.exists()) {
            message.setStatus(0);
            message.setMsg("你要下载的资源已经被删除！");
            return message;

        }
        //设置响应头，控制浏览器下载该文件 并将文件还原为原始的名称
        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = null;
        try {
            in = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            message.setStatus(0);
            message.setMsg("下载失败！");
            return message;
        }
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while ((len = in.read(buffer)) > 0) {
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();
        message.setStatus(1);
        return message;
    }

    @RequestMapping("/deleteFile")
    public Message test10(String filetype,HttpServletRequest request){
        //获取用户名
        String username= (String) request.getSession().getAttribute("username");
        if(username==null||username==""){
            message.setStatus(0);
            message.setMsg("请先登录");
            return  message;
        }
        //获取文件信息
        Document document=studentService.findBytype(username,filetype);
        File file=new File(document.getPath());
        //删除服务器文件
        if(file.delete()){
            //删除数据库记录
            int flag= studentService.deleteFile(document.getId());
            if(flag==1){
                message.setStatus(1);
                message.setMsg("删除成功!");
            }
            else{
                message.setStatus(0);
                message.setMsg("删除失败!");
            }
        }else{
            //本地文件已经丢失 直接删除数据库记录
            int flag= studentService.deleteFile(document.getId());
            if(flag==1){
                message.setStatus(1);
                message.setMsg("删除成功!");
            }
            else{
                message.setStatus(0);
                message.setMsg("删除失败!");
            }
        }
        return message;
    }



}
