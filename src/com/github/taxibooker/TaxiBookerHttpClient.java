package com.github.taxibooker;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Key;

import java.io.IOException;
import java.util.List;


enum OrderState {NOT_FOUND, IN_PROGRESS, BOOKED, COMPLETED}

public class TaxiBookerHttpClient {
    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static final JsonFactory JSON_FACTORY = new JacksonFactory();

    public int number;

    public static class BackendUrl extends GenericUrl {

        public BackendUrl(String encodedUrl) {
            super(encodedUrl);
        }

        public static BackendUrl fetch(String query) {
            return new BackendUrl(
                    "https://www.hipchat.com/api/features");
        }
    }

    public TaxiBookerHttpClient() {
        number = 25;
    }

    public String addOrder(Object order /* Order order */) {
        number += 4;
        String result;
        try {
            result = sendRequest().toPrettyString();
        } catch (IOException e)
        {
            System.out.println("Exception: " + e);
            result = "Something went wrong";

        }
        return result;
    }

    public OrderState getOrderState(String orderId) {

        return OrderState.NOT_FOUND;
    }

    private GenericJson sendRequest() throws IOException {
        HttpRequestFactory requestFactory =
                HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) {
                        request.setParser(new JsonObjectParser(JSON_FACTORY));
                    }
                });
        BackendUrl url = BackendUrl.fetch("How to code in Java");
        HttpRequest request = requestFactory.buildGetRequest(url);
        return parseResponse(request.execute());
    }

    private static GenericJson parseResponse(HttpResponse response) throws IOException {
        GenericJson result = response.parseAs(GenericJson.class);
        System.out.println("Response: " + result);
        return result;
    }


}