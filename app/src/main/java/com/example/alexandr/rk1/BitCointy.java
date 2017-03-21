package com.example.alexandr.rk1;


public class BitCointy {
    private String currency;
    private Number value;

    public BitCointy(String currency, Double value) {
        this.currency = currency;
        this.value = value;
    }

    public String getCurrency() {
        return this.currency;
    }

    public Number getValue() {
        return this.value;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}


