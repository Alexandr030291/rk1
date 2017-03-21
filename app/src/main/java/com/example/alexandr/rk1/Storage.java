package com.example.alexandr.rk1;


import android.content.Context;
import android.content.SharedPreferences;

public class Storage {

    private static final String KEY_Currency = "Currency";
    private static final String KEY_BitCoin = "BitCoin";
    private static final String KEY_Value = "Value";

    private static Storage INSTANCE;
    private SharedPreferences preferences;

    public static synchronized Storage getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new Storage(context.getApplicationContext());
        }

        return INSTANCE;
    }

    public Storage(Context context) {
        this.preferences = context.getSharedPreferences(KEY_BitCoin, 0);
    }


    public String loadCurrentCurrency() {
        return this.preferences.getString(KEY_Currency, "");
    }

    public void saveCurrentCurrency(String currency) {
        this.preferences.edit().putString(KEY_Currency, currency).apply();
    }

    public void saveBitCointy(BitCointy bitCointy) {
        if(bitCointy == null) {
            this.preferences.edit().remove(KEY_BitCoin).apply();
        } else {
            this.preferences.edit().putString(KEY_BitCoin, Serializer.toJson(bitCointy)).apply();
        }
    }

    public BitCointy getLastSavedBitCointy() {
        String result = this.preferences.getString(KEY_BitCoin, "");
        return result.isEmpty()?null:Serializer.fromJson(result);
    }

    public void saveValue(Double value) {
        if(value == null) {
            this.preferences.edit().remove(KEY_Value).apply();
        } else {
            this.preferences.edit().putString(KEY_Value, value.toString()).apply();
        }
    }

    public Double getLastSavedValue() {
        String result = this.preferences.getString(KEY_Value, "");
        return result.isEmpty()?null: Double.valueOf(result);
    }

}
