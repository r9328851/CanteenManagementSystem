package com.service;

import com.beans.fooditem;
import com.db.DB;

public class FoodService {

	public static fooditem[] fetchMenu() {
		return DB.fetchMenudb();
	}

	public static int OrderFood(String venId, String custId, int fid, int fquan, double ftotal) {
		return DB.orderFood(venId, custId, fid, fquan, ftotal);
	}

	public static fooditem validate(int fid) {
		return DB.validateMenu(fid);
	}

}
