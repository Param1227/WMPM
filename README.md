## Project Information ##

This file shows you how to run the Project on your system

# Requirements #
 
* Installed Maven
 
# How to Install # 

* Clone the GIT repository

# Clean Install #

* go to the folder where the pom.xml in Terminal
* perform: mvn clean install

# Run # 

After clean install was done type:
mvn jetty:run 

# How to get to the server # 

Go to http://localhost:8080/index.html 
This should open the SwaggerUI.
Then in the top-line of the website insert:
http://localhost:8080/index.html 
click on explore

# How to use the Software # 

Get an offer:
* Open offer part
* insert for example:

Date: 02.08.2016
From: VIE
To: ROM

Select your offer copy the UUID and paste it to the booking part.
Add your name and email adress to book the ticket. 

Then the booking will be done and your Ticket will be posted on Twitter, you get a confirmation via mail and the ticket is persited into the database. 


# How to cancel a ticket: # 

First you can cancel it via the swagger but also via mail!

Swagger:
Insert UUID via Storno Form

Mail: 
Send a mail to: wmpm16.g6@gmail.com with the Subject: "Storno" and UUID of the Ticket"






