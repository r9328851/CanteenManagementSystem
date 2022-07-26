package com.service;

import com.beans.Login;
import com.beans.fooditem;
import com.beans.vendor;
import com.db.DB;

public class VendorService {
	
	public boolean checkLogin(Login login) {
		return DB.checkLogin(login);
	}

	public String fetchNameByUsername(String username) {
		return DB.fetchNameByUsername(username);
	}

	public static fooditem[] fetchMenu(String username) {
		return DB.fetchVendorMenu(username);
	}

	public static int acceptRejectOrder(int order_id, String ch) {
		return DB.acceptOrRejectOrder(order_id, ch);
	}

	public static vendor[] MyProfile(String username) {
		return DB.myVendorProfile(username);
	}

	public static void ModifyFood(int foodid, String foodName, double foodPrice) {
		DB.modifyFoodMenu(foodid, foodName, foodPrice);
	}

	public String fetchcustomerNameByUsername(String username) {
		return DB.fetchCustomerName(username);
	}
}
