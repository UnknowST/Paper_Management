package com.service.impl;

import com.daomain.Staffinfo;
import com.mapper.PublicMapper;
import com.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("PublicService")
@Transactional(propagation= Propagation.REQUIRED)
public class PublicServiceimp implements PublicService {
    @Autowired
    private PublicMapper publicMapper;
    @Override
    public int updateInfo(Staffinfo staffinfo) {
        return publicMapper.updateInfo(staffinfo);
    }

    @Override
    public int modifPassword(String username, String oldpassword, String newspassword) {
        return publicMapper.modifPassword(username, oldpassword, newspassword);
    }
}
