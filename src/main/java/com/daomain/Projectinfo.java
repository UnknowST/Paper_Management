package com.daomain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Projectinfo {
    String id,projectdept,projectname,projectdtyle,clientman,proconstractcode,projectdemo,projectstate,requirecontent,infowriteman,
            manageman,issubmit,contractsigndate,prokectfr,projectchange,projectfujian;
    @DateTimeFormat(pattern = "yyyy-MM-dd")//页面写入数据库时格式化
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")  //查询输出时格式转换
    Date infowritedate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectdept() {
        return projectdept;
    }

    public void setProjectdept(String projectdept) {
        this.projectdept = projectdept;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProjectdtyle() {
        return projectdtyle;
    }

    public void setProjectdtyle(String projectdtyle) {
        this.projectdtyle = projectdtyle;
    }

    public String getClientman() {
        return clientman;
    }

    public void setClientman(String clientman) {
        this.clientman = clientman;
    }

    public String getProconstractcode() {
        return proconstractcode;
    }

    public void setProconstractcode(String proconstractcode) {
        this.proconstractcode = proconstractcode;
    }

    public String getProjectdemo() {
        return projectdemo;
    }

    public void setProjectdemo(String projectdemo) {
        this.projectdemo = projectdemo;
    }

    public String getProjectstate() {
        return projectstate;
    }

    public void setProjectstate(String projectstate) {
        this.projectstate = projectstate;
    }

    public String getRequirecontent() {
        return requirecontent;
    }

    public void setRequirecontent(String requirecontent) {
        this.requirecontent = requirecontent;
    }

    public String getInfowriteman() {
        return infowriteman;
    }

    public void setInfowriteman(String infowriteman) {
        this.infowriteman = infowriteman;
    }

    public String getManageman() {
        return manageman;
    }

    public void setManageman(String manageman) {
        this.manageman = manageman;
    }

    public String getIssubmit() {
        return issubmit;
    }

    public void setIssubmit(String issubmit) {
        this.issubmit = issubmit;
    }

    public String getContractsigndate() {
        return contractsigndate;
    }

    public void setContractsigndate(String contractsigndate) {
        this.contractsigndate = contractsigndate;
    }

    public String getProkectfr() {
        return prokectfr;
    }

    public void setProkectfr(String prokectfr) {
        this.prokectfr = prokectfr;
    }

    public String getProjectchange() {
        return projectchange;
    }

    public void setProjectchange(String projectchange) {
        this.projectchange = projectchange;
    }

    public String getProjectfujian() {
        return projectfujian;
    }

    public void setProjectfujian(String projectfujian) {
        this.projectfujian = projectfujian;
    }

    public Date getInfowritedate() {
        return infowritedate;
    }

    public void setInfowritedate(Date infowritedate) {
        this.infowritedate = infowritedate;
    }

    @Override
    public String toString() {
        return "Projectinfo{" +
                "id='" + id + '\'' +
                ", projectdept='" + projectdept + '\'' +
                ", projectname='" + projectname + '\'' +
                ", projectdtyle='" + projectdtyle + '\'' +
                ", clientman='" + clientman + '\'' +
                ", proconstractcode='" + proconstractcode + '\'' +
                ", projectdemo='" + projectdemo + '\'' +
                ", projectstate='" + projectstate + '\'' +
                ", requirecontent='" + requirecontent + '\'' +
                ", infowriteman='" + infowriteman + '\'' +
                ", manageman='" + manageman + '\'' +
                ", issubmit='" + issubmit + '\'' +
                ", contractsigndate='" + contractsigndate + '\'' +
                ", prokectfr='" + prokectfr + '\'' +
                ", projectchange='" + projectchange + '\'' +
                ", projectfujian='" + projectfujian + '\'' +
                ", infowritedate=" + infowritedate +
                '}';
    }
}
