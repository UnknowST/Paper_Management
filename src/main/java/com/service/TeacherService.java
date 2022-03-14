package com.service;

import com.daomain.Document;
import com.daomain.Projectinfo;
import com.daomain.Staffinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherService {
    /**
     * 论文选题上传
     * @param projectinfo
     * @return
     */
    int insert_project(Projectinfo projectinfo);
    /**
     * 查询某一个老师的所有选题
     * @param infowriteman
     * @return
     */
    List<Projectinfo> select_all(String infowriteman);
    /** 删除选题
     * @param id
     * @return
     */
    int delete_project(String id);
    /**
     *  更新选题信息
     * @param projectinfo
     * @return
     */
    int update_p(Projectinfo projectinfo);
    /**
     * 查询某一个老师已经审核通过的选题
     * @param infowriteman
     * @return
     */
    List<Projectinfo> confirmList(String infowriteman);
    /**
     * 查询某一个选题 学生列表
     * @param pmid
     * @return
     */
    List<Staffinfo> pickList(String pmid);
    /**
     * 老师确定选题学生
     * @param pmid
     * @param appuser
     * @return
     */
    int confirmPro(@Param("pmid")String pmid, @Param("appuser")String appuser);
    /**
     * 查询 已经确认了的选题列表
     * @param infowriteman
     * @return
     */
    List<Projectinfo>  ProconfirmList(String infowriteman);

    /**
     * 查询已经确认选题的学生信息
     * @param pmid
     * @return
     */
    Staffinfo userinfo(String pmid);
    /**
     * 查询某一个选题 文件列表
     * @param pmid
     * @return
     */
    List<Document> fileList(String pmid);
    /**
     * 查询待审核的 选题
     * @return
     */
    List<Projectinfo> projectinfoNo(String proconstractcode);
    /**
     * 查询已审核通过的  选题
     * @return
     */
    List<Projectinfo> projectinfoYes(String proconstractcode);
    /**
     * 查询被驳回 选题
     * @return
     */
    List<Projectinfo> projectinfoRe(String proconstractcode);

    /**
     * 修改选题的审核状态
     * @param id
     * @param projectstate
     * @param contractsigndate
     * @return
     */
    int updatState(@Param("id")String id,@Param("projectstate") String projectstate,@Param("contractsigndate") String contractsigndate);


}
