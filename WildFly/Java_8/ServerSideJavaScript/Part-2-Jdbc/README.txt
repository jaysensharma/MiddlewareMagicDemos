This example explains how we can use the Nashorn Java Script to interact with the Database.

MySQL Part
----------------------------------
Use MySQL Database and do the following.
mysql -u root -p 


mysql> create database TestJavaScriptDB;
Query OK, 1 row affected (0.00 sec)


mysql> use TestJavaScriptDB;
Database changed


mysql> CREATE TABLE CUSTOMER (custId INT(10) NOT NULL, custName CHAR(20) NOT NULL);
Query OK, 0 rows affected (0.02 sec)

mysql> INSERT INTO CUSTOMER VALUES (1000, "MiddlewareMagic");
Query OK, 1 row affected (0.00 sec)

mysql> INSERT INTO CUSTOMER VALUES (2000, "Customer-2");
Query OK, 1 row affected (0.01 sec)

mysql> INSERT INTO CUSTOMER VALUES (3000, "Customer-3");
Query OK, 1 row affected (0.00 sec)

mysql> SELECT * FROM CUSTOMER;
+--------+-----------------+
| custId | custName        |
+--------+-----------------+
|   1000 | MiddlewareMagic |
|   2000 | Customer-2      |
|   3000 | Customer-3      |
+--------+-----------------+
3 rows in set (0.01 sec)




#######################################
To know more about this demo refer to:
#######################################

JDK8 Based Nashorn JavaScript & java to interact with MySQL Database.
http://middlewaremagic.com/jboss/?p=2760
