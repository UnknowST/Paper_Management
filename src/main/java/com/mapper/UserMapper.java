package com.mapper;

import com.daomain.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper
{
    /*查询全部用户*/
    List<Userinfo> findall();
}
