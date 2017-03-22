package com.example.alexandr.rk1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements ServiceHelper.BitCointyResultListener {
    private int mRequestId;
    private TextView textTextResult;
    private EditText textTextValue;

    private final View.OnClickListener onSettings = (view) ->
            startActivity(new Intent(MainActivity.this,Settings.class));

    private final View.OnClickListener onUpdate = (view) -> {
        double value = 0;
        if (!textTextValue.getText().toString().isEmpty()) {
            value = Double.valueOf(textTextValue.getText().toString());
        }
        Storage.getInstance(getApplicationContext()).saveValue(value);
        newRequestID();
    };

    private void newRequestID(){
        mRequestId = (mRequestId == 0)?
                ServiceHelper.getInstance().makeLikeBitCointy(getApplicationContext(), this):mRequestId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTextResult = (TextView) findViewById(R.id.result);
        textTextValue = (EditText) findViewById(R.id.enter_value);

        findViewById(R.id.settings).setOnClickListener(onSettings);
        findViewById(R.id.update).setOnClickListener(onUpdate);

        //default option for first start
        if (Storage.getInstance(this).loadCurrentCurrency().isEmpty()) {
            Storage.getInstance(this).saveCurrentCurrency(Currency.USD);
        }
        if (Storage.getInstance(this).getLastSavedValue()==null) {
            Storage.getInstance(this).saveValue(0.0);
        }
    }

    @Override
    protected void onStop(){
        ServiceHelper.getInstance().removeListener(mRequestId);
        mRequestId =0;
        super.onStop();
    }

    @Override
    protected void onStart(){
        super.onStart();
        newRequestID();
        onBitCointyResult(true);
    }

    @Override
    public void onBitCointyResult(boolean success) {
        mRequestId = 0;
        BitCointy bitCointy = Storage.getInstance(getApplicationContext()).getLastSavedBitCointy();
        if (bitCointy!=null) {
            final String currency = bitCointy.getCurrency();
            final String result = String.valueOf(bitCointy.getValue());
            final String value = String.valueOf(Storage.getInstance(getApplicationContext()).getLastSavedValue());
            textTextResult.setText(value+" "+currency+" = "+result+" Bincointy");
        }else {
            textTextResult.setText("ERROR");
        }
    }
}
