package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.beans.Customer;
import com.beans.Login;
import com.beans.fooditem;
import com.beans.order;
import com.beans.vendor;

/**
 * OrderDb class used to connect to data base.
 * @author RohithDondapati
 */
public class DB {
	// Here use your database url, username and password
	private static String url = "jdbc:mysql://localhost:3306/CMSDB70622?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private static String username = "root";
	private static String password = "Password123";
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static Connection con;

	// Establishes connection with database
	private static void dbConnect() {

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Closes the connection with the database
	private static void dbClose() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Validate customer details
	public static boolean checkLogin(Login login) {
		dbConnect();
		boolean status = false;
		
		String sql="select * from login where username=? and password=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, login.getUsername());
			pstmt.setString(2, login.getPassword());
			
			ResultSet rst =   pstmt.executeQuery();
			status = rst.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbClose();
		return status;
	}

	// Fetch food menu from database
	public static fooditem[] fetchMenudb() {
		dbConnect();
	    fooditem y[]=null;
		
		String sql="select * from fooditem";
		try {
			Statement stmt = con.prepareStatement(sql);
			 
			ResultSet rst =   stmt.executeQuery(sql);
			ArrayList<fooditem> List=new ArrayList<fooditem>();
			while(rst.next()) {
				fooditem food=new fooditem(
				rst.getInt("food_Id"),
				rst.getString("food_Name"),
				rst.getString("vendor_Id"),
				rst.getDouble("food_Price")
						);
				List.add(food);
				
			}
			y=new fooditem[List.size()];
			y=List.toArray(y);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbClose();
		return y;
	}

	// Fetch customer name from database
	public static String fetchNameByUsername(String username) {
		dbConnect();
		
		String name = "";
		
		String sql="select vendor_name from vendor v,login l where v.vendor_id = l.username "
				+ "AND username=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			 
			ResultSet rst =   pstmt.executeQuery();
			while(rst.next()) {
				name = rst.getString("vendor_name");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbClose();
		return name; 
	}
	
	// Validating food items whether the correct option is selected or not
	public static fooditem validateMenu(int fid) {
		
		dbConnect();
		fooditem f = null;
        String sql = " select * from fooditem where food_id = ?  ";
        try
        {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,fid);
                
            ResultSet rs = pstmt.executeQuery();
            if ( rs.next () )
            {
                f = new fooditem(
                    rs.getInt("food_id"),
                    rs.getString("food_name"),
                    rs.getString("vendor_id"),
                    rs.getInt("food_price") );
            }
            pstmt.close();
        } catch(SQLException e){
			e.printStackTrace();
        }
        dbClose();
        return f;
	}
	
	// Updates the customer wallet balance
	public static int updateCustomerWallet(String custId, double bal)
    {
		dbConnect();
		
		int i = 0;
        String sql = "UPDATE Customer SET Customer_WalletBal = ? WHERE Customer_Id = ?";
        try
        {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDouble(1,bal);
            pstmt.setString(2,custId);
            i = pstmt.executeUpdate();
            
            pstmt.close();
            
        } catch(SQLException e){
			e.printStackTrace();
        }
        dbClose();
        return i;
    }

	// Ordering the food
	public static int orderFood(String venId, String custId, int fid, int fquan, double ftotal) {
		dbConnect();
		int i =0;
        String type = "ORDERED";
        String sql = "insert into orderDetails (Vendor_Id,Customer_Id,Food_Id,Quantity,Date_Time,Order_Value,Order_Status) values(?,?,?,?,SYSDATE(),?,?)";
        try
        {
            PreparedStatement pstmt=con.prepareStatement(sql);  
            
            pstmt.setString(1,venId);  
            pstmt.setString(2,custId);  
            pstmt.setInt(3,fid);
            pstmt.setInt(4,fquan);   
            pstmt.setDouble(5,ftotal);
            pstmt.setString(6,type);
            
            i = pstmt.executeUpdate();
			
			pstmt.close();

        } catch(Exception e){
            System.out.println(e);
        }  
		dbClose();
        return i;
	}

	// Customer registration
	public static int registerUser(Login login) {
		dbConnect();

		int i = 0;
		String sql = "Insert into login(username,password) values (?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, login.getUsername());
			pstmt.setString(2, login.getPassword());
			i = pstmt.executeUpdate();

			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return i;
	}

	// Validating the username of a customer
	public static Login checkUserExists(String username) {
		dbConnect();

		Login l=null;
		String sql = "select * from login where username = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rst = pstmt.executeQuery();
            while(rst.next()) {
            	l=new Login ( 
            	rst.getString("username"),
            	rst.getString("password"));
            }

			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return l;
	}

	// Changing the login password
	public static int updatePassword(String username, String confirmPass) {
        dbConnect();

		int i = 0;
		String sql = "update login set password = ? where username = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, confirmPass);
			pstmt.setString(2, username);
			i = pstmt.executeUpdate();

			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return i;
    }
 
	// Fetching the customer wallet balance from database
	public static double customerWalBal(String username) {
		dbConnect();

		double i = 0;
		String sql = "select customer_WalletBal from customer where customer_Id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rst = pstmt.executeQuery();
            if ( rst.next () )
            {
				i = rst.getDouble("customer_WalletBal");
			}

			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return i;
	}

	// Customer previous orders history
	public static order[] customerOrderHistory(String username) {
		dbConnect();
		
		order[] odArr = null;
		String sql = " select * from orderDetails where customer_Id = ?  ";
            
        try
        {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,username);
            
            ResultSet rst = pstmt.executeQuery();
            
            ArrayList<order> list=new ArrayList<order>();  
            while( rst.next() )
            {
                order od = new order(
                    rst.getInt("order_id"), 
                    rst.getString("vendor_id"), 
                    rst.getString("customer_Id"),
                    rst.getInt("food_id"), 
                    rst.getInt("quantity"),
                    rst.getDate("date_Time"), 
                    rst.getInt("Order_Value"), 
                    rst.getString("Order_Status") );
                list.add(od);
            }
            odArr=new order[list.size()];
            odArr= list.toArray(odArr);

            pstmt.close();
        } catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return odArr;
	}

	// Customer's profile
    public static Customer[] myProfile(String username) {
		dbConnect();

        Customer[] cArray = null;
		String sql = " select * from customer where customer_Id = ? ";
        
        try
        {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,username);

            ResultSet rst = pstmt.executeQuery();
            
            ArrayList<Customer> list=new ArrayList<Customer>();
            while(rst.next())
            {
                Customer c = new Customer(
                    rst.getString("customer_Id"),
                    rst.getString("customer_Name"),
                    rst.getString("customer_Coupon"),
                    rst.getDouble("customer_WalletBal"));
                list.add(c);
            }
            cArray=new Customer[list.size()];
            cArray = list.toArray(cArray);
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return cArray;
    }

	// Fetches the food menu offered by the specified vendor
	public static fooditem[] fetchVendorMenu(String username) {
		dbConnect();
	    fooditem y[]=null;

		try {
			PreparedStatement pstmt = con.prepareStatement("select * from fooditem where vendor_Id=?");
			pstmt.setString(1, username);
			ResultSet rst =pstmt.executeQuery();
			ArrayList<fooditem> List=new ArrayList<fooditem>();
			while(rst.next()) {
				fooditem food=new fooditem(
				rst.getInt("food_Id"),
				rst.getString("food_Name"),
				rst.getString("vendor_Id"),
				rst.getDouble("food_Price")
						);
				List.add(food);
				
			}
			y=new fooditem[List.size()];
			y=List.toArray(y);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbClose();
		return y;
	}

	// Previous orders recieved by the vendor
    public static order[] vendorOrderHistory(String username) {
        dbConnect();
		
		order[] odArr = null;
		String sql = " select * from orderDetails where vendor_Id = ?  ";
            
        try
        {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,username);
            
            ResultSet rst = pstmt.executeQuery();
            
            ArrayList<order> list=new ArrayList<order>();  
            while( rst.next() )
            {
                order od = new order(
                    rst.getInt("order_id"), 
                    rst.getString("vendor_id"), 
                    rst.getString("customer_Id"),
                    rst.getInt("food_id"), 
                    rst.getInt("quantity"),
                    rst.getDate("date_Time"), 
                    rst.getDouble("Order_Value"), 
                    rst.getString("Order_Status") );
                list.add(od);
            }
            odArr=new order[list.size()];
            odArr= list.toArray(odArr);

            pstmt.close();
        } catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return odArr;
    }

	// status of the food ordered
    public static order[] orderStatus(String type, String username) {
		dbConnect();

        order[] odArr = null;
		String sql = " select * from orderDetails where order_Status = ?  AND Vendor_id = ?";

        try
        {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,type);
            pstmt.setString(2,username);
                
            ResultSet rst = pstmt.executeQuery();
            ArrayList<order> list=new ArrayList<order>();  
            while( rst.next() )
            {
                order od = new order(
                    rst.getInt("order_id"), 
                    rst.getString("vendor_id"),  
                    rst.getString("customer_id"), 
                    rst.getInt("food_id"), 
                    rst.getInt("quantity"),
                    rst.getDate("date_Time"), 
                    rst.getDouble("order_Value"), 
                    rst.getString("order_Status") );
                list.add(od);
            }
            odArr=new order[list.size()];
            odArr= list.toArray(odArr);
        
            pstmt.close();
        } catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
        return odArr;
    }

