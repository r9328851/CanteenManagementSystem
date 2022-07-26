package com.CMS.Factory;

import com.CMS.Model.Customer;
import com.CMS.Model.Menu;
import com.CMS.Model.OrderDetails;
import com.CMS.Model.Vendor;
import com.CMS.Persistence.OrderDb;

/**
 * OrderFactory class used to fetch and insert data to database.
 * @author RohithDondapati (Hexaware)
 */
public class OrderFactory {

    public static Menu[] fetchMenu(){
        return OrderDb.fetchDb();
    }

    public static Menu validate(int fid){
        return OrderDb.validateMenu(fid);
    }

    public static Customer[] customerProfile(int loginId){
        return OrderDb.customerProfileDb(loginId);
    }

    public static Customer validateCustomer(int loginId, String psw){
        return OrderDb.validateCustomer(loginId,psw);
    }

    public static Vendor[] vendorProfile(){
        return OrderDb.vendorProfileDb();
    }

    public static Vendor validateVendor(int venId){
        return OrderDb.validateVendor(venId);
    }

    public static OrderDetails[] vendorOrderHistory(int venId){
        return OrderDb.vendorOrderHistoryDb(venId);
    }

    public static OrderDetails[] customerOrderHistory(int loginId){
        return OrderDb.customerOrderHistoryDb(loginId);
    }

    public static  OrderDetails[] Order_Status(String type, int venid){
        return OrderDb.Order_Status(type, venid);
    }

    public static int acceptRejectOrder(int food_id, String ch){
        return OrderDb.acceptOrRejectOrder(food_id,ch);
    }

    public static int updateCustomerWallet(String custid, int total){
        return OrderDb.updateCustomerWalletBalance(custid,total);
    }

    public static int OrderFood(int venId,String custId,int fid,int fquan,int ftotal){
        return OrderDb.insertDb(venId,custId,fid,fquan,ftotal);
    }

    public static int updateWalBal(String custid, float bal){
        return OrderDb.updateWalBalDb(custid,bal);
    }

    public static int changePass(int user, String confirmPass){
        return OrderDb.updatePass(user, confirmPass);
    }

    public static int addFood(String fname, int fprice, int vendorid){
        return OrderDb.addFoodItem(fname,fprice,vendorid);
    }

    public static int modifyFood(int fid,int foodprice,int vendorid){
        return OrderDb.modifyFoodPrice(fid,foodprice,vendorid);
    }
}
