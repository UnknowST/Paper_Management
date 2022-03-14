package com.mapper;


import com.daomain.Staffinfo;
import com.daomain.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginMapper {
    /*用户登录*/
    Userinfo login(@Param("username") String username, @Param("password") String password);

    /**
     * 登录成功返回用户信息
     * @param username
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
