package com.atbanfei.ssm.pojo;

/**
 * 学生
 */




public class Student {
    private Integer s_id;
    private String s_name;
    private String s_gender;
    private String s_phone;
    private String s_password;
    private Integer g_id;
    private Integer r_id;

    private Grade grade;
    private Role role;

    public Student() {
    }

    public Student(Integer s_id, String s_name, String s_gender, String s_phone, String s_password, Integer g_id, Integer r_id) {
        this.s_id = s_id;
        this.s_name = s_name;
        this.s_gender = s_gender;
        this.s_phone = s_phone;
        this.s_password = s_password;
        this.g_id = g_id;
        this.r_id = r_id;
    }


    public Integer getS_id() {
        return s_id;
    }

    public void setS_id(Integer s_id) {
        this.s_id = s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_gender() {
        return s_gender;
    }

    public void setS_gender(String s_gender) {
        this.s_gender = s_gender;
    }

    public String getS_phone() {
        return s_phone;
    }

    public void setS_phone(String s_phone) {
        this.s_phone = s_phone;
    }

    public String getS_password() {
        return s_password;
    }

    public void setS_password(String s_password) {
        this.s_password = s_password;
    }

    public Integer getG_id() {
        return g_id;
    }

    public void setG_id(Integer g_id) {
        this.g_id = g_id;
    }

    public Integer getR_id() {
        return r_id;
    }

    public void setR_id(Integer r_id) {
        this.r_id = r_id;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Student{" +
                "s_id=" + s_id +
                ", s_name='" + s_name + '\'' +
                ", s_gender='" + s_gender + '\'' +
                ", s_phone='" + s_phone + '\'' +
                ", s_password='" + s_password + '\'' +
                ", g_id=" + g_id +
                ", r_id=" + r_id +
                '}';
    }
}
