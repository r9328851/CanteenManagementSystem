package com.service;

import com.beans.order;
import com.db.DB;

public class OrderService {

    public static order[] custOrderHist(String username) {
        return DB.customerOrderHistory(username);
    }

    public static order[] venOrderHist(String username) {
        return DB.vendorOrderHistory(username);
    }

    public static order[] Order_Status(String type, String username) {
        return DB.orderStatus(type, username);
    }

    public static order[] OrderFetch(int order_id) {
        return DB.orderFetch(order_id);
    }

	public static order[] customerOrderStatus(String type, String username) {
		return DB.customerOrderStatusDB(type,username);
	}

}
