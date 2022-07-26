package com.CMS.Model;

public class Customer{
    
    private String custId;
    private String custName;
    private String custPhone;
    private String custEmail;
    private String custCoupon;
    private float custWalletBalance;
    private int custLoginId;
    private String custPassword;

    public Customer(String custId, String custName, int custLoginId, String custPhone, 
    String custEmail, String custCoupon, float custWalletBalance) {
        this.custId = custId;
        this.custName = custName;
        this.custPhone = custPhone;
        this.custEmail = custEmail;
        this.custCoupon = custCoupon;
        this.custWalletBalance = custWalletBalance;
        this.custLoginId = custLoginId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustCoupon() {
        return custCoupon;
    }

    public void setCustCoupon(String custCoupon) {
        this.custCoupon = custCoupon;
    }

    public float getCustWalletBalance() {
        return custWalletBalance;
    }

    public void setCustWalletBalance(float custWalletBalance) {
        this.custWalletBalance = custWalletBalance;
    }

    public int getCustLoginId() {
        return custLoginId;
    }

    public void setCustLoginId(int custLoginId) {
        this.custLoginId = custLoginId;
    }

    public String getCustPassword() {
        return custPassword;
    }

    public void setCustPassword(String custPassword) {
        this.custPassword = custPassword;
    }

    public String toString(){
        return "\nCustomer ID: "+custId+"\nCustomer Name: "+custName+"\nCustomer Phone: "+custPhone+"\nCustomer Email: "+
        custEmail+"\nCustomer coupon: "+custCoupon+"\nCustomer Wallet Balance: "+custWalletBalance+"\nCustomer LoginId: "+custLoginId;
    }
}