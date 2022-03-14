package com.daomain;

import org.springframework.stereotype.Component;

@Component
public class Message {
    //状态码
   private Integer status;
   //返回码 和 信息   type 记录用户类型 {学生，老师，系主任，管理员)
   private String  code,msg,type;
   private Object object;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
