package com.karpuk.booking.service;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

public class CurrencyExchangeClient {

    private static final String URL = "https://free.currencyconverterapi.com/api/v6/convert";
    private static final String COMPACT_PARAMETR = "compact";
    private static final String COMPACT_VALUE = "ultra";
    private static final String Q_PARAMETR = "q";

    private OkHttpClient client = new OkHttpClient();

    public double getRate(String srcCurrency, String destCurrency) {
        String currencyExchange = srcCurrency + "_" + destCurrency;
        String url = HttpUrl.parse(URL).newBuilder()
                .addQueryParameter(COMPACT_PARAMETR, COMPACT_VALUE)
                .addQueryParameter(Q_PARAMETR, currencyExchange)
                .build()
                .toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String jsonStringBody = response.body().string();
            JSONObject obj = new JSONObject(jsonStringBody);
            return obj.getDouble(currencyExchange);

        } catch (IOException e) {
            throw new RuntimeException("Can not get rate", e);
        }
    }

}
