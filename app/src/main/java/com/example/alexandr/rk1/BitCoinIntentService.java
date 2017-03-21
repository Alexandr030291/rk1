package com.example.alexandr.rk1;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.ResultReceiver;


public class BitCoinIntentService extends IntentService {
    public final  static String ACTION_BITCOINTY = "action.BITCOINTY";

    public static final String EXTRA_BITCOINTY_RESULT_RECEIVER = "extra.BITCOINTY_RESULT_RECEIVER";

    public final static int RESULT_SUCCESS = 0;
    public final static int RESULT_ERROR = 1;

    public BitCoinIntentService() {
        super("BitCoinIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final Bundle data = new Bundle();
            final String action = intent.getAction();
            if (ACTION_BITCOINTY.equals(action)) {
                final ResultReceiver receiver = intent.getParcelableExtra(EXTRA_BITCOINTY_RESULT_RECEIVER);
                try {
                    Storage storage = Storage.getInstance(getApplicationContext());
                    final double value =storage.getLastSavedValue();
                    String currency = storage.loadCurrentCurrency();
                    BitCointy bitCointy = (new BitCointyLoader()).loadBitCointy(currency,value);
                    storage.saveBitCointy(bitCointy);
                    receiver.send(RESULT_SUCCESS, data);
                }
                catch (Exception e) {
                    receiver.send(RESULT_ERROR, data);
                }
            }
        }
    }
}
