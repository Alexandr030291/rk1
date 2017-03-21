package com.example.alexandr.rk1;

public class Currency {
    public final static String RUB = "RUB";
    public final static String USD = "USD";

    public static String[] ALL_Currency;

    private Currency() {
        throw new RuntimeException();
    }

    static {
        ALL_Currency = new String[]{RUB,USD};
    }

}
