# CZ2002 Object Oriented Design and Programming - Project

## Topic
Restaurant Reservation Point of Sale System <br/>
Console line version

## Tool
java

## Directory
doc - documentation <br/>
possystem - code

## Description
### Some Assumptions
Restaurant operation time: Lunch: 11am - 4pm Dinner: 6pm - 11pm<br/>

 * Reservations can only be made ahead of time.<br/>
 * For lunch reservation (11am - 4pm), it can only be made the day before. (Cut-off time: the previous day) <br/>
 * For dinner reservation (6pm - 9pm), it can only be made before 4pm on the day itself. (Cut-off time: before 4pm of the actual date of reservation)<br/>
 * Reservation will be automatically cancelled after 15 mins.<br/>
 * Reservation will be closed after 50% of the tables are reserved based on lunch or dinner session.<br/>
 * So that there will still be tables for walk in guests.<br/>

### Staff List
In this assignment, we assume at the staff list is fixed.<br/>
Please use the IDs below during the Restaurant Reservation Point of Sale System.<br/>

 ID		Name			Gender		Position<br/>
 --------------------------------------------<br/>
 1		&emsp; Andy			male		Manager<br/>
 2		Bryant			male		Cashier<br/>
 3		Cindy			female		Waitress<br/>
 4		Dan				male		Waiter<br/>
 5		Eddie			male		Chef<br/>
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++<br/>

### Table List
In this assignment, we assume at the table list is fixed.<br/>

 TableNo		Seats<br/>
 --------------------------------------------<br/>
 21				2<br/>
 22				2<br/>
 23				2<br/>
 24				2<br/>
 25				2<br/>
 41				4<br/>
 42				4<br/>
 43				4<br/>
 44				4<br/>
 45				4<br/>
 46				4<br/>
 47				4<br/>
 61				6<br/>
 62				6<br/>
 63				6<br/>
 64				6<br/>
 65				6<br/>
 66				6<br/>
 101			10<br/>
 102			10<br/>
 103			10<br/>
 104			10<br/>
 105			10<br/>

### Order List
In this assignment, we assume that the staff have to responsibility to
close all active order at the end of the lunch or dinner shifts.<br/>

Any unclosed orders causing malfunction of the system will not be liable.<br/>
