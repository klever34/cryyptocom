package com.app.crypto.cryptoalc.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.app.crypto.cryptoalc.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 31/Oct/2017.
 */

public class CreateCardActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String item, item2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_card);
        final Activity activity = this;
        activity.setTitle("Create Card");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        List<String> countries = new ArrayList<String>();


        countries.add("NGN");
        countries.add("JPY");
        countries.add("USD");
        countries.add("EUR");
        countries.add("GBP");
        countries.add("ZAR");
        countries.add("CNY");
        countries.add("INR");
        countries.add("SGD");
        countries.add("TWD");
        countries.add("AUD");
        countries.add("RUB");
        countries.add("MXN");
        countries.add("ILS");
        countries.add("MYR");
        countries.add("NZD");
        countries.add("SEK");
        countries.add("CHF");
        countries.add("NOK");
        countries.add("BRL");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);
        List<String> curType = new ArrayList<String>();
        curType.add("BTC");
        curType.add("ETH");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, curType);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);


        Button mBut = (Button) findViewById(R.id.submitButton);
        mBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateCardActivity.this, MainActivity.class);
                intent.putExtra("BtcTxt", item);
                intent.putExtra("curType", item2);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;
        if(spinner.getId() == R.id.spinner){
            item = adapterView.getItemAtPosition(i).toString();
        }
        else if (spinner.getId() == R.id.spinner2){
            item2 = adapterView.getItemAtPosition(i).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
