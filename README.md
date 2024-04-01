# demo_shopping_cart

This is a demo project on a shopping cart application containing APIs that facilitate user sign up, login, view/add shopping items onto cart. 
The goal is to apply discount based on following set of rules 

On a retail website, the following discounts apply:
1.	If the user is an employee of the store, he gets a 30% discount
2.	If the user is an affiliate of the store, he gets a 10% discount
3.	If the user has been a customer for over 2 years, he gets a 5% discount.
4.	For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
5.	The percentage based discounts do not apply on groceries.
6.	A user can get only one of the percentage based discounts on a bill.

Technology stack:

Spring Boot3.2.4
JDK 17
H2 databased (in memory store)
Mockito/Junit for unit tests
Spring security.

Primarily we have 3 usecases as below

1) Customer flow - to process API calls for user signup and login authentication
2) Shopping line items - to process all shopping cart items, group and distinguish between grocery and non grocery ones
3) Order processing - to process a customer order and calculate the discount based on above listed business rules.


Unit test cases are added and mocked at controller and service layer.
