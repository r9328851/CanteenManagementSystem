package com.CMS.Cli;

import java.util.Scanner;

import com.CMS.Factory.OrderFactory;
import com.CMS.Model.Customer;
import com.CMS.Model.Menu;
import com.CMS.Model.OrderDetails;
import com.CMS.Model.Vendor;

/**
 * CliMain used as Client interface for java coding.
 * @author RohithDondapati (Hexaware)
 */
public class CliMain
{
    
    static Scanner sc=new Scanner(System.in);
    
    /**
     * main method  used to display the options we had in the application.
     */
    public static void main( String[] args )
    {   
        System.out.println("---------------------------" );
        System.out.println("Canteen Management System" );
        System.out.println("---------------------------" ); 
        System.out.println("Enter your choice....");
        System.out.println("1. Show Today's Menu");
        System.out.println("2. Vendor Login");
        System.out.println("3. Customer Login");
        System.out.println("4. Exit the page\n");

        // choose the options
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                menuList();
                Runtime.getRuntime().halt(0);
                break;
            case 2:
                vendorProfile();    
                break;
            case 3:
                customerProfile();
                break;
            case 4:
                Runtime.getRuntime().halt(0);
            default:
                System.out.println("Choose options between 1 and 4");
                main(args);
        }
    }

    /**
     * this method is to fetch Menu list.
     */
    public static void menuList()
    {
        // fetches the food menu
        Menu m[]=OrderFactory.fetchMenu();
        System.out.println("\nFood Id"+"\t"+"Food Name"+"\t\t\t"+"Food Price"); 
        for(int i=0; i<m.length; i++)
        {
              System.out.println(m[i].getFoodId()+"\t"+m[i].getFoodName()+"\t\t\t"+m[i].getFoodPrice());
        }
    }

    /**
     * this method is for VendorProfile.
     */
    public static void vendorProfile()
    {

        // prompt the user -> Enter Vendor ID:
        System.out.println( "\nEnter Vendor Id: ");
        int venId = sc.nextInt();

        // fetch vendorObject for this VendorId
        Vendor ven = OrderFactory.validateVendor(venId);

        // if validates vendorObject 
        if ( ven == null )
        {
            System.out.println("\nInvalid Vendor id");
            vendorProfile();
        }
        else
        {
            vendorOptions(ven.getVenId(),ven);
        }
    }

    /**
     * this method is for Vendor options after vendor loggin in.
     */
    public static void vendorOptions(int venID, Vendor ven)
    {
        System.out.println("---------------------------\nCanteen Management System\n---------------------------");
        System.out.println("Welcome Back\n---------------------------" );

        // display vendor options
        System.out.println("\n1. Accept Or Reject Order \n2. See Vendor Order History \n3. My Profile \n4. Add food items \n5. Modify Food Price \nPress any other number to exit \n");
        int choice = sc.nextInt();
        sc.nextLine();
        switch(choice)
        {
            case 1:
                String type = "ORDERED";
                OrderDetails[] od = OrderFactory.Order_Status(type,venID);
                for ( OrderDetails o: od )
                {
                    System.out.println( o );
                    System.out.println("Enter food id to accept or reject the order");
                    int food_id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("\nEnter your choice as \"ACCEPTED\" or \"REJECTED\" the order.");
                    String ch = sc.nextLine();
                    int i = OrderFactory.acceptRejectOrder(food_id,ch);
                    if(ch.equals("REJECTED"))
                    {
                        String custid = o.getCustomerId();
                        int total= o.getOrderValue();
                        updateCustWallet(custid,total);
                    }
                    if(i==1){
                        System.out.println("Order has been" + ch);
                    }
                }
                System.out.println("\nNo more pending orders to show.");
                Runtime.getRuntime().halt(0);
                break;
            case 2:
                VendorOrderHistory(venID);
                Runtime.getRuntime().halt(0);
                break;
            case 3:
                System.out.println("Vendor Id = " + ven.getVenId());
                System.out.println("Vendor Name = " + ven.getVenName());
                System.out.println("Vendor Phone = " + ven.getVenPhone());
                System.out.println("Vendor Specialization = " + ven.getVenSpecs());
                break;
            case 4:
                System.out.println("Enter Food Name : ");
                String fname = sc.nextLine();
                System.out.println("Enter Food Price : ");
                int fprice = sc.nextInt();
                int vendorid = ven.getVenId();
                int i = OrderFactory.addFood(fname,fprice,vendorid);
                if(i == 1)
                {
                    System.out.println("Added food item successfully");
                }
                else
                {
                    System.out.println("Enter the details correctly.");
                }
                Runtime.getRuntime().halt(0);
                break;
            case 5:
                i = 0;
                System.out.println("\nEnter the Food id: ");
                int fid=sc.nextInt();

                Menu m = OrderFactory.validate(fid);
                fid = m.getFoodId();
                System.out.println("Enter the Food Price: ");
                int foodprice = sc.nextInt();
                vendorid = m.getVendorId();
                i = OrderFactory.modifyFood(fid,foodprice,vendorid);
                if(i == 1)
                {
                    System.out.println("Modified food price successfully");
                }
                else
                {
                    System.out.println("Enter the details correctly.");
                }
                Runtime.getRuntime().halt(0);
                break;
            default:
                System.out.println("\nSuccessfully exit");
                Runtime.getRuntime().halt(0);
        }
    }

    /**
     * this method refunds the money to customer
     */
    public static void updateCustWallet(String custid, int total){
        OrderFactory.updateCustomerWallet(custid,total);
    }

    /**
     * this method is for VendorOrderHistory.
     */
    public static void VendorOrderHistory(int venID)
    {
        // fetches that vendor specific history only.
        OrderDetails[] od = OrderFactory.vendorOrderHistory(venID);
        for ( OrderDetails o: od )
            System.out.println(o);
    }

    /**
     * this method is for customerProfile.
     */
    public static void customerProfile()
    {
        // prompt the user -> Enter Login ID:
        System.out.println("\nEnter Login Id: ");
        int user = sc.nextInt();
        sc.nextLine();
        
        // prompt the user -> Enter Password
        System.out.println("Enter Password: ");
        String custPsw = sc.nextLine();

        //fetch customer details
        Customer[] cArray = OrderFactory.customerProfile(user);

        // fetch customerObject for this LoginId
        Customer cust = OrderFactory.validateCustomer(user, custPsw);
        
        // if validates customerObject
        if (cust == null)
        {
            System.out.println("\nInvalid Login Credentials\n");
            customerProfile();
        }
        else
        {
            // options to diplay after customer logged in.
            System.out.println("---------------------------\nCanteen Management System\n---------------------------");
            System.out.println("Welcome Back\n---------------------------" );

            // display options available for that customer in his account.
            System.out.println("1. Order Food\n2. View My Purchase History\n3. My Profile\n4. Change Password\n5. Log Out\n");
            
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice)
            {
                case 1:
                    menuList();
                    placeOrder();    
                    break;
                case 2:
                    CustomerOrderHistory(user);
                    break;
                case 3:
                    // displays logged in customer details
                    for ( Customer c: cArray )
                        System.out.println(c);
                    break;
                case 4:
                    changePassword(user,custPsw);
                    break;
                case 5:
                    System.out.println("\nYou have successfully logged out.\nTo order food Login again.");
                    Runtime.getRuntime().halt(0);
                default:
                    System.out.println("\nInvalid option selected.\nLogging you out");
                    Runtime.getRuntime().halt(0);
            }
        }
    }

    /**
     * this method is to change the customer login password
     */
    private static void changePassword(int user, String custPsw)
    {
        System.out.println("\n\nEnter Current Password: ");
        String cPass = sc.nextLine();
        if(custPsw.equals(cPass))
        {
            System.out.println("\nEnter the New Password: ");
            String newPass = sc.nextLine();
            System.out.println("\nConfirm your New Password: ");
            String confirmPass = sc.nextLine();

            if(confirmPass.equals(newPass))
            {
                int i = OrderFactory.changePass(user, confirmPass);
                if(i == 1)
                {
                    System.out.println("\n\nYour Account Password was changed successfully.");
                    System.out.println("\nLogin again into your account with new Password.");
                    customerProfile();
                }
                else
                {
                    System.out.println("\nError Occured, Try After Sometime.");
                }
            }
            else
            {
                System.out.println("\nPasswords does not match");
                changePassword(user, custPsw);
            }
        }
        else
        {
            System.out.println("\nWrong Password!");
            changePassword(user, custPsw);
        }
    }

    /**
     * this method  is to place food order.
    */ 
    public static void placeOrder()
    {
        System.out.println("\nEnter the Food id:");
        int fid=sc.nextInt();

        Menu m = OrderFactory.validate(fid);

        if (m == null)
        {
            System.out.println("Invalid food id.\nPlease enter the correct food id.");
            placeOrder();
        }
        else
        {
            System.out.println("Enter the Food Quantity:");
            int fquan = sc.nextInt();
            int fprice = m.getFoodPrice();
            String fname = m.getFoodName();
            int venId = m.getVendorId();
            int ftotal = fquan*fprice;
            
            System.out.println("\nYou are redirecting to payment page...");
            System.out.println("----------------------------------------------");
            System.out.println("\nAuthentication Required.....\n");
            System.out.println("Enter you login credentials to debit the required amount from your wallet");
            // prompt the user to enter login id
            System.out.println("\nEnter Login Id: ");
            int user = sc.nextInt();
            sc.nextLine();
            
            // prompt the user -> Enter Password
            System.out.println("Enter Password: ");
            String custPsw = sc.nextLine();
            
            // customer validation to for placing order
            Customer c = OrderFactory.validateCustomer(user, custPsw);

            String custId = c.getCustId();
            float walletBalance = c.getCustWalletBalance();

            if(walletBalance >= ftotal)
            {
                int b = updateWalletBalance(custId,walletBalance,ftotal);
                if(b == 1)
                {
                    int i = OrderFactory.OrderFood(venId,custId,fid,fquan,ftotal);
                    if(i == 1)
                    {
                        System.out.println("\nYour Order for " + fname + " has been placed.");
                        System.out.println("Thank You. Visit the website again for more food items.");
                    }
                    else
                    {
                        System.out.println("\nUnexpected error occured. Try again");
                        placeOrder();
                    }
                }
            }
            else{
                System.out.println("\nInsufficient wallet balance in ur account.\nAdd the required amount to place the order.");
            }
        }
    }
    
    /**
     * this method is for updating customers wallet balance
     */
    public static int updateWalletBalance(String custid, float bal, int ftotal)
    {
        bal -= ftotal;
        int i = OrderFactory.updateWalBal(custid,bal);
        if(i == 1)
        {
            System.out.println("\nRs." + ftotal + " has been deducted successfully.");
        }
        else
        {
            Runtime.getRuntime().halt(0);
        }
        return i;
    }

    /**
     * this method is for CustomerOrderHistory.
     */
    public static void CustomerOrderHistory(int loginID)
    {
        OrderDetails[] od = OrderFactory.customerOrderHistory(loginID);
        for ( OrderDetails o: od )
            System.out.println(o);
    }

}
