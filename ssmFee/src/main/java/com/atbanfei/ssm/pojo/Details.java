package com.atbanfei.ssm.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 班费详细
 */




public class Details {
    private Integer d_id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date d_date;
    private Integer f_id;
    private Integer s_id;
    private Integer g_id;
    private Double d_money;


    private Grade grade;
    private Student student;
    private ClassFee classFee;


    public Details() {
    }

    public Details(Integer d_id, Date d_date, Integer f_id, Integer s_id,  Integer g_id, Double d_money) {
        this.d_id = d_id;
        this.d_date = d_date;
        this.f_id = f_id;
        this.s_id = s_id;
        this.g_id = g_id;
        this.d_money = d_money;
    }

    public Integer getD_id() {
        return d_id;
    }

    public void setD_id(Integer d_id) {
        this.d_id = d_id;
    }

    public Date getD_date() {
        return d_date;
    }

    public void setD_date(Date d_date) {
        this.d_date = d_date;
    }

    public Integer getF_id() {
        return f_id;
    }

    public void setF_id(Integer f_id) {
        this.f_id = f_id;
    }

    public Integer getS_id() {
        return s_id;
    }

    public void setS_id(Integer s_id) {
        this.s_id = s_id;
    }

    public Integer getG_id() {
        return g_id;
    }

    public void setG_id(Integer g_id) {
        this.g_id = g_id;
    }

    public Double getD_money() {
        return d_money;
    }

    public void setD_money(Double d_money) {
        this.d_money = d_money;
    }


    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ClassFee getClassFee() {
        return classFee;
    }

    public void setClassFee(ClassFee classFee) {
        this.classFee = classFee;
    }

    @Override
    public String toString() {
        return "Details{" +
                "d_id=" + d_id +
                ", d_date=" + d_date +
                ", f_id=" + f_id +
                ", s_id=" + s_id +
                ", g_id=" + g_id +
                ", d_money=" + d_money +
                '}';
    }
}

