package com.mapper;

import com.daomain.Procurementorder;
import com.daomain.Projectinfo;
import com.daomain.Staffinfo;
import com.daomain.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Mapper
@Repository
public interface AdminMapper {

    /**
     *  按类别查询 用户信息
     * @param rolename  用户类别
     * @return
     */
    List<Staffinfo> userList(@Param("rolename") String rolename ,@Param("staffinfo") Staffinfo staffinfo);

    /**
     *  添加用户详细信息  并带回主键
     * @param staffinfo
     * @return
     */
    int insertStaffinfo(Staffinfo staffinfo);

    /**
     * 添加用户账号信息
     * @param userinfo
     * @return
     */
    int insertUserinfo(Userinfo userinfo);

    /**
     *  根据rolename 返回roleid
     * @param rolename
     * @return
     */
    String getRoleid(String rolename);

    /**
     * 检查用户账号是否已经存在
     * @param username
     * @return
     */
    Userinfo checkId(String username);

    /**
     * 删除用户 详细信息
     * @param username
     * @return
     */
    int deleteStaffinfo(String username);

    /**
     * 删除用户账号
     * @param username
     * @return
     */
    int deleteUserinfo(String username);

    /**
     * 删除学生账号时 一起删除他的选题记录
     * @param appuser
     * @return
     */
    int deletePro(String appuser);

    /**
     * 删除该账号上传的所有文件
     * @param createuserid
     * @return
     */
    int deleteFile(String createuserid);

    /**
     * 返回该账号小的选题列表
     * @param appuser
     * @return
     */
    List<Procurementorder> byAppuser(String appuser);

    /**
     * 返回账号类型
     * @param username
     * @return
     */
    String getRolename(String username);

    /**
     * 修改选题状态
     * @param id
     * @return
     */
    int modifState(String id);

    /**
     *  重置密码
     * @param username
     * @return
     */
    int resetPassword(String username);

    /**
     * 更新用户信息
     * @param staffinfo
     * @return
     */
    int updateStaffinfo(Staffinfo staffinfo);

    /**
     * 修改账号类型
     * @param username
     * @param rolename
     * @return
     */
    int updateRoleid(@Param("username")String username,@Param("rolename")String rolename);

    /**
     * 检索 选题列表
     * @param projectinfo
     * @return
     */
    List<Projectinfo> proList(Projectinfo projectinfo);


    /**
     * 删除 题目
     * @param id
     * @return
     */
    int deletepro(String id);

    /**
     * 根据 选题编号 删除题目
     * @param pmid
     * @return
     */
    int byPmid(String pmid);

    /**
     * 删除某一个选题 的所有文件
     * @param pid
     * @return
     */
    int byPid(String pid);


}
