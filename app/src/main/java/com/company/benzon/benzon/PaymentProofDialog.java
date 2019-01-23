package com.company.benzon.benzon;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class PaymentProofDialog extends Dialog implements View.OnClickListener {

    private PaymentActivity paymentActivity;

    private TextView fuelTypeTextView, volumeTextView, priceTextView;
    private Button negativeButton, positiveButton;

    private String fuelType, transDate, transTime;
    private float fuelVolume, fuelPrice, fuelCost;

    public PaymentProofDialog(Context context, Activity activity, String type, float volume, float price, float cost) {
        super(context);
        paymentActivity = (PaymentActivity) activity;
        fuelType = type;
        fuelVolume = volume;
        fuelPrice = price;
        fuelCost = cost;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_proof_dialog_layout);

        fuelTypeTextView = findViewById(R.id.type);
        volumeTextView = findViewById(R.id.volume);
        priceTextView = findViewById(R.id.price);
        negativeButton = findViewById(R.id.cancel_button);
        positiveButton = findViewById(R.id.ok_button);

        fuelTypeTextView.setText(fuelType);
        String tmpVolume = "" + String.format("%.2f", fuelVolume) + " Л";
        volumeTextView.setText(tmpVolume);
        String tmpPrice = "" + fuelPrice + " Р";
        priceTextView.setText(tmpPrice);

        negativeButton.setOnClickListener(this);
        positiveButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok_button:
                transDate = getTransDate();
                transTime = getTransTime();
                Intent intent = new Intent(getContext(), PaymentCheckActivity.class);
                intent.putExtra("cost", fuelCost);
                intent.putExtra("volume", fuelVolume);
                intent.putExtra("price", fuelPrice);
                intent.putExtra("fuel_type", fuelType);
                intent.putExtra("date", transDate);
                intent.putExtra("time", transTime);
                getContext().startActivity(intent);
                break;
            case R.id.cancel_button:
                dismiss();
                break;
        }
    }

    private String getTransDate(){
        String result;
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
        result = formatForDateNow.format(date);
        return result;
    }

    private String getTransTime(){
        String result;
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm:ss", Locale.US);
        formatForDateNow.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        result = formatForDateNow.format(date);
        return result;
    }
}
