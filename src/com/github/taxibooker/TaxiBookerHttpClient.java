package com.github.taxibooker;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.client.util.Key;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockHttpUnsuccessfulResponseHandler;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.testing.util.LogRecordingHandler;
import com.google.api.client.testing.util.MockBackOff;
import com.google.api.client.testing.util.MockSleeper;
import com.google.api.client.util.BackOff;
import com.google.api.client.util.Key;
import com.google.api.client.util.LoggingStreamingContent;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.Value;
//import com.google.common.base.Charsets;
//import com.google.common.collect.ImmutableList;
//import com.google.common.collect.ImmutableSet;
//import com.google.common.collect.Lists;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;


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
                    "http://script.google.com/macros/s/AKfycbyR8RnL1gN1cQpHY1OQPRzfoKJIfpH0s6JUmEyPF0lWfdhraXMu/exec?content={'orderId': 'dsadasdasdasdasdasdasdasdasdasdasdasdasdasd', 'phoneNumber': '+380508561377', 'addressFrom': 'вулиця Горького 3', 'addressTo': 'залізничний вокзал', 'bookingTime': '18:30 24 Грудня 2014'}");
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
//        HttpRequest request = requestFactory.buildGetRequest(url);
        Map<String, String> json = new HashMap<String, String>();
        json.put("key", "value");
        final HttpContent content = new JsonHttpContent(new JacksonFactory(), json);
//        String contentValue = "{'name': 'hello'}";
//        byte[] bytes = StringUtils.getBytesUtf8(contentValue);
//        InputStreamContent content = new InputStreamContent(
//                new HttpMediaType("application/json").build(),
//                new ByteArrayInputStream(bytes));

        System.out.println(url);
        System.out.println(content.getLength());
//        HttpRequest request = requestFactory.buildRequest("POST", url, content);
//        HttpRequest request = requestFactory.buildPostRequest(url, content);
        HttpRequest request = requestFactory.buildGetRequest(url);


        return parseResponse(request.execute());
    }

    private static GenericJson parseResponse(HttpResponse response) throws IOException {
        GenericJson result = response.parseAs(GenericJson.class);
        System.out.println("Response: " + result);
        return result;
    }


}