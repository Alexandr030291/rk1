package com.example.alexandr.rk1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

public class Settings extends AppCompatActivity {
    private RadioButton radioButton1;
    private RadioButton radioButton2;

    private final View.OnClickListener onSelect1 = (view) -> {
        Storage.getInstance(getApplicationContext()).saveCurrentCurrency(Currency.RUB);
        draw();
    };

    private final View.OnClickListener onSelect2 = (view) -> {
        Storage.getInstance(getApplicationContext()).saveCurrentCurrency(Currency.USD);
        draw();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        radioButton1 = (RadioButton) findViewById(R.id.cancel_action_1);
        radioButton2 = (RadioButton) findViewById(R.id.cancel_action_2);

        radioButton1.setOnClickListener(onSelect1);
        radioButton2.setOnClickListener(onSelect2);

        radioButton1.setText(Currency.RUB);
        radioButton2.setText(Currency.USD);
        draw();
    }


    private void draw(){
        radioButton1.setChecked(false);
        radioButton2.setChecked(false);
        final String category = Storage.getInstance(getApplicationContext()).loadCurrentCurrency();
        if (category.equals(Currency.RUB))
            radioButton1.setChecked(true);
        else if (category.equals(Currency.USD))
            radioButton2.setChecked(true);
    }
}
