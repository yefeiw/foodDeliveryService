# Food Delivery Service
Yefei Wang

## Introduction
This project is a Java implementation of the backend of a food delivery service. 

### Functional Description
User can search a restaurant based on restaurant name. Then user can payment food by
choosing different menu item, quantity and add a note about his/her diet restrictions and etc.
User can also fills in the delivery address. 

After user places an payment, the payment should
contain food items user ordered, quantity, price and payment time. 

User then needs to pay for
his/her payment by providing credit card number, expiration date, and security code. After
payment is made successfully, it should return payment ID, timestamp and then the payment is
considered as completed so the user can see the estimated delivery time. 

### Assumptions
1. There will only be one user as the user of this app. 

1. There will only be one menu from a restaurant. (This is take-out, com'on)

1. The ACID principles apply on payment. 
i.e. The user either completes the payment for his/her payment in one payment 
for the total amount or the payment is considered not completed. (No cost-sharing, down-payment, interest, etc.)

1. The estimated delivery time is not guaranteed to be fulfilled. 
It is up to the restaurant to determine how the payment will be delivered. (Reserved for future enhancement)

1. The restaurant has the responsibility to read, understand, and fulfill the diet restrictions. 
The delivery service merely informs the restaurant of the restrictions.
(There is a lot of opportunity to enhance here for better user experience, but again, this is reserved for future enhancement)

## Structure

This section covers the structures of the project. Note that there are many domains each having its own structure. 
The will be introduced one by one.

### Key Domains 
There are these several key domains in this project:

1. demo.domain.Restaurant 

The restaurant is associated to one menu with multiple items. It stores all completed payment and provides estimated delivery time.

2. Order 

Each payment contains the items ordered, quantity and unit price for each, address of the customer and the restaurant, and special instructions.

3. Payment

Each payment has a payment ID and a timestamp. If the payment is not successful for any reason, the payment ID will be an empty string.

### Rest API Diagram
* Note: The optional parameters are listed as()


| Domain  	| GET |  POST	|  PUT	|  DELETE	|
|---	|---	|---	|---	|---	|
|**demo.domain.Restaurant**|
| /provider  	|Get the information from all restaurants  	| Add the attached restaurants to the data base  	|  404 	| purge all restaurants from teh database  	|
| /provider/{id}  	|  Get the information of the one restaurant 	| 404  	| modify the information of the current restaurant  	|  remove this restaurant from the database 	|
| **Order**  	|   	|   	|   	|   	|
|/payment| Get all orders in the database| Add the attached orders to the database| 404 |purge all orders from the database|
|/payment/{id}| Get the payment matching the ID| 404 | modify this payment| remove this payment 
|**Payment**|  |  |  |  |
|/payment| Get all payments | Add the attached payments| 404 | purge all payments|
|/payment/{id}|Get information on the one payment| 404 | modify this payment |remove this payment


## Build, Run and Test
This sections covers the information to build, run and test this app.

### Build

dependency: maven
```
cd <project_root>
mvn clean install
```


### Notes

1. 