	// vendor accepts or rejects the order
	public static int acceptOrRejectOrder(int order_id, String status) {
		dbConnect();
		int i = 0;
		String sql = "update orderDetails set order_Status = ? where order_Id = ?";
        try
        {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,status);
            pstmt.setInt(2,order_id);
            i = pstmt.executeUpdate();
            
            System.out.println("Order " + status);
            
            pstmt.close();
        } catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
        return i;
	}

	// fetches the order details
	public static order[] orderFetch(int order_id) {
		dbConnect();
	    order y[]=null;
		
		String sql="select * from orderDetails where order_id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, order_id);
			ResultSet rst =   pstmt.executeQuery();
			ArrayList<order> List=new ArrayList<order>();
			while(rst.next()) 
			{
                order od = new order(
                    rst.getInt("order_Id"), 
                    rst.getString("vendor_id"),  
                    rst.getString("customer_id"), 
                    rst.getInt("food_id"), 
                    rst.getInt("quantity"),
                    rst.getDate("date_Time"), 
                    rst.getDouble("order_Value"), 
                    rst.getString("order_Status") );
				List.add(od);
				
			}
			y=new order[List.size()];
			y=List.toArray(y);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return y;
	}

	// To update the wallet if the order is rejected or cancelled
    public static void custWalletBalance(String custid, double total) {
		dbConnect();
		int i=0;
        try
        {
            PreparedStatement stmt = con.prepareStatement("update Customer set customer_WalletBal = customer_WalletBal + ? where customer_Id = ?");
            stmt.setDouble(1,total);
            stmt.setString(2,custid);
            i = stmt.executeUpdate();
            if(i>=1){
            	System.out.println("Amount refunded successfully");
			}
            stmt.close();
        } catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
    }

	// Vendor's profile
	public static vendor[] myVendorProfile(String username) {
		dbConnect();

        vendor[] vArray = null;
		String sql = " select * from vendor where vendor_id = ? ";
        
        try
        {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,username);

            ResultSet rst = pstmt.executeQuery();
            ArrayList<vendor> list=new ArrayList<vendor>();
            while(rst.next())
            {
                vendor v = new vendor(
                    rst.getString("vendor_Id"),
                    rst.getString("vendor_Name"),
                    rst.getString("vendor_Phone"),
                    rst.getString("vendor_Specs"));
                list.add(v);
            }
            vArray=new vendor[list.size()];
            vArray = list.toArray(vArray);
			pstmt.close();
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return vArray;
	}

	// add or delete or change the food menu
	public static void modifyFoodMenu(int foodid, String foodName, double foodPrice) {
		dbConnect();
		int i =0;
		String sql = "update fooditem set food_Name = ?, food_Price = ? where food_id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,foodName);
			pstmt.setDouble(2, foodPrice);
			pstmt.setInt(3, foodid);
			
			i = pstmt.executeUpdate();
			if(i >=1){
				System.out.println("Updated the menu successfully");
			}
			else{
				System.out.println("Try Again");
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
	}

	// Fetches order status of particular customer
	public static order[] customerOrderStatusDB(String type, String username) {
		dbConnect();

        order[] odArr = null;
		String sql = " select * from orderDetails where order_Status = ?  AND customer_id = ?";

        try
        {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,type);
            pstmt.setString(2,username);
                
            ResultSet rst = pstmt.executeQuery();
            ArrayList<order> list=new ArrayList<order>();  
            while( rst.next() )
            {
                order od = new order(
                    rst.getInt("order_id"), 
                    rst.getString("vendor_id"),  
                    rst.getString("customer_id"), 
                    rst.getInt("food_id"), 
                    rst.getInt("quantity"),
                    rst.getDate("date_Time"), 
                    rst.getDouble("order_Value"), 
                    rst.getString("order_Status") );
                list.add(od);
            }
            odArr=new order[list.size()];
            odArr= list.toArray(odArr);
        
            pstmt.close();
        } catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
        return odArr;
	}

	// Fetches customer name
	public static String fetchCustomerName(String username) {
		dbConnect();
		
		String name = "";
		
		String sql="select customer_name from customer c,login l where c.customer_id = l.username "
				+ "AND username=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			 
			ResultSet rst =   pstmt.executeQuery();
			while(rst.next()) {
				name = rst.getString("customer_name");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbClose();
		return name; 
	}
		
}