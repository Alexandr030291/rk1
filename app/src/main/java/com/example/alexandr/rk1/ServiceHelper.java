package com.example.alexandr.rk1;


import android.content.Context;

import java.util.Hashtable;
import java.util.Map;

import android.content.Intent;
import android.os.Handler;

class ServiceHelper {
    private  int mIdCounter = 1;
    private static ServiceHelper instance;
    private final Map<Integer, BitCointyResultReceiver> mResultReceivers = new Hashtable<>();

    private ServiceHelper(){}

    synchronized static ServiceHelper getInstance() {
        if (instance == null) {
            instance = new ServiceHelper();
        }
        return instance;
    }

    int makeLikeBitCointy(final Context context, final BitCointyResultListener listener) {
        final BitCointyResultReceiver receiver = new BitCointyResultReceiver(mIdCounter, new Handler());
        receiver.setListener(listener);
        mResultReceivers.put(mIdCounter, receiver);

        Intent intent = new Intent(context, BitCoinIntentService.class);
        intent.setAction(BitCoinIntentService.ACTION_BITCOINTY);
        intent.putExtra(BitCoinIntentService.EXTRA_BITCOINTY_RESULT_RECEIVER, receiver);
        context.startService(intent);

        return mIdCounter++;
    }

    void removeListener(final int id) {
        BitCointyResultReceiver receiver = mResultReceivers.remove(id);
        if (receiver != null) {
            receiver.setListener(null);
        }
    }

    interface BitCointyResultListener {
        void onBitCointyResult(final boolean success);
    }
}
