package com.daomain;

import org.springframework.stereotype.Component;

@Component
public class Userinfo {
    String userid,roleid,username,password,userstate,staffid,fig;
    Staffinfo staffinfo;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserstate() {
        return userstate;
    }

    public void setUserstate(String userstate) {
        this.userstate = userstate;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public String getFig() {
        return fig;
    }

    public void setFig(String fig) {
        this.fig = fig;
    }

    public Staffinfo getStaffinfo() {
        return staffinfo;
    }

    public void setStaffinfo(Staffinfo staffinfo) {
        this.staffinfo = staffinfo;
    }

    @Override
    public String toString() {
        return "Userinfo{" +
                "userid='" + userid + '\'' +
                ", roleid='" + roleid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userstate='" + userstate + '\'' +
                ", staffid='" + staffid + '\'' +
                ", fig='" + fig + '\'' +
                ", staffinfo=" + staffinfo +
                '}';
    }
}
