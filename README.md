# Booking show System
This is a case study for booking show.
Created By: Apurba Panda
Contact: 9564053407

## Setup
To Run: bootrun --args='--spring.profiles.active=dev'

## DB Details
H2-Console: http://localhost:8080/
URL: jdbc:h2:mem:booking-show
username: apurba

## API Usage (Swagger UI Documentation)
Please check image directory for more details.

Swagger home: http://localhost:8080/swagger-ui/index.html#/
![img.png](Images/img11.png)

Admin -> setup show
![img.png](Images/img.png)

User -> Check Available Seat
![img_1.png](Images/img_1.png)

User -> Book Ticket 
![img_2.png](Images/img_2.png)

User --> Trying to book already booked ticket 
![img_3.png](Images/img_3.png)

User --> Trying to book ticket with used phone number 
![img.png](Images/img5.png)

Admin --> View
![img.png](Images/img6.png)

User --> cancel booking with invalid ticketId
![img.png](Images/img7.png)

User --> Cancellation Window Expired
![img.png](Images/img8.png)

user --> Successfully cancel 
![img.png](Images/img10.png)




