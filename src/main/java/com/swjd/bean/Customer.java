package com.swjd.bean;

public class Customer {
    private int customerId;
    private String customerName;
    private int customerUserId;
    //客户负责人的真实姓名
    private String customerUserName;
    private int customerCreateId;
    private String customerSource;
    //客户信息来源
    private String customerSourceDict;
    private String customerIndustory;
    //客户行业(中文)
    private String customerIndustoryDict;
    private String customerPhone;
    private String customerAddress;
    private String customerDate;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerUserId() {
        return customerUserId;
    }

    public void setCustomerUserId(int customerUserId) {
        this.customerUserId = customerUserId;
    }

    public String getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    public int getCustomerCreateId() {
        return customerCreateId;
    }

    public void setCustomerCreateId(int customerCreateId) {
        this.customerCreateId = customerCreateId;
    }

    public String getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(String customerSource) {
        this.customerSource = customerSource;
    }

    public String getCustomerSourceDict() {
        return customerSourceDict;
    }

    public void setCustomerSourceDict(String customerSourceDict) {
        this.customerSourceDict = customerSourceDict;
    }

    public String getCustomerIndustory() {
        return customerIndustory;
    }

    public void setCustomerIndustory(String customerIndustory) {
        this.customerIndustory = customerIndustory;
    }

    public String getCustomerIndustoryDict() {
        return customerIndustoryDict;
    }

    public void setCustomerIndustoryDict(String customerIndustoryDict) {
        this.customerIndustoryDict = customerIndustoryDict;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }


    public String getCustomerDate() {
        return customerDate;
    }

    public void setCustomerDate(String customerDate) {
        this.customerDate = customerDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerUserId=" + customerUserId +
                ", customerUserName='" + customerUserName + '\'' +
                ", customerCreateId=" + customerCreateId +
                ", customerSource='" + customerSource + '\'' +
                ", customerSourceDict='" + customerSourceDict + '\'' +
                ", customerIndustory='" + customerIndustory + '\'' +
                ", customerIndustoryDict='" + customerIndustoryDict + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerDate='" + customerDate + '\'' +
                '}';
    }

    public Customer() {
    }
}
