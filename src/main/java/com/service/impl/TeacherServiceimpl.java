package com.service.impl;

import com.daomain.Document;
import com.daomain.Procurementorder;
import com.daomain.Projectinfo;
import com.daomain.Staffinfo;
import com.mapper.TeacherMapper;
import com.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("TeacherService")
@Transactional(propagation= Propagation.REQUIRED)
public class TeacherServiceimpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public int insert_project(Projectinfo projectinfo) {
        return teacherMapper.insert_project(projectinfo);
    }

    @Override
    public List<Projectinfo> select_all(String infowriteman) {
        return teacherMapper.select_all(infowriteman);
    }

    @Override
    public int delete_project(String id) {
        return teacherMapper.delete_project(id);
    }

    @Override
    public int update_p(Projectinfo projectinfo) {
        return teacherMapper.update_p(projectinfo);
    }

    @Override
    public List<Projectinfo> confirmList(String infowriteman) {
        return teacherMapper.confirmList(infowriteman);
    }

    @Override
    public List<Staffinfo> pickList(String pmid) {
        return teacherMapper.pickList(pmid);
    }

    @Override
    public int confirmPro(String pmid, String appuser) {
        //需要加一个查询  查询这个学生是否已经又确认的选题
        Procurementorder procurementorder= teacherMapper.checkUser(appuser);
        //已经有选题了
        if(procurementorder!=null){
            return 0;
        }
        int flag1= teacherMapper.confirmPro(pmid, appuser);
        int flag2=teacherMapper.confirmPro_2(pmid);
        if(flag1==1&&flag2==1){
            return 1;
        }
        else return 0;
    }

    @Override
    public List<Projectinfo> ProconfirmList(String infowriteman) {
        return teacherMapper.ProconfirmList(infowriteman);
    }

    @Override
    public Staffinfo userinfo(String pmid) {
        return teacherMapper.userinfo(pmid);
    }

    @Override
    public List<Document> fileList(String pmid) {
        return teacherMapper.fileList(pmid);
    }

    @Override
    public List<Projectinfo> projectinfoNo(String proconstractcode) {
        return teacherMapper.projectinfoNo(proconstractcode);
    }

    @Override
    public List<Projectinfo> projectinfoYes(String proconstractcode) {
        return teacherMapper.projectinfoYes(proconstractcode);
    }

    @Override
    public List<Projectinfo> projectinfoRe(String proconstractcode) {
        return teacherMapper.projectinfoRe(proconstractcode);
    }

    @Override
    public int updatState(String id, String projectstate, String contractsigndate) {
        return teacherMapper.updatState(id, projectstate, contractsigndate);
    }


}
