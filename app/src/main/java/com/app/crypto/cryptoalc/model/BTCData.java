package com.app.crypto.cryptoalc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 26/Oct/2017.
 */

public class BTCData {

    private static final String[] titles = {
            "NGN",
            "USD",
            "EUR",
            "INR",
            "GBP",
            "JPY",
            "TWD",
            "AUD",
            "RUB",
            "MXN",
            "ILS",
            "MYR",
            "NZD",
            "SEK",
            "CHF",
            "NOK",
            "BRL",
            "SGD",
            "ZAR",
            "CNY"
    };

    private static  final String[] prices = {
            "Nigerian Naira",
            "United States Dollar",
            "Euro",
            "Indian Rupees",
            "Great Britain Pounds",
            "Japanese Yemen",
            "Taiwan Dollar",
            "Australian Dollar",
            "Russian Ruble",
            "Mexican Peso",
            "Israel New Shekel",
            "Malaysian Ringgit",
            "New Zealand Dollar",
            "Swedish Krona",
            "Swiss Franc",
            "Norwegian Krone",
            "Brazilian Real",
            "Singapore Dollar",
            "South African Rand",
            "Chinese Yuan"

    };


    public static List<ListItem> getListData(){
        List<ListItem> data = new ArrayList<>();

        for (int i = 0 ; i<titles.length && i < prices.length; i++){
            ListItem item = new ListItem();
            item.setTitle(titles[i]);
            item.setSubTitle(prices[i]);
            data.add(item);

        }
        return data;
    }
    public static List<ListItem> addNewItem(String a, String b){
        List<ListItem> data = new ArrayList<>();
         ListItem item = new ListItem();
            item.setTitle(a);
            item.setSubTitle(b);
            data.add(item);

        return data;
    }

}
