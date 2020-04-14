package com.example.demo.test;

import java.math.BigDecimal;

public class User {

    private Integer age1;
    private Long age2;
    private Float age3;
    private Double age4;
    private BigDecimal age5;
    private String id;
    private String name;
    public Integer getAge1() {
        return age1;
    }
    public void setAge1(Integer age1) {
        this.age1 = age1;
    }
    public Long getAge2() {
        return age2;
    }
    public void setAge2(Long age2) {
        this.age2 = age2;
    }
    public Float getAge3() {
        return age3;
    }
    public void setAge3(Float age3) {
        this.age3 = age3;
    }
    public Double getAge4() {
        return age4;
    }
    public void setAge4(Double age4) {
        this.age4 = age4;
    }
    public BigDecimal getAge5() {
        return age5;
    }
    public void setAge5(BigDecimal age5) {
        this.age5 = age5;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "User [age1=" + age1 + ", age2=" + age2 + ", age3=" + age3 + ", age4=" + age4 + ", age5=" + age5
                + ", id=" + id + ", name=" + name + "]";
    }
    
}
