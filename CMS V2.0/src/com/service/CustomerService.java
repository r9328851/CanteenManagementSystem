package com.service;

import com.beans.Customer;
import com.beans.Login;
import com.db.DB;

public class CustomerService {
	
	public boolean checkLogin(Login login){
		return DB.checkLogin(login);
	}
	
	public static int changePass(String username, String confirmPass){
		return DB.updatePassword(username, confirmPass);
	}

	public static int updateWalBal(String custId, double bal) {
		return DB.updateCustomerWallet(custId, bal);
	}

    public static int insertingNewUser(Login login) {
		return DB.registerUser(login);
    }

	public static Login CheckUsername( String username) {
		return DB.checkUserExists(username);
	}

	public static double WalletBalance(String username) {
		return DB.customerWalBal(username);
	}

	public static Customer[] MyProfile(String username) {
		return DB.myProfile(username);
	}

	public static void updateCustWallet(String custid, double total) {
		DB.custWalletBalance(custid, total);
	}

}
