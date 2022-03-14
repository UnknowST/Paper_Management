package com.service;

import com.daomain.Document;
import com.daomain.Procurementorder;
import com.daomain.Projectinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {
    /**
     * 查询学生所属部门下已经通过审核的选题
     *
     * @param projectname
     * @param manageman
     * @param requirecontent
     * @param proconstractcode
     * @return
     */
    List<Projectinfo> find_all(@Param("projectname") String projectname, @Param("manageman") String manageman, @Param("requirecontent") String requirecontent, @Param("proconstractcode") String proconstractcode,@Param("username")String username);
    /**
     * 检查学生选题数是否达到上限
     * @param appuser
     * @return
     */
    int checkNum(String appuser);


    /**
     * 添加选题记录
     * @param pmid
     * @param appuser
     * @return
     */
    int insertOrder(@Param("pmid")String pmid,@Param("appuser")String appuser);
    /**
     * 查询已经选题列表
     * @param appuser
     * @return
     */
    List<Procurementorder> selectedPro(String appuser);
    /**
     * 根据ID查询某一个选题的详细信息
     * @param id
     * @return
     */
    Projectinfo findOne(String id);
    /**
     * 查询学生最终选题 只能有一个
     * @param appuser
     * @return
     */
    Procurementorder finishOrder(String appuser);
    /**
     * 文件上传
     * @param document
     * @return
     */
    int uploadFile(Document document);

    /**
     * 根据用户名进行文件查找
     * @param createusername
     * @return
     */
    List<Document> findAll(String createusername);
    /**
     * 根据用户名和文件类型 返回文件信息
     * @param createusername
     * @param filetype
     * @return
     */
    Document findBytype(@Param("createusername")String createusername,@Param("filetype")String filetype);

    /**
     * 根据文件ID删除文件
     * @param id
     * @return
     */
    int deleteFile(String id);

    /**
     * 检查某用户是否已经上传某一类型的文件
     * @param createusername
     * @param filetype
     * @return
     */
    int  checkFile(String createusername,String filetype);

}
