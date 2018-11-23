package com.myccnice.practice.manual.jdk.jdk8.stream.vo;

public class Parent extends Person {

    /**
     * 身份证编号
     */
    private String idCardNo;

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public Parent() {

    }

    public Parent(String idCardNo) {
        super();
        this.idCardNo = idCardNo;
    }

}
