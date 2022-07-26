package com.CMS.Model;

import java.sql.Date;

public class OrderDetails {
   private int orderNo;
   private int vendorId;
   private String customerId;
   private int foodId;
   private int quantity;
   private Date eta;
   private Date datetime;
   private int  orderValue;
   private String orderStatus;

   public OrderDetails (int orderNo, int vendorId, String customerId, int foodId, int quantity, Date eta, Date datetime,
       int orderValue, String orderStatus) {
     this.orderNo = orderNo;
     this.vendorId = vendorId;
     this.customerId = customerId;
     this.foodId = foodId;
     this.quantity = quantity;
     this.eta = eta;
     this.datetime = datetime;
     this.orderValue = orderValue;
     this.orderStatus = orderStatus;
   }

      /**
     * @return int return the orderNo
     */
    public int getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo the orderNo to set
     */
    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return int return the vendorId
     */
    public int getVendorId() {
        return vendorId;
    }

    /**
     * @param vendorId the vendorId to set
     */
    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * @return String return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return int return the foodId
     */
    public int getFoodId() {
        return foodId;
    }

    /**
     * @param foodId the foodId to set
     */
    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    /**
     * @return int return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return Date return the eta
     */
    public Date getEta() {
        return eta;
    }

    /**
     * @param eta the eta to set
     */
    public void setEta(Date eta) {
        this.eta = eta;
    }

    /**
     * @return Date return the datetime
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * @param datetime the datetime to set
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    /**
     * @return int return the orderValue
     */
    public int getOrderValue() {
        return orderValue;
    }

    /**
     * @param orderValue the orderValue to set
     */
    public void setOrderValue(int orderValue) {
        this.orderValue = orderValue;
    }

    /**
     * @return String return the orderStatus
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus the orderStatus to set
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

   @Override
   public String toString() {
     return "\nCustomer ID \t= " + customerId + "\nDateTime \t= " + datetime + "\nETA \t\t= " + eta + "\nFoodId \t\t= " + foodId
         + "\nOrderNo \t= " + orderNo + "\nOrderStatus \t= " + orderStatus + "\nOrderValue \t= " + orderValue + "\nQuantity \t= "
         + quantity + "\nVendorId \t= " + vendorId;
   }

   

  // generate the getter and setters for this
  // default constructor, all argument constructor
  // toString - do not show password
}