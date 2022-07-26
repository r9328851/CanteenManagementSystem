package com.CMS.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.CMS.Model.Customer;
import com.CMS.Model.Menu;
import com.CMS.Model.OrderDetails;
import com.CMS.Model.Vendor;

import java.sql.PreparedStatement;

/**
 * OrderDb class used to connect to data base.
 * @author RohithDondapati (Hexaware)
 */
public class OrderDb {

    static String className = "com.mysql.cj.jdbc.Driver";
    static String sqlUrl = "jdbc:mysql://localhost:3306/CMSDB70622?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static String sqlUser = "root";
    static String sqlPass = "Password123";

    // Fetch Menu from database
    public static Menu[] fetchDb()
    {
        Menu m[]=null;
        try
        {  
            Class.forName(className);
            Connection con=DriverManager.getConnection(sqlUrl,sqlUser,sqlPass);
            
            Statement stmt=con.createStatement();      
            ResultSet rs=stmt.executeQuery("select * from menu");  
            
            ArrayList<Menu> list=new ArrayList<Menu>();          
            while(rs.next())
            { 
                Menu mnu = new Menu(
                    rs.getInt("Food_ID"),
                    rs.getString("Food_Name"),
                    rs.getInt("Vendor_id"),
                    rs.getInt("Food_Price")
                );
                list.add(mnu);
            }
            m=new Menu[list.size()];
            m= list.toArray(m);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return m;      
    }

    // validate the items in menu with foodid from database
    public static Menu validateMenu( int foodId )
    {
        Menu m = null;
        try
        {
            Class.forName(className);
            Connection con=DriverManager.getConnection(sqlUrl,sqlUser,sqlPass);
                 
            String sql = " select * from Menu where food_id = ?  ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,foodId);
                
            ResultSet rs = stmt.executeQuery();
            if ( rs.next () )
            {
                m = new Menu(
                    rs.getInt("food_id"),
                    rs.getString("food_name"),
                    rs.getInt("vendor_id"),
                    rs.getInt("food_price") );
            }
            stmt.close();
            con.close();
        } 
        catch(Exception e)
        {
            System.out.println( e );
        }
        return m;
    }

    // fetches the specified customer profile from database
    public static Customer[] customerProfileDb(int loginId)
    {
        Customer[] cArray = null;
        try
        {
            Class.forName(className);
            Connection con=DriverManager.getConnection(sqlUrl,sqlUser,sqlPass);
            
            String sql = " select Customer_Id,Customer_Name,Username,Customer_Phone,Customer_Email,Customer_Coupon,Customer_WalletBal from Customer where Username = ? ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,loginId);

            ResultSet rs = stmt.executeQuery();
            
            ArrayList<Customer> list=new ArrayList<Customer>();
            while(rs.next())
            {
                Customer c = new Customer(
                    rs.getString("Customer_Id"),
                    rs.getString("Customer_Name"),
                    rs.getInt("Username"),
                    rs.getString("Customer_Phone"),
                    rs.getString("Customer_Email"),
                    rs.getString("Customer_Coupon"),
                    rs.getFloat("Customer_WalletBal"));
                list.add(c);
            }
            cArray=new Customer[list.size()];
            cArray = list.toArray(cArray);
        }
        catch(Exception e)
        {
            System.out.println( e );
        }
        return cArray;
    }

    // Validate the customer login credentials from database
    public static Customer validateCustomer(int loginId, String psw)
    {
        Customer c = null;
        try
        {
            Class.forName(className);
            Connection con=DriverManager.getConnection(sqlUrl,sqlUser,sqlPass);
             
            String sql = " select * from Customer where Username = ? and login_password = ? ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,loginId);
            stmt.setString(2,psw);
            
            ResultSet rs = stmt.executeQuery();
            if ( rs.next () )
            {
                c = new Customer(
                    rs.getString("Customer_Id"),
                    rs.getString("Customer_Name"),
                    rs.getInt("Username"),
                    rs.getString("Customer_Phone"),
                    rs.getString("Customer_Email"),
                    rs.getString("Customer_Coupon"),
                    rs.getFloat("Customer_WalletBal"));
            }
            stmt.close();
            con.close();
        } 
        catch(Exception e)
        {
            System.out.println( e );
        }
        return c;
    }

    //fetches vendor profile from database
    public static Vendor[] vendorProfileDb()
    {
        Vendor[] vnArray = null;
        try
        {
            Class.forName(className);
            Connection con=DriverManager.getConnection(sqlUrl,sqlUser,sqlPass);
            
            Statement stmt=con.createStatement();          
            ResultSet rs=stmt.executeQuery("select * from vendor");  
            
            ArrayList<Vendor> list=new ArrayList<Vendor>();          
            while(rs.next())
            { 
                Vendor v = new Vendor(
                    rs.getInt("vendor_id"),
                    rs.getString("vendor_Name"),
                    rs.getString("vendor_phone"),
                    rs.getString("vendor_specs") );
                list.add(v);
            }
            vnArray=new Vendor[list.size()];
            vnArray= list.toArray(vnArray);
        } 
        catch(Exception e)
        {
            System.out.println( e );
        }
        return vnArray;
    }

    // Validate the Vendor ID from database
    public static Vendor validateVendor( int venId )
    {
        Vendor v = null;
        try
        {
            Class.forName(className);
            Connection con=DriverManager.getConnection(sqlUrl,sqlUser,sqlPass);
                 
            String sql = " select * from Vendor where vendor_id = ?  ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,venId);
                
            ResultSet rs = stmt.executeQuery();
            if ( rs.next () )
            {
                v = new Vendor(
                    rs.getInt("vendor_id"),
                    rs.getString("vendor_Name"),
                    rs.getString("vendor_phone"),
                    rs.getString("vendor_specs") );
            }
                stmt.close();
                con.close();
        } 
        catch(Exception e)
        {
            System.out.println( e );
        }
        return v;
    }

    // fetch the specified vendor orderhistory from database
    public static OrderDetails[] vendorOrderHistoryDb(int vendorId)
    {
        OrderDetails[] odArr = null;
        try
        {
            Class.forName(className);
            Connection con=DriverManager.getConnection(sqlUrl,sqlUser,sqlPass);
                
            String sql = " select * from OrderDetails where Vendor_id = ?  ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,vendorId);

            ResultSet rs = stmt.executeQuery();
            
            ArrayList<OrderDetails> list=new ArrayList<OrderDetails>();  
            while( rs.next() )
            {
                OrderDetails od = new OrderDetails(
                    rs.getInt("Order_No"), 
                    rs.getInt("Vendor_id"),  
                    rs.getString("Customer_id"), 
                    rs.getInt("Food_id"), 
                    rs.getInt("Quantity"), 
                    rs.getDate("eta"), 
                    rs.getDate("Date_Time"), 
                    rs.getInt("Order_value"), 
                    rs.getString("Order_status") );
                list.add(od);
            }
            odArr=new OrderDetails[list.size()];
            odArr= list.toArray(odArr);

            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println( e );
        }
        return odArr;
    }

    // fetch the specified customer orderhistory from database
    public static OrderDetails[] customerOrderHistoryDb(int loginId)
    {
        OrderDetails[] odArr = null;
        try
        {
            Class.forName(className);
            Connection con=DriverManager.getConnection(sqlUrl,sqlUser,sqlPass);
            
            String sql = " select * from OrderDetails where Customer_Id = (select Customer_Id from Customer where Username = ?)  ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,loginId);
            
            ResultSet rs = stmt.executeQuery();
            
            ArrayList<OrderDetails> list=new ArrayList<OrderDetails>();  
            while( rs.next() )
            {
                OrderDetails od = new OrderDetails(
                    rs.getInt("Order_No"), 
                    rs.getInt("Vendor_id"), 
                    rs.getString("Customer_Id"),
                    rs.getInt("Food_id"), 
                    rs.getInt("Quantity"), 
                    rs.getDate("eta"), 
                    rs.getDate("Date_Time"), 
                    rs.getInt("Order_value"), 
                    rs.getString("Order_status") );
                list.add(od);
            }
            odArr=new OrderDetails[list.size()];
            odArr= list.toArray(odArr);

            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println( e );
        }
        return odArr;
    }

    // fetches the orderdetails from database where orderstatus set to "ordered"
    public static OrderDetails[] Order_Status(String type, int venid)
    {
        OrderDetails[] odArr = null;
        try
        {
            Class.forName(className);
            Connection con=DriverManager.getConnection(sqlUrl,sqlUser,sqlPass);
                
            String sql = " select * from OrderDetails where Order_Status = ?  AND Vendor_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,type);
            stmt.setInt(2,venid);
                
            ResultSet rs = stmt.executeQuery();
            ArrayList<OrderDetails> list=new ArrayList<OrderDetails>();  
            while( rs.next() )
            {
                OrderDetails od = new OrderDetails(
                    rs.getInt("Order_No"), 
                    rs.getInt("Vendor_id"),  
                    rs.getString("Customer_id"), 
                    rs.getInt("Food_id"), 
                    rs.getInt("Quantity"), 
                    rs.getDate("eta"), 
                    rs.getDate("Date_Time"), 
                    rs.getInt("Order_value"), 
                    rs.getString("Order_status") );
                list.add(od);
            }
            odArr=new OrderDetails[list.size()];
            odArr= list.toArray(odArr);
        
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println( e );
        }
        return odArr;
    }

    // accepts or rejects the customer orders by vendors
    public static int acceptOrRejectOrder(int food_id, String ch)
    {
        int i = 0;
        try
        {
            Class.forName(className);
            Connection con=DriverManager.getConnection(sqlUrl,sqlUser,sqlPass);
            
            String sql = "UPDATE ORDERDETAILS SET Order_Status = ? WHERE Food_Id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,ch);
            stmt.setInt(2,food_id);
            i = stmt.executeUpdate();
            
            System.out.println("Order " + ch);
            
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
           System.out.println( e );
        }
        return i;
    }

    public static int updateCustomerWalletBalance(String custid, int total)
    {
        int i = 0;
        try
        {
            Class.forName(className);
            Connection con=DriverManager.getConnection(sqlUrl,sqlUser,sqlPass);
            
            String sql = "UPDATE Customer SET Customer_WalletBal = Customer_WalletBal + ? WHERE Customer_Id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,total);
            stmt.setString(2,custid);
            i = stmt.executeUpdate();
            
            System.out.println("Amount refunded successfully");
            
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
           System.out.println( e );
        }
        return i;
    }

    // order food from customer login
    public static int insertDb (int ven_id, String custID, int fid, int fquan, int ftotal)
    {
        int i =0;    
        try
        {
            Class.forName(className);
            String type = "ORDERED";  
            
            Connection con=DriverManager.getConnection(sqlUrl,sqlUser,sqlPass);
            PreparedStatement stmt=con.prepareStatement("insert into orderDetails (Vendor_Id,Customer_Id,Food_Id,Quantity,Date_Time,ETA,Order_Value,Order_Status) values(?,?,?,?,SYSDATE(),ADDTIME(SYSDATE(),'00:15'),?,?)");  
            
            stmt.setInt(1,ven_id);  
            stmt.setString(2,custID);  
            stmt.setInt(3,fid);
            stmt.setInt(4,fquan);   
            stmt.setInt(5,ftotal);
            stmt.setString(6,type);
            
            i = stmt.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }  
        return i;
    }

    // update walletbalance after food is ordered
    public static int updateWalBalDb(String custid, float bal)
    {
        int i = 0;
        try
        {
            Class.forName(className);
            Connection con=DriverManager.getConnection(sqlUrl,sqlUser,sqlPass);
            
            String sql = "UPDATE Customer SET Customer_WalletBal = ? WHERE Customer_Id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setFloat(1,bal);
            stmt.setString(2,custid);
            
            i = stmt.executeUpdate();
            
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println( e );
        }
        return i;
    }

    public static int updatePass(int user, String confirmPass)
    {
        int i = 0;
        try
        {
            Class.forName(className);
            Connection con=DriverManager.getConnection(sqlUrl,sqlUser,sqlPass);
            
            String sql = "UPDATE Customer SET login_password = ? WHERE Username = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,confirmPass);
            stmt.setInt(2,user);
            
            i = stmt.executeUpdate();
            
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println( e );
        }
        return i;
    }

    public static int addFoodItem(String fname, int fprice, int vendorid)
    {
        int i = 0;
        try
        {
            Class.forName(className);
            Connection con=DriverManager.getConnection(sqlUrl,sqlUser,sqlPass);
            
            String sql = "INSERT INTO Menu (Food_Name, Vendor_Id, Food_Price) values (?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,fname);
            stmt.setInt(2,vendorid);
            stmt.setInt(3,fprice);
            
            i = stmt.executeUpdate();
            
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println( e );
        }
        return i;
    }

    public static int modifyFoodPrice(int fid, int fprice, int vendorid)
    {
        int i = 0;
        try
        {
            Class.forName(className);
            Connection con=DriverManager.getConnection(sqlUrl,sqlUser,sqlPass);
            
            String sql = "UPDATE Menu set Food_Price = ? where Food_Id = ? and Vendor_Id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,fprice);
            stmt.setInt(2,fid);
            stmt.setInt(3,vendorid);
            
            i = stmt.executeUpdate();
            
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println( e );
        }
        return i;
    }

}