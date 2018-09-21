# NessieProject

## Setup
1. Install Android Studio
https://developer.android.com/studio/install
2. `git clone` the repo
3. Open project in AS
4. Android Studio > Preferences > Appearance & Behavior > System Settings > HTTP Proxy
Set manual proxy configuration if needed
5. Gradle Sync (Can manually trigger this with File > Sync Project with Gradle Files) This will take a while
6. Instant run on phone or emulator (follow steps for emulator. Downloading may take a while)
7. If build does not run right away, try clean and building

## About
This project is an Android app written primarily in Kotlin using MV-VM architecture and retrofit2 for API calls.
I created this app to explore the Nessie API and learn some Android skills. I also learned some things about two-way databinding,
live data and architecture components!  
This is a work in progress and not all the error handling is completed so the app _may_ crash on some unexpected input.  
Most of the API requests will show if the request was successful or not by a toast message on the bottom of the Android app.
The only time it does not do this is when the results are obvious (AKA a GET function that is unsuccessful will show a message
where the account/bill should be).

## Navigation and API calls
### Customer Account
If no customer account associated, allows you to create a customer account. (@POST("/customers"))  
If a customer account is already created, you will see a page with more choices
#### Get Account Info
This shows all the accounts that are associated with the customer (@GET("/customers/{customer_id}/accounts")) 
##### Deposit
You can deposit some money into your account. Note that this request is successful but something changes the status on
the backend to change from "pending" to "cancelled" so you won't see your balance updated. Currently looking into this.
(@POST("/accounts/{id}/deposits"))
##### Order Pizza
This is simply a purchase endpoint. It goes to a merchant that I created called "Pizzaria" and orders a $5 pizza.
Besides seeing "Request was successful!" you won't see any changes on the client side. This does not make a withdrawl
from the account. That is a feature I want to add in the future. (@POST("/accounts/{id}/purchases"))
#### Add Account
Here you can add an account. Ensure that the account number is 16 digits. (@POST("/customers/{customer_id}/accounts"))  
You can ensure this works by going back to `Get Account Info` and seeing your new account
#### Delete All Accounts
This will delete all accounts associated with this customer! (@DELETE("/data"))
#### View Upcoming Bill
If you have posted a bill with any of the accounts, only the NEWEST bill will appear below the button when you click. 
There is currently no way to post a bill through the app at this time. (@GET("/customers/{customer_id}/bills"))
### ATMS
Input Latitude, Longitude and Radius to get nearby atms. If no atms appear, increase your radius. 39, -77 with a radius of 5 always 
works. This uses pagination, so when scrolling through atms it will query more atms. (@GET("/atms"))
### Find Pizza
This uses the enterprise endpoint to find all the merchants that are in the database and then filter only the ones that have
"Pizza" in the name. (@GET("/enterprise/merchants"))

