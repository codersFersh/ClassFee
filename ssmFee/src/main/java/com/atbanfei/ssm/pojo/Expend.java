package com.atbanfei.ssm.pojo;

/**
 * 班费支出
 */




public class Expend {
    private Integer e_id;
    private String e_date;
    private String e_title;
    private Double e_gross;

    public Expend() {
    }

    public Expend(Integer e_id, String e_date, String e_title, Double e_gross) {
        this.e_id = e_id;
        this.e_date = e_date;
        this.e_title = e_title;
        this.e_gross = e_gross;
    }

    public Integer getE_id() {
        return e_id;
    }

    public void setE_id(Integer e_id) {
        this.e_id = e_id;
    }

    public String getE_date() {
        return e_date;
    }

    public void setE_date(String e_date) {
        this.e_date = e_date;
    }

    public String getE_title() {
        return e_title;
    }

    public void setE_title(String e_title) {
        this.e_title = e_title;
    }

    public Double getE_gross() {
        return e_gross;
    }

    public void setE_gross(Double e_gross) {
        this.e_gross = e_gross;
    }

    @Override
    public String toString() {
        return "Expend{" +
                "e_id=" + e_id +
                ", e_date='" + e_date + '\'' +
                ", e_title='" + e_title + '\'' +
                ", e_gross='" + e_gross + '\'' +
                '}';
    }
}
