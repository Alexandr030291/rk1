package com.example.alexandr.rk1;


import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BitCointyLoader {
    private final static String API_KEY = "2ndV4pZBnSmsh0AmmnTmk3R9GmBgp1HhTUrjsncQwIJ2Il9tcG";
    private final static String METHOD_URL = "https://community-bitcointy.p.mashape.com/convert/";


    private final OkHttpClient httpClient = new OkHttpClient();

    public BitCointyLoader() {
    }

    public BitCointy loadBitCointy(String currency, double value) throws IOException {
        Request request = (new Request.Builder())
                .url(METHOD_URL + value +"/" + currency)
                .header("X-Mashape-Key", API_KEY)
                .header("Accept", "text/plain")
                .build();
        Response response = this.httpClient.newCall(request).execute();

        BitCointy var4;
        try {
            if(!response.isSuccessful()) {
                throw new IOException("Wrong status: " + response.code() + "; body: " + response.body().string());
            }

            var4 = Serializer.fromAnswer(response.body().string());
        } finally {
            response.body().close();
        }

        return var4;
    }
}


/*
    HttpResponse<String> response = Unirest.get("https://community-bitcointy.p.mashape.com/convert/10/USD")
            .header("X-Mashape-Key", "pzmdYhUfvPmshD06A1f30wPoZUc8p1RgBrdjsnSR5LLsxvnF0C")
            .header("Accept", "text/plain")
            .asString();
 */