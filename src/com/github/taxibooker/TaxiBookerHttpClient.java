package com.github.taxibooker;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;


public class TaxiBookerHttpClient {
    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private String phoneNumber;

    public TaxiBookerHttpClient(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static class BackendUrl extends GenericUrl {
        private static String environment = "production";
        private static String server_url = "http://script.google.com/macros/s/AKfycbyR8RnL1gN1cQpHY1OQPRzfoKJIfpH0s6JUmEyPF0lWfdhraXMu/exec";

        public BackendUrl(String encodedUrl) {
            super(encodedUrl);
        }

        public static BackendUrl post(String orderId, String phoneNumber, String addressFrom,
                                      String addressTo, String bookingTime) {
            Map<String, String> data = new HashMap<>();
            data.put("env", environment);
            data.put("orderId", orderId);
            data.put("phoneNumber", phoneNumber);
            data.put("addressFrom", addressFrom);
            data.put("addressTo", addressTo);
            data.put("bookingTime", bookingTime);
            Gson gson = new Gson();
            String json = gson.toJson(data);
            return new BackendUrl(server_url + "?addOrder=" + json);
        }

        public static BackendUrl get(String orderId) {
            Map<String, String> data = new HashMap<>();
            data.put("env", environment);
            data.put("orderId", orderId);
            Gson gson = new Gson();
            String json = gson.toJson(data);
            return new BackendUrl(server_url + "?getOrder=" + json);
        }

    }

    public String addOrder(String addressFrom, String addressTo, String bookingTime) {
        String orderId = UUID.randomUUID().toString();
        String result;
        try {
            result = sendPostRequest(orderId, addressFrom, addressTo, bookingTime).toPrettyString();
            System.out.println(result);
            return orderId;
        } catch (IOException e) {
            System.out.println("Something went wrong. Exception: " + e);
            return null;
        }
    }

    public HashMap getOrder(String orderId) {
        try {
            String json = sendGetRequest(orderId).toPrettyString();
            System.out.println(json);
            Gson gson = new Gson();
            HashMap<String, String> map = new HashMap<String, String>();
            map = (HashMap<String, String>) gson.fromJson(json, map.getClass());
            return map;
        } catch (IOException e) {
            System.out.println("Something went wrong. Exception: " + e);
            return null;
        }
    }

    private GenericJson sendPostRequest(String orderId, String addressFrom,
                                        String addressTo, String bookingTime) throws IOException {
        HttpRequestFactory requestFactory =
                HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) {
                        request.setParser(new JsonObjectParser(JSON_FACTORY));
                    }
                });
        BackendUrl url = BackendUrl.post(orderId, this.phoneNumber, addressFrom, addressTo, bookingTime);
        System.out.println("URL for sendPostRequest: " + url);
        HttpRequest request = requestFactory.buildGetRequest(url);
        return parseResponse(request.execute());
    }

    private GenericJson sendGetRequest(String orderId) throws IOException {
        HttpRequestFactory requestFactory =
                HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) {
                        request.setParser(new JsonObjectParser(JSON_FACTORY));
                    }
                });
        BackendUrl url = BackendUrl.get(orderId);
        System.out.println("URL for sendGetRequest: " + url);
        HttpRequest request = requestFactory.buildGetRequest(url);
        return parseResponse(request.execute());
    }

    private static GenericJson parseResponse(HttpResponse response) throws IOException {
        GenericJson result = response.parseAs(GenericJson.class);
        System.out.println("Response: " + result);
        return result;
    }
}