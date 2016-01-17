?# taxi-booker-client
Android Client for Taxi Booker. Java-based app.
---

### Taxi booker application

Mobile application allows user you to send a request for a taxi.
* User selects origin and destination on Android client.
* Client sends an HTTP request to the server, which is written on the Google Apps Script and deployed.
* Server processes the HTTP request, validates record and adds it to the table of orders (Google Spreadsheet)
* After that there is an order to insert in a calendar manager (Google Calendar).
* After processing the request the mobile client receives the status of the request.
* And using a unique GUID, that corresponds the oreder generated on the client, user can perform a repeated request and obtain order status.

CONTRIBUTORS:

* Zabelina Tania
* Tsvirchkova Anna


See https://github.com/android-dream-team/taxi-booker-backend in more details.

Here are some images, demonstrating performance characteristics of the application.
![alt tag](https://github.com/android-dream-team/taxi-booker-client/blob/master/ddms1.png)
![alt tag](https://github.com/android-dream-team/taxi-booker-client/blob/master/ddms2.png)
It can be issued, that application uses rather small amount of heap memory. The feedback is received rather quickly.

Here are also some screens of the application:
1. Start screen
![alt tag](https://github.com/android-dream-team/taxi-booker-client/blob/master/screen1.png)
2. The order is added to database
![alt tag](https://github.com/android-dream-team/taxi-booker-client/blob/master/screen2.png)
3. Google callendar
![alt tag](https://github.com/android-dream-team/taxi-booker-client/blob/master/screen3.jpg)
4. Database
![alt tag](https://github.com/android-dream-team/taxi-booker-client/blob/master/screen4.jpg)
