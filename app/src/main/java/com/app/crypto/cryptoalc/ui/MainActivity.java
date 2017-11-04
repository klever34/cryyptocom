package com.app.crypto.cryptoalc.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.crypto.cryptoalc.R;
import com.app.crypto.cryptoalc.adapter.BTCAdapter;
import com.app.crypto.cryptoalc.app.AppController;
import com.app.crypto.cryptoalc.model.BTCData;
import com.app.crypto.cryptoalc.model.ListItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BTCAdapter.ItemClickCallback {
    private RecyclerView recView;
    private BTCAdapter adapter;
    private ArrayList listData;
    public String urlObj = "https://min-api.cryptocompare.com/data/price?fsym=";
    public String urlObj2 = "&tsyms=";
    public String JSON;
    String btc;
    String cty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        btc = i.getStringExtra("BtcTxt");
        cty = i.getStringExtra("curType");
        if (isNetworkAvailable()){
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,urlObj+cty+urlObj2+btc,null,
                    new Response.Listener<JSONObject>(){

                        @Override
                        public void onResponse(JSONObject response) {
                            try{
                                JSON = response.getString(btc);
                                listData = (ArrayList) BTCData.addNewItem(cty+" to "+btc,JSON);
                                recView = (RecyclerView) findViewById(R.id.air_rec_list);
                                recView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                adapter = new BTCAdapter(BTCData.addNewItem(cty+" to "+btc,JSON),MainActivity.this);
                                recView.setAdapter(adapter);
                                adapter.setItemClickCallback(MainActivity.this);
                                adapter.notifyDataSetChanged();

                            }
                            catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
            AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        }
        else {
            listData = (ArrayList) BTCData.addNewItem("BTC/ETH","Price");
            recView = (RecyclerView) findViewById(R.id.air_rec_list);
            recView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            adapter = new BTCAdapter(BTCData.addNewItem("BTC/ETH","Price"),MainActivity.this);
            recView.setAdapter(adapter);
            adapter.setItemClickCallback(MainActivity.this);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }


        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateCardActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onItemClick(int p) {
        ListItem item = (ListItem) listData.get(p);

        Intent i = new Intent(this, ConversionActivity.class);
        i.putExtra("price", item.getSubTitle());
        startActivity(i);
    }

    @Override
    public void onSecondaryIconClick(int p) {

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

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.code_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.code:
                startActivity(new Intent(this, CountriesAndCodes.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
