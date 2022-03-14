package com.service;

import com.daomain.Staffinfo;
import com.daomain.Userinfo;

public interface LoginService {
    /*用户登录*/
    Userinfo login(String username,String password);
    /**
     * 登录成功返回用户信息
     * @param userid
     * @return
     */
    Staffinfo select_info(String username);
    /**
     * 查询用户角色
     * @param roleid
     * @return
     */
    String roletype(String roleid);
}
