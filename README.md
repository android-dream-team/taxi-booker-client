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
