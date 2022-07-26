package com.main;

import java.util.Scanner;

import com.beans.Customer;
import com.beans.Login;
import com.beans.fooditem;
import com.beans.order;
import com.beans.vendor;
import com.service.CustomerService;
import com.service.FoodService;
import com.service.OrderService;
import com.service.VendorService;

/**
 * App is used as Client interface for java coding.
 * @author RohithDondapati
 */
public class App {
	/**
     * main method  used to display the options we had in the application.
     */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String username= "";
		Login login = new Login(null, null); 
		VendorService vendorService = new VendorService(); 
		while(true) {
			System.out.println("--------------------------------");
			System.out.println("    Canteen Management System   ");
			System.out.println("--------------------------------");
			System.out.println("1. For Customer Login");
			System.out.println("2. For Registering New Customer");
			System.out.println("3. Change Password");
			System.out.println("4. For Logging Out");
			System.out.println("5. For Vendor Login");
			
			// Select from the options displayed
			System.out.println("Enter your choice");
			int input = sc.nextInt();
			if(input == 4) {
				System.out.println("Logging out...");
				System.out.println("Bye " + username);
				break; 
			}
			
			switch(input) {
				case 1:
					System.out.println("Enter Customer Username");
					sc.nextLine();
					username=sc.nextLine();
					login.setUsername(username);
					
					System.out.println("Enter Password");
					java.lang.String password=sc.nextLine();
					login.setPassword(password);
					
					//check if username and password are present in User table 
					CustomerService customerService = new CustomerService();
					boolean status = customerService.checkLogin(login);
					if(status == true) {
						//display the full menu
						String name = vendorService.fetchcustomerNameByUsername(username);
						System.out.println("Welcome " + name);
						while(true) {
							System.out.println("1. Show menu");
							System.out.println("2. Placing order");
							System.out.println("3. Order history");
							System.out.println("4. profile");
							System.out.println("5. Wallet balance");
							System.out.println("6. Cancel Order");
							System.out.println("7. Previous Menu");
							input = sc.nextInt(); 
							if(input == 7) {
								break; 
							}
							switch(input) {
								case 1: 
									/* ========MUST_HAVE===========
									 * From fooditem table, display 
									 * food_id | food_name   | food_price
									 * 
									 * code tip: create FoodItem class, in DB(ResultSet) 
									 * fetch each row into FoodItem object, add object to List 
									 * and iterate through list.  
									 */
									fooditem x[]=FoodService.fetchMenu();
									System.out.println("food_id | food_name   | food_price");
									for(int i=0 ;i<x.length ;i++) {
										System.out.println(x[i].getFoodId()+"\t" +x[i].getFoodName()+"\t"+x[i].getPrice());
									}
									break;
								case 2: 
									/*  ========MUST_HAVE===========
									 * Place Order
									 * show foodItem menu => ask(foodItem ID) => ask(quantity)
									 * compute(total_amount=foodItem_price*quantity)//400
									 * go to DB=> fetch customer details based on username=> (customer)
									 * check(customer.walletBalance>total_amount?insert into order history table: 
									 * display "less balance message". )
									 * Note: for date_time: use LocalDate.now() 
									 */
									fooditem x1[]=FoodService.fetchMenu();
									System.out.println("food_id | food_name   | food_price");
									for(int i=0 ;i<x1.length ;i++) {
										System.out.println(x1[i].getFoodId()+"\t" +x1[i].getFoodName()+"\t"+x1[i].getPrice());
									}
									System.out.println("\nEnter the Food id:");
							        int fid=sc.nextInt();

							        fooditem x11 = FoodService.validate(fid);

							        if (x11 == null)
							        {
							            System.out.println("Invalid food id.\nPlease enter the correct food id.");
							        }
							        else
							        {
							            System.out.println("Enter the Food Quantity:");
							            int fquan = sc.nextInt();
							            sc.nextLine();
							            double fprice = x11.getPrice();
							            java.lang.String fname = x11.getFoodName();
							            String venId = x11.getVendorId();
							            double ftotal = fquan*fprice;
							            
							            System.out.println("\nYou are redirecting to payment page...");
							            System.out.println("----------------------------------------------");
							            System.out.println("\nAuthentication Required.....\n");
							            System.out.println("Enter you login credentials to debit the required amount from your wallet");
							            // prompt the user to enter login id
							            System.out.println("\nEnter Customer Username: ");
										username=sc.nextLine();
										login.setUsername(username);
							            
							            // prompt the user -> Enter Password
							            System.out.println("Enter Password: ");
										password=sc.next();
										login.setPassword(password);
							            
										status = false;
										
							            // customer validation to for placing order
										status = customerService.checkLogin(login);
										if(status == true)
										{
								            String custId = username;
								            double walletBalance = CustomerService.WalletBalance(username);
	
								            if(walletBalance >= ftotal)
								            {
								                double bal = 0;
								                bal=walletBalance- ftotal;
								                int b = CustomerService.updateWalBal(custId,bal);
								                if(b >= 1)
								                {
								                    System.out.println("\nRs." + ftotal + " has been deducted successfully.");
								                    int i = FoodService.OrderFood(venId,custId,fid,fquan,ftotal);
								                    if(i >= 1)
								                    {
								                        System.out.println("\nYour Order for " + fname + " has been placed.");
								                        System.out.println("Thank You. Visit the website again for more food items.");
								                    }
								                    else
								                    {
								                        System.out.println("\nUnexpected error occured. Try again");
								                    }
								                }
								                else
							                    {
							                        System.out.println("\nUnexpected error occured. Try again");
							                    }
								            }
								            else
								            {
								                System.out.println("\nInsufficient wallet balance in ur account.\nAdd the required amount to place the order.");
								            }
										}
										else
										{
											System.out.println("\n Invalid Credentials");
										}
							        }
									break;
								case 3:
									/* ========MUST_HAVE===========
									 * go to DB=> fetch records from order_details based on customer username. 
									 * In SQL Query: connect(join) order_details to customer, 
									 * then customer to login 
									 * and apply criteria on login.username. 
									 * take list from DB and iterate over it. 
									 */
									order[] odArr = OrderService.custOrderHist(username);
									for ( order o: odArr )
										System.out.println(o);
									break;
								case 4:
									/* ========MUST_HAVE===========
									 * Go to DB and fetch all customer info, along with username and display 
									 * For username info, join customer to login
									 */
									Customer[] profile = CustomerService.MyProfile(username);
									for (Customer c: profile)
										System.out.println(c);
									break;
								case 5:
									/* ========MUST_HAVE===========
									 * go to DB and fetch wallet balance of the customer 
									 */
									System.out.println("Your Current Wallet Balance is Rs." + CustomerService.WalletBalance(username));
									break;
								case 6:
									/* ========MUST_HAVE===========
									 * Show all PENDING orders of customer based on username.
									 * for this=> connect login to customer and customer to order_details  
									 * Take order ID as input from the user and update the order_status as
									 * "CANCELLED". 
									 *   
									 */
									String type = "ORDERED";
									order[] od = OrderService.customerOrderStatus(type,username);
									for ( order o: od )
									{
										System.out.println( o );
									}
									
									System.out.println("Enter order id for which you want to cancel");
									int order_id = sc.nextInt();
									sc.nextLine();
									System.out.println("Enter 1 to cancel the order");
									int m = sc.nextInt();
									if(m == 1){
										int n = VendorService.acceptRejectOrder(order_id,"CANCELLED");
										if(n >= 1){
											order[] o = OrderService.OrderFetch(order_id);
											for(int g = 0; g < o.length; g++)
											{
												String custid = o[g].getCustomerId();
												double total= o[g].getOrderValue();
												CustomerService.updateCustWallet(custid,total);
											}
										}
									}
									else{
										System.out.println("Please enter 1 to cancel the order");
									}
									break;
								default:
									System.out.println("Invalid Input");
									break;
							}
						}
						
					}
					else {
						System.out.println("Invalid Credentials, please try again");
					}
					break;
				case 2:
					/* ========NICE_TO_HAVE===========
					 * Register New Customer
					 * Take Input from Customer: customer_name,username,password 
					 * set customer_coupon="something", customer_walletBalance=0   
					 * Save customer info in customer table and login info in login table and 
					 * preserve login_id FK in customer table
					 */ 
					int i = 0;
					System.out.println("Enter Username");
					sc.nextLine();
					username=sc.nextLine();
					login.setUsername(username);
					
					System.out.println("Enter password");
					password = sc.nextLine();
					login.setPassword(password);
					i = CustomerService.insertingNewUser(login);
					if(i == 1){
						System.out.println("Registered successfully");
					}
					else{
						System.out.println("Registration is unsuccessfull, Try Again Later");
					}
					break;
				case 3:
					/* ========NICE_TO_HAVE===========
					 * Change Password:
					 * Take username from user 
					 * if the username is present in the DB, ask for new password
					 * and update the password in the DB
					 */
					System.out.println("Enter Username");
					sc.nextLine();
					username=sc.nextLine();
					Login p = CustomerService.CheckUsername(username);
					if(p !=null){
						System.out.println("Enter New Password");
						password = sc.nextLine();
						System.out.println("Enter again to confirm the password");
						String confirmpass = sc.nextLine();
						if(confirmpass.equals(password)){
							int c= CustomerService.changePass(username,confirmpass);
							if(c >=1){
								System.out.println("Password updated successfully.");
							}
							else{
								System.out.println("Password not updated, Try after sometime");
							}
						}
						else{
							System.out.println("Wrong Passwords");
						}
					}
					else{
						System.out.println("Invalid Credentials");
					}
					break;
				case 5: 
					System.out.println("Enter Vendor Username");
					sc.nextLine();
					username=sc.nextLine();
					login.setUsername(username);
					
					System.out.println("Enter Password");
					password=sc.next();
					login.setPassword(password);
					status = vendorService.checkLogin(login);
					if(status == true) {
						//display the full vendor menu
						String name = vendorService.fetchNameByUsername(username);
						System.out.println("Welcome " + name);
						while(true) {
							System.out.println("1. Show menu");
							System.out.println("2. Accept & Reject");
							System.out.println("3. Order History");
							System.out.println("4. Edit Menu");
							System.out.println("5. Profile");
							System.out.println("6. Previous menu");
							input = sc.nextInt();
							if(input == 6) {
								break; 
							}
							switch(input) {
							case 1: 
								/* ========NICE_TO_HAVE===========
								 * fetch fooditem rows based on vendor username
								 * connect fooditem => vendor => login  
								 * iterate over result 
								 */
								fooditem f[]=VendorService.fetchMenu(username);
								System.out.println("food_id | food_name   | food_price");
								for(int j=0 ;j<f.length ;j++) {
									System.out.println(f[j].getFoodId()+"\t" +f[j].getFoodName()+"\t"+f[j].getPrice());
								}
								break; 
							case 2:
								  /* ========COULD_HAVE===========
								   * ACCEPT+REJECT 
								   * show all orders based on vendor username as done in case 3
								   * Ask for Order_details_id. 
								   * give 2 options 
								   * 1. ACCEPT ORDER
								   * 2. REJECT ORDER 
								   * if input=1?update order_status= "ACCEPT": update order_status= "REJECT"
								   * based on Order_details_id
								   */
								String type = "ORDERED";
								order[] od = OrderService.Order_Status(type,username);
								for ( order o: od )
								{
									System.out.println( o );
								}
								System.out.println("Enter order id to accept or reject the order");
								int order_id = sc.nextInt();
								sc.nextLine();
								System.out.println("Enter 1 to accept, 2 to reject the order");
								int m = sc.nextInt();
								switch(m){
									case 1: VendorService.acceptRejectOrder(order_id,"ACCEPTED");
											break;
									case 2: int n = VendorService.acceptRejectOrder(order_id,"REJECTED");
											if(n >= 1){
												order[] o = OrderService.OrderFetch(order_id);
												for(int g = 0; g < o.length; g++)
												{
													String custid = o[g].getCustomerId();
													double total= o[g].getOrderValue();
													CustomerService.updateCustWallet(custid,total);
												}
											}
											break;
									default:break;
								}
								break;
							case 3: 
								/* ========NICE_TO_HAVE===========
								 * Fetch order_details based on vendor username
								 * order_details=>fooditem=>vendor=>login 
								 * iterate over order_details rows 
								 */
								order[] odArr = OrderService.venOrderHist(username);
								for ( order o: odArr )
									System.out.println(o);
								break;
							case 4: 
								/* ========COULD_HAVE===========
								 * EDIT MENU
								 * Show menu as shown in case 1
								 * Ask vendor which fooditemID it wants to edit : fooditem_id=4
								 * Ask new values of name and price of the fooditem. 
								 * go to DB and update this new values of name and price for given
								 * fooditemID
								 */
								i = 0;
								fooditem h[]=VendorService.fetchMenu(username);
								System.out.println("food_id | food_name   | food_price");
								for(int j=0 ;j<h.length ;j++) {
									System.out.println(h[j].getFoodId()+"\t" +h[j].getFoodName()+"\t"+h[j].getPrice());
								}
								System.out.println("Enter food id which you want to edit:");
								int foodid = sc.nextInt();
								System.out.println("Enter Food name:");
								sc.nextLine();
								String foodName = sc.nextLine();
								System.out.println("Enter food price:");
								double foodPrice = sc.nextDouble();
								VendorService.ModifyFood(foodid, foodName, foodPrice);
								break;
							case 5: 
								/* ========NICE_TO_HAVE===========
								 * Display vendor info based on vendor username
								 * vendor=> login
								 */
								vendor[] profile = VendorService.MyProfile(username);
								for (vendor v: profile)
									System.out.println(v);
								break;
							}
						} 
					}
					else {
						System.out.println("Invalid Credentials, please try again");
					}
					break;
				default:
					System.out.println("Invalid Input");
					break;
			}
		}
		sc.close();
	}

}

