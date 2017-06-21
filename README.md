# Food Delivery Service
Yefei Wang

## Introduction
This project is a Java implementation of the backend of a food delivery service. 

### Functional Description
User can search a restaurant based on restaurant name. Then user can order food by
choosing different menu item, quantity and add a note about his/her diet restrictions and etc.
User can also fills in the delivery address. 

After user places an order, the order should
contain food items user ordered, quantity, price and order time. 

User then needs to pay for
his/her order by providing credit card number, expiration date, and security code. After
payment is made successfully, it should return payment ID, timestamp and then the order is
considered as completed so the user can see the estimated delivery time. 

### Assumptions

1. There will only be one menu from a restaurant. (This is take-out, com'on)

2. The ACID principles apply on payment. 
i.e. The user either completes the payment for his/her order in one payment 
for the total amount or the payment is considered not completed. (No cost-sharing, down-payment, interest, etc.)

3. The estimated delivery time is not guaranteed to be fulfilled. 
It is up to the restaurant to determine how the order will be delivered. (Reserved for future enhancement)

4. The restaurant has the responsibility to read, understand, and fulfill the diet restrictions. The delivery service merely informs the customer of the restrictions.
(There is a lot of opportunity to enhance here for better user experience, but again, this is reserved for future enhancement)

## Structure

This section covers the structures of the project. Note that there are many domains each having its own structure. 
The will be introduced one by one.

### Key Domains 
There are these several key domains in this project:

1. Restaurant 

The restaurant is associated to one menu with multiple items. It stores all completed order and provides estimated delivery time.

2. Order 

Each order contains the items ordered, quantity and unit price for each, address of the customer and the restaurant, and special instructions.

3. Payment

Each payment has a payment ID and a timestamp. If the payment is not successful for any reason, the payment ID will be an empty string.

### Rest API Diagram
* Note: The optional parameters are listed as()
| Domain | GET | POST | PUT | DELETE|
| --- | --- | --- | --- |
| /provider | Get the list of all restaurants | Add the list of restaurants in the payload to the database | 404  | purge all restaurants from the database |
| /provider/{id}/ Get the information of one restaurant | 404 | Update information of this restaurant | remove this restaurant |
