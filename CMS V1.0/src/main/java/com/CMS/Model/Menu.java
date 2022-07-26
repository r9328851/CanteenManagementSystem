package com.CMS.Model;

/**
 * food class used to display food information.
 * @author hexware
 */
public class Menu {
    private int foodId;
    private String foodName;
    private int vendorId;
    private int foodPrice;

    public Menu(int foodId, String foodName, int vendorId, int foodPrice){
        this.foodId = foodId;
        this.foodName = foodName;
        this.vendorId = vendorId;
        this.foodPrice = foodPrice;

    }
    public void setFoodId(int foodId){
        this.foodId=foodId;
    }

    public int getFoodId(){
        return foodId;
    }

    public void setFoodName(String foodName){
        this.foodName=foodName;
    }

    public String getFoodName(){
        return foodName;
    }

    public void setFoodPrice(int foodPrice){
        this.foodPrice=foodPrice;
    }

    public int getFoodPrice(){
        return foodPrice;
    }

    public void setVendorId(int vendorId){
        this.vendorId=vendorId;
    }

    public int getVendorId(){
        return vendorId;
    }

    public String toString(){
        return "Food id: " + foodId + "\nFood Name: " + foodName + "\nFood Price: " + foodPrice + "\nVendor ID: " + vendorId;
    }
    
}
