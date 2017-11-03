package com.app.crypto.cryptoalc.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.app.crypto.cryptoalc.R;
import com.app.crypto.cryptoalc.adapter.BTCAdapter;
import com.app.crypto.cryptoalc.model.BTCData;
import com.app.crypto.cryptoalc.model.ListItem;

import java.util.ArrayList;

/**
 * Created by USER on 3/Nov/2017.
 */

public class CountriesAndCodes extends AppCompatActivity implements BTCAdapter.ItemClickCallback{
    private RecyclerView recView;
    private BTCAdapter adapter;
    private ArrayList listData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.codes);
        listData = (ArrayList) BTCData.getListData();
        recView = (RecyclerView) findViewById(R.id.air_rec_list);
        recView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BTCAdapter(BTCData.getListData(),CountriesAndCodes.this);
        recView.setAdapter(adapter);
        adapter.setItemClickCallback(CountriesAndCodes.this);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int p) {
        ListItem item = (ListItem) listData.get(p);
    }

    @Override
    public void onSecondaryIconClick(int p) {

    }
}
