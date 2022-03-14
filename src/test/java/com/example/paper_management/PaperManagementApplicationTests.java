package com.example.paper_management;

import com.daomain.Userinfo;
import com.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PaperManagementApplicationTests {

    @Autowired
    UserMapper mp;

    @Test
    void contextLoads() {


        List<Userinfo> list=mp.findall();
        System.out.println(list.toString());
    }

}
