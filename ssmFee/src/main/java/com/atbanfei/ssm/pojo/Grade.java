package com.atbanfei.ssm.pojo;

/**
 * 班级
 */





public class Grade {
    private Integer g_id;
    private String g_classname;

    public Grade() {
    }

    public Grade(Integer g_id, String g_classname) {
        this.g_id = g_id;
        this.g_classname = g_classname;
    }

    public Integer getG_id() {
        return g_id;
    }

    public void setG_id(Integer g_id) {
        this.g_id = g_id;
    }

    public String getG_classname() {
        return g_classname;
    }

    public void setG_classname(String g_classname) {
        this.g_classname = g_classname;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "g_id=" + g_id +
                ", g_classname='" + g_classname + '\'' +
                '}';
    }
}
