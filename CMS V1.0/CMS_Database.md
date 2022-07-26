## Create Database CMSDB70622
CREATE DATABASE CMSDB70622;
USE CMSDB70622;

## Tables required
## CustomerProfile table
CREATE TABLE Customer(
Customer_Id VARCHAR(25) UNIQUE NOT NULL PRIMARY KEY,
Customer_Name VARCHAR(50) NOT NULL,
Username INTEGER UNIQUE NOT NULL,
login_password VARCHAR(25) NOT NULL,
Customer_Phone INTEGER UNIQUE NOT NULL,
Customer_Email VARCHAR(25) UNIQUE NOT NULL,
Customer_Coupon VARCHAR(25),
Customer_WalletBal DECIMAL(9,2)
);

## Inserting Duplicate Values
INSERT INTO Customer VALUES
('101','Rohith',137638,'Rohith@143',901078056, 'r9328851@gmail.com','FLAT50',1230.35),
('102','Harish',742748,'Harish255',630109044,'hari143@gmail.com','ORDER30',2000.25),
('103','Kalyani',237846,'Kalyanisk@84',634062647,'kallusk@gmail.com','HUNGRY60',190.50);

## Display customer table data
SELECT * FROM Customer;

## VendorProfile
CREATE TABLE Vendor(
Vendor_Id INTEGER UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
Vendor_Name VARCHAR(50) NOT NULL,
Vendor_phone INTEGER UNIQUE NOT NULL,
Vendor_Specs VARCHAR(50) NOT NULL
);

## Inserting Duplicate Values
INSERT INTO Vendor VALUES
(50001, 'Narasimha', 824642863, 'South-Indian'),
(50002, 'Prasanna', 743848243, 'North-indian'),
(50003, 'Anjali', 932983005, 'Italian Cuisine');

## Display Vendor table data
SELECT * FROM Vendor;

## Menu table
CREATE TABLE Menu(
Food_Id INTEGER UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
Food_Name VARCHAR(25) NOT NULL,
Vendor_Id INTEGER NOT NULL,
Food_Price DECIMAL(9,2),
CONSTRAINT vendor_id_fk FOREIGN KEY(Vendor_Id) REFERENCES Vendor(Vendor_Id)
);

## Inserting Duplicate Values
INSERT INTO Menu VALUES
(1, 'Aalo Paratha', '50002', 100.00),
(2, 'Pav Bhaji', '50002',150.00),
(3, 'Chole Bhature','50002', 130.00),
(4, 'Masala Dosa', '50001',60.00),
(5, 'Hyderabadi Dum Biryani', '50001',180.00),
(6, 'Sambar Idly', '50001',50.00),
(7, 'Malabar Parota', '50001',110.00),
(8, 'Pizza Margherita', '50003',170.00),
(9, 'Lasagne', '50003',140.00),
(10, 'Arrosticini', '50003',200.00);

## Display Food menu table data
SELECT * FROM Menu;

## Orderfood table
CREATE TABLE orderDetails(
Order_No INTEGER UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
Vendor_Id INTEGER NOT NULL,
Customer_Id VARCHAR(25) NOT NULL,
Food_Id INTEGER NOT NULL,
Quantity INTEGER NOT NULL,
Date_Time DATETIME,
ETA DATETIME DEFAULT "2022-04-08 00:15:00",
Order_Value DECIMAL(9,2),
Order_Status ENUM('ACCEPTED', 'REJECTED', 'ORDERED'),
CONSTRAINT customer_id_fk FOREIGN KEY(Customer_Id) REFERENCES Customer(Customer_Id),
CONSTRAINT vendor_id_fk2 FOREIGN KEY(Vendor_Id) REFERENCES Vendor(Vendor_Id),
CONSTRAINT food_id_fk FOREIGN KEY(Food_Id) REFERENCES Menu(Food_Id)
);

## Inserting Duplicate Values
INSERT INTO orderDetails VALUES
(1, '50001', '101', 6, 2, "2022-04-05", "2022-04-05 00:13:00", 100.00, "ORDERED"),
(2, '50002', '101', 2, 1, "2022-04-06", "2022-04-06 00:14:00", 150.00, "ORDERED"),
(3, '50003', '103', 10, 1, "2022-04-06", "2022-04-06 00:18:00", 200.00, "ORDERED"),
(4, '50001', '102', 7, 1, "2022-04-07", "2022-04-07 00:12:00", 110.00, "ORDERED"),
(5, '50002', '102', 3, 1, "2022-04-07", "2022-04-07 00:15:00", 130.00, "ORDERED");

## Display Orderdetails table data
SELECT * FROM orderDetails;
