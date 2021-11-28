Innovance project

I used Java 8, Spring boot, MySQL and hibernate.

Before you run this Spring boot project on an IDE, please open MySQL Workbench, create a new or existing connection and run the userScript.sql file and databaseScript.sql file from this link: https://github.com/yozmosis/innovanceSQL_file.git

Once the database and its two tables are setup, you can run the Spring boot project.

This project includes 7 end points for various request types. GET requests are unsecured but the remaining request types have basic authentication enabled, requiring the user name of "innovance" and password of "innovance".

Below I've listed the end points, their descriptions, and an example for each. You could also view the Swagger UI for documentation at http://localhost:8080/swagger-ui/#/ once you run the spring boot project.

GET Requests:

1)  localhost:8080/api/customers

      Description: Returns a list of all the customers

2)  localhost:8080/api/accounts/{customerId}

      example: localhost:8080/api/accounts/1
      
      Description of example: returns customer account details for the customer with primary key Id of 1 (NOT T.C. kimlik number) stored in account table of database

POST Requests:

1)  localhost:8080/api/customers
      
      Description: Saves a new customer to the customer table of the database
      
      Example body: {
                      "firstName": "Faruk",
                      "lastName": "Gulloglu",
                      "email": "faruk@faruk.com",
                      "tckn": 23456
                      }

2)  localhost:8080/api/accounts

    Description: Creates a new account in the account table of the database for an existing customer. A customer can only have one account of any particular currency, otherwise it returns an error.
    
    Example body: {
                    "currency": "CNY",
                    "balance": 10000,
                    "customer_id": 1
                    }

PUT Requests:

1)  localhost:8080/api/customers
    
    Example body: {
                    "id": 1,
                    "firstName": "Jimmy",
                    "lastName": "Yavuz",
                    "email": "jimmyy@jimyy.com",
                    "tckn": 777778
                  }
                  
    Description of example: Updates the details for customer with id of 1. Please note, tckn will remain unchanged even if a different tckn is provided.
 
2)  localhost:8080/api/transfer

    Example body: {
                     "currency": "USD",
                     "amount": 33,
                     "fromCustomerId": 1,
                     "toCustomerId": 2
                   }
    
    Description of example: Transfers 33 USD from customerId 1 to customerId 2. Error handling is in place to make sure sender has enough funds and that receiver has an account for that currency.


DELETE Requests:

 1) localhost:8080/api/accounts/{accountId}
    
    Example url: localhost:8080/api/accounts/3
    
    Description of example: Deletes account with primary key id of 3 from the account table of the database.
    




