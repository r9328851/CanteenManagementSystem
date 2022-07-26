package com.CMS;

import static org.junit.Assert.assertEquals;

import com.CMS.Model.Menu;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

/**
 * Unit test for Menu class.
 */
public class MenuTest 
{
    static Menu menu;
    @BeforeClass
    public static void beforeClass(){
        menu=new Menu(101,"Burger",50001,100);
    }
   
    @Test
    public void testGet(){
       assertEquals(101,menu.getFoodId());
       assertEquals("Burger",menu.getFoodName());
       assertEquals(50001, menu.getVendorId());
       assertEquals(100,menu.getFoodPrice());
    }

    @Test
    public void testToString(){
        String str=menu.toString();
        String expected= "Food id: " + menu.getFoodId() + "\nFood Name: " + menu.getFoodName() + "\nFood Price " + menu.getFoodPrice() + "\nVendor ID: " + menu.getVendorId();
        assertEquals(str,expected);
    }

    @AfterClass
    public static void afterClass(){
        menu=null;
    }
}
