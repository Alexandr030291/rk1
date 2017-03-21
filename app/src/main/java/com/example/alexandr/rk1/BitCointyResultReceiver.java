package com.example.alexandr.rk1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

@SuppressLint("ParcelCreator")
class BitCointyResultReceiver extends ResultReceiver {
    private final int requestId;
    private  ServiceHelper.BitCointyResultListener mListener;

    public  BitCointyResultReceiver(int requestId, final Handler handler){
        super(handler);
        this.requestId =requestId;
    }

    void setListener(final ServiceHelper.BitCointyResultListener listener){ mListener = listener;}

    @Override
    protected void onReceiveResult(final int resultCode, final Bundle resultData){
        if (mListener != null) {
            final boolean success = (resultCode == BitCoinIntentService.RESULT_SUCCESS);
            mListener.onBitCointyResult(success);
        }
        ServiceHelper.getInstance().removeListener(requestId);
    }

}

