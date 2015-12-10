package com.github.taxibooker;

import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) {
        // Example how to use TaxiBookerHttpClient

        // we should use UTC timezone here for convenience with Backend
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        // add initial order
        String phoneNumber = "8 050 856 13 77";
        TaxiBookerHttpClient client = new TaxiBookerHttpClient(phoneNumber);
        String addressFrom = "проспект Перемоги 45, Київ";
        String addressTo = "провулок Ковальський 5, Київ";
        String bookingTime = new Date().toString();
        System.out.println(bookingTime);
        String orderId = client.addOrder(addressFrom, addressTo, bookingTime);

        // get information about the order
        HashMap order = client.getOrder(orderId);
        System.out.println("Fetched order: \n" + order);

    }

}
