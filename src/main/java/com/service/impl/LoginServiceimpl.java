package com.service.impl;

import com.daomain.Staffinfo;
import com.daomain.Userinfo;
import com.mapper.LoginMapper;
import com.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("LoginService")
@Transactional(propagation= Propagation.REQUIRED)
public class LoginServiceimpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public Userinfo login(String username, String password) {
        return loginMapper.login(username, password);
    }

    @Override
    public Staffinfo select_info(String username) {
        return loginMapper.select_info(username);
    }

    @Override
    public String roletype(String roleid) {
        return loginMapper.roletype(roleid);
    }
}
