package com.app.crypto.cryptoalc.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.crypto.cryptoalc.R;

/**
 * Created by USER on 26/Oct/2017.
 */

public class ConversionActivity extends AppCompatActivity {
    String getPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion_activity);
        Intent intent = getIntent();
        getPrice = intent.getStringExtra("price");
        if (!isNetworkAvailable()){
            if(getPrice == "Price"){
                getPrice = "?";
            }
        }
        final Activity activity = this;
        activity.setTitle("Make Conversions");
        TextView priceView = (TextView) findViewById(R.id.editText2);
        priceView.setText("Price "+getPrice);

        Button mButton = (Button) findViewById(R.id.convert);
        mButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                EditText eText = (EditText) findViewById(R.id.editText4);
                String amtText = eText.getText().toString().trim();
                double dAmt = Double.parseDouble(amtText);
                double pAmt = Double.parseDouble(getPrice);

                double total = dAmt * pAmt;
                TextView textV = (TextView) findViewById(R.id.textView2);
                String newTotal = String.valueOf(total);
                textV.setText(newTotal);
            }
        });
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(networkInfo != null && networkInfo.isConnected()){
            isAvailable = true;
        }
        return isAvailable;
    }

}
