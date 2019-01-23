package com.company.benzon.benzon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.company.benzon.benzon.views.GasStationPopupView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {

    ConstraintLayout mainLayout;
    ConstraintLayout.LayoutParams layoutParams;

    GasStationPopupView popupView;
    boolean popupStatus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkRegistration();  //если пользователь не авторизирован, запускает активити регистрации
        setContentView(R.layout.activity_main);
        popupStatus = false;

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mainLayout = findViewById(R.id.main_layout);
        popupView = new GasStationPopupView(this);

        layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnMapClickListener(this);

        LatLng position1 = new LatLng(52.234529, 104.299675);
        MarkerOptions marker1 = new MarkerOptions().position( position1).title("БРК");
        googleMap.addMarker(marker1);

        LatLngBounds.Builder latLngBuilder = new LatLngBounds.Builder();
        latLngBuilder.include(position1);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(position1));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15f));

    }


    @SuppressLint("ResourceType")
    @Override
    public boolean onMarkerClick(Marker marker) {
        if(marker.getTitle().equals("БРК")){
            if(!popupStatus) {
                ConstraintSet constraintSet = new ConstraintSet();
                mainLayout.addView(popupView, layoutParams);
                popupView.setId(1);
                constraintSet.clone(mainLayout);
                constraintSet.connect(1, ConstraintSet.BOTTOM, R.id.main_layout, ConstraintSet.BOTTOM, 0);
                constraintSet.applyTo(mainLayout);
                popupView.getFillUpButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goToPaymentIntentActivity = new Intent(MainActivity.this, PaymentActivity.class);
                        startActivity(goToPaymentIntentActivity);
                    }
                });
                popupStatus = true;
            }
        }

        return false;
    }


    @Override
    public void onMapClick(LatLng latLng) {
        if(popupStatus){
            mainLayout.removeView(popupView);
            popupStatus = false;
        }
    }

    private void checkRegistration(){
        SharedPreferences userSettings = getSharedPreferences(GlobalData.USER_PREFS, Context.MODE_PRIVATE);
        if(!userSettings.contains(GlobalData.NUMBER_KEY)) {
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
        }
        else{
            GlobalData.user = new Profile(userSettings.getString(GlobalData.NAME_KEY, " "), userSettings.getString(GlobalData.SURNAME_KEY, ""),
                    userSettings.getString(GlobalData.NUMBER_KEY, ""));
        }
    }
}
