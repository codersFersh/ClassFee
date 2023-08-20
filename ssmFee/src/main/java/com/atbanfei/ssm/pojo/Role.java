package com.atbanfei.ssm.pojo;





public class Role {
    private Integer r_id;
    private String r_name;
    private String r_description;

    public Role() {
    }

    public Role(Integer r_id, String r_name, String r_description) {
        this.r_id = r_id;
        this.r_name = r_name;
        this.r_description = r_description;
    }

    public Integer getR_id() {
        return r_id;
    }

    public void setR_id(Integer r_id) {
        this.r_id = r_id;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getR_description() {
        return r_description;
    }

    public void setR_description(String r_description) {
        this.r_description = r_description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "r_id=" + r_id +
                ", r_name='" + r_name + '\'' +
                ", r_description='" + r_description + '\'' +
                '}';
    }
}
