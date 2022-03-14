package com.service.impl;

import com.daomain.Procurementorder;
import com.daomain.Projectinfo;
import com.daomain.Staffinfo;
import com.daomain.Userinfo;
import com.mapper.AdminMapper;
import com.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("AdminService")
@Transactional(propagation= Propagation.REQUIRED)
public class AdminServiceimpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;


    @Override
    public List<Staffinfo> userList(String rolename, Staffinfo staffinfo) {
        return adminMapper.userList(rolename, staffinfo);
    }

    @Override
    public int addUser(Staffinfo staffinfo, String rolename) {
        //检查username
        Userinfo userinfo1=adminMapper.checkId(staffinfo.getUsername());
        if(userinfo1!=null){
            return 3;
        }
        //获取roleid
        String roleid=adminMapper.getRoleid(rolename);
        //插入 staffinfo 并返回主键
        int flag1=adminMapper.insertStaffinfo(staffinfo);
        if(flag1==1){
            Userinfo userinfo=new Userinfo();
            userinfo.setUsername(staffinfo.getUsername());
            userinfo.setRoleid(roleid);
            userinfo.setStaffid(staffinfo.getUserid());
            int flag2=adminMapper.insertUserinfo(userinfo);
            if(flag2==1) return 1;
        }
        return 0;
    }

    @Override
    public int deleteUser(String username) {
        //1.查看学生是什么类型
        String rolename= adminMapper.getRolename(username);
        //学生账号
        if(rolename.equals("学生")==true){
            //获取选题列表
            List<Procurementorder> list=adminMapper.byAppuser(username);
            for (Procurementorder procurementorder : list) {
                if(procurementorder.getState().equals("已确认")==true){
                    //修改选题状态
                    try {
                        adminMapper.modifState(procurementorder.getId());
                    } catch (Exception e) {
                        return -1;
                    }
                }
            }
            //删除所有已选记录
            try {
                adminMapper.deletePro(username);
            } catch (Exception e) {
                return -1;
            }
            //删除所有文件记录
            try {
                adminMapper.deleteFile(username);
            } catch (Exception e) {
                return -1;
            }
        }
        //删除详细信息
        try {
            adminMapper.deleteStaffinfo(username);
        } catch (Exception e) {
            return -1;
        }
        //删除账号
        try {
            adminMapper.deleteUserinfo(username);
        } catch (Exception e) {
            return -1;
        }
        return 1;
    }

    @Override
    public int resetPassword(String username) {
        return adminMapper.resetPassword(username);
    }

    @Override
    public int updateuser(Staffinfo staffinfo, String rolename) {

        try {
            adminMapper.updateStaffinfo(staffinfo);
            adminMapper.updateRoleid(staffinfo.getUsername(),rolename);
        } catch (Exception e) {
            return -1;
        }
        return 1;
    }

    @Override
    public List<Projectinfo> proList(Projectinfo projectinfo) {
        return adminMapper.proList(projectinfo);
    }

    @Override
    public int deletePro(String id) {
        try {
            //1.删除所有相关的选题记录
            adminMapper.byPmid(id);
            //2.删除所有传文件
            adminMapper.byPid(id);
            //3.删除选题
            adminMapper.deletepro(id);
        } catch (Exception e) {
            return -1;
        }
        return 1;
    }
}
