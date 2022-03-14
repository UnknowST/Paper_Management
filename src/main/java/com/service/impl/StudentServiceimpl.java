package com.service.impl;

import com.daomain.Document;
import com.daomain.Procurementorder;
import com.daomain.Projectinfo;
import com.mapper.StudentMapper;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("StudentService")
@Transactional(propagation= Propagation.REQUIRED)
public class StudentServiceimpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List<Projectinfo> find_all(String projectname, String manageman, String requirecontent, String proconstractcode,String username) {
        return studentMapper.find_all(projectname, manageman, requirecontent, proconstractcode,username);
    }

    @Override
    public int checkNum(String appuser) {
        return studentMapper.checkNum(appuser);
    }

    @Override
    public int insertOrder(String pmid, String appuser) {
        return studentMapper.insertOrder(pmid, appuser);
    }

    @Override
    public List<Procurementorder> selectedPro(String appuser) {
        return studentMapper.selectedPro(appuser);
    }

    @Override
    public Projectinfo findOne(String id) {
        return studentMapper.findOne(id);
    }

    @Override
    public Procurementorder finishOrder(String appuser) {
        return studentMapper.finishOrder(appuser);
    }

    @Override
    public int uploadFile(Document document) {
        return studentMapper.uploadFile(document);
    }

    @Override
    public List<Document> findAll(String createusername) {
        return studentMapper.findAll(createusername);
    }

    @Override
    public Document findBytype(String createusername, String filetype) {
        return studentMapper.findBytype(createusername, filetype);
    }

    @Override
    public int deleteFile(String id) {
        return studentMapper.deleteFile(id);
    }

    @Override
    public int checkFile(String createusername, String filetype) {
        Document document=studentMapper.checkFile(createusername, filetype);
        if(document!=null) return 1;
        return 0;
    }
}
