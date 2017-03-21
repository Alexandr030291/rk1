package com.example.alexandr.rk1;


import android.util.Log;
import com.google.gson.Gson;

class Serializer {
    private static final Gson gson = new Gson();

    private Serializer() {
    }

    static BitCointy fromJson(String json){
        return gson.fromJson(json,BitCointy.class);
    }

    static BitCointy fromAnswer(String answer) {
        Log.d("!@!", "d " + answer);
        return gson.fromJson(answer, BitCointy.class);
    }

    static String toJson(BitCointy bitCointy) {
        return gson.toJson(bitCointy);
    }
}
