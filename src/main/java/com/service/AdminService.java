package com.service;

import com.daomain.Projectinfo;
import com.daomain.Staffinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminService {
    /**
     *  按类别查询 用户信息
     * @param rolename  用户类别
     * @return
     */
    List<Staffinfo> userList(@Param("rolename") String rolename , Staffinfo staffinfo);

    /**
     * 添加用户信息
     * @param staffinfo
     * @param rolename
     * @return
     */
    int addUser(Staffinfo staffinfo,String rolename);

    /**
     * 删除账号
     * @param username
     * @return
     */
    int deleteUser(String username);
    /**
     *  重置密码
     * @param username
     * @return
     */
    int resetPassword(String username);

    /**
     * 更新用户信息
     * @param staffinfo
     * @param rolename
     * @return
     */
    int updateuser(Staffinfo staffinfo,String rolename);
    /**
     * 检索 选题列表
     * @param projectinfo
     * @return
     */
    List<Projectinfo> proList(Projectinfo projectinfo);

    /**
     * 删除 某一选题 同时删除所有选题记录和 相关文件
     * @param id
     * @return
     */
    int deletePro(String id);

}
