package com.atbanfei.ssm.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 班费汇总
 */




public class ClassFee {
    private Integer f_id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date f_date;
    private String f_title;
    private Integer g_id;
    private Double f_gross;        //班费总额
    private String f_status;

    private Grade grade;


    public ClassFee() {
    }

    public ClassFee(Integer f_id, Date f_date, String f_title, Integer g_id, Double f_gross, String f_status, Grade grade) {
        this.f_id = f_id;
        this.f_date = f_date;
        this.f_title = f_title;
        this.g_id = g_id;
        this.f_gross = f_gross;
        this.f_status = f_status;
        this.grade = grade;
    }

    public Integer getF_id() {
        return f_id;
    }

    public void setF_id(Integer f_id) {
        this.f_id = f_id;
    }

    public Date getF_date() {
        return f_date;
    }

    public void setF_date(Date f_date) {
        this.f_date = f_date;
    }

    public String getF_title() {
        return f_title;
    }

    public void setF_title(String f_title) {
        this.f_title = f_title;
    }

    public Integer getG_id() {
        return g_id;
    }

    public void setG_id(Integer g_id) {
        this.g_id = g_id;
    }

    public Double getF_gross() {
        return f_gross;
    }

    public void setF_gross(Double f_gross) {
        this.f_gross = f_gross;
    }

    public String getF_status() {
        return f_status;
    }

    public void setF_status(String f_status) {
        this.f_status = f_status;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "ClassFee{" +
                "f_id=" + f_id +
                ", f_date=" + f_date +
                ", f_title='" + f_title + '\'' +
                ", g_id=" + g_id +
                ", f_gross=" + f_gross +
                ", f_status='" + f_status + '\'' +
                ", grade=" + grade +
                '}';
    }
}
