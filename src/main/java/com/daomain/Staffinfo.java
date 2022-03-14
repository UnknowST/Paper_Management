package com.daomain;

import org.springframework.stereotype.Component;

@Component
public class Staffinfo {
    String userid,name,username,sex,dept,password,loginname,idcard,homeaddress,postcode,email,mobilephone,status,sysroleguid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSysroleguid() {
        return sysroleguid;
    }

    public void setSysroleguid(String sysroleguid) {
        this.sysroleguid = sysroleguid;
    }

    @Override
    public String toString() {
        return "staffinfo{" +
                "userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", dept='" + dept + '\'' +
                ", password='" + password + '\'' +
                ", loginname='" + loginname + '\'' +
                ", idcard='" + idcard + '\'' +
                ", homeaddress='" + homeaddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", email='" + email + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", status='" + status + '\'' +
                ", sysroleguid='" + sysroleguid + '\'' +
                '}';
    }
}
