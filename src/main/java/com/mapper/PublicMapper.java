package com.mapper;

import com.daomain.Staffinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PublicMapper {
    /**
     * 修改用户信息
     * @return
     */
    int updateInfo(Staffinfo staffinfo);

    /**
     * 修改密码
     * @param username
     * @param oldpassword
     * @param newspassword
     * @return
     */
    int modifPassword(@Param("username")String username,@Param("oldpassword")String oldpassword,@Param("newpassword")String newspassword);


}
