package com.itheima.domain;

import java.sql.Timestamp;

public class Product {
    private Long id;
    // 产品编号
    private String productNum;
    // 产品名称
    private String productName;
    // 出发城市
    private String cityName;

    // 出发时间.....
    // @DateTimeFormat 只能把String--->Date
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    //private Date departureTime1;

    // 在oralce中用date，对应的是java中的Timestamp
    private Timestamp departureTime;
    // 产品价格
    private double productPrice;
    // 产品描述
    private String productDesc;
    // 0 关闭； 1 开启
    private Integer productStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }
}
















