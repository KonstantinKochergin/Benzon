package com.company.benzon.benzon.views;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

import com.company.benzon.benzon.R;

public class GasStationPopupView extends ConstraintLayout {

    private Context context;

    private TextView addressTextView;
    private TextView phoneTextView;
    private Button fillUpButton;
    private Button preOrderButton;

    public GasStationPopupView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public GasStationPopupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public GasStationPopupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.gas_station_popup, this);
        addressTextView = findViewById(R.id.address);
        phoneTextView = findViewById(R.id.phone_number);
        fillUpButton = findViewById(R.id.fill_up_button);
        preOrderButton = findViewById(R.id.pre_order_button);
    }

    public Button getFillUpButton(){
        return fillUpButton;
    }
}
