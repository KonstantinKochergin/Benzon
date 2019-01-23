package com.company.benzon.benzon.fragments;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.company.benzon.benzon.PaymentActivity;
import com.company.benzon.benzon.R;
import com.company.benzon.benzon.views.FuelTypeView;

import java.util.ArrayList;

public class FuelColumnFragment extends Fragment {

    ArrayList<FuelTypeView> typeViews = new ArrayList<>();
    PaymentActivity paymentActivity;

    FuelTypeView currentActiveTypeView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paymentActivity = (PaymentActivity)getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fuel_column_fragment, container, false);
        typeViews.add((FuelTypeView) view.findViewById(R.id.fuel1));
        typeViews.add((FuelTypeView) view.findViewById(R.id.fuel2));
        typeViews.add((FuelTypeView) view.findViewById(R.id.fuel3));
        typeViews.add((FuelTypeView) view.findViewById(R.id.fuel4));

        typeViews.get(0).setFuelType("АИ-98");
        typeViews.get(0).setFuelPrice(40.15f);
        typeViews.get(1).setFuelType("АИ-95");
        typeViews.get(1).setFuelPrice(38.25f);
        typeViews.get(2).setFuelType("АИ-92");
        typeViews.get(2).setFuelPrice(35.82f);
        typeViews.get(3).setFuelType("ДТ");
        typeViews.get(3).setFuelPrice(37.61f);

        for (FuelTypeView typeView : typeViews) {
            typeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FuelTypeView fuelTypeView = (FuelTypeView)v;
                    if(currentActiveTypeView != null){
                        currentActiveTypeView.setBackgroundColor(ContextCompat.getColor(paymentActivity.getApplicationContext(), R.color.colorPrimary));
                    }
                    if(paymentActivity != null){
                        paymentActivity.setCurrentFuelStats(fuelTypeView.getFuelPrice(), fuelTypeView.getFuelType());
                        FuelTypeView prev = currentActiveTypeView;
                        currentActiveTypeView = fuelTypeView;
                        currentActiveTypeView.setBackgroundColor(ContextCompat.getColor(paymentActivity.getApplicationContext(), R.color.redPrimary));
                        if (prev != null){
                            paymentActivity.refreshVolume();
                        }
                    }
                }
            });
        }
        return view;

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void onFuelTypeClick(View view){

    }
}
