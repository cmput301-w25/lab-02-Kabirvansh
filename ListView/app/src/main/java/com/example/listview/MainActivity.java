package com.example.listview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the ListView
        cityList = findViewById(R.id.city_list);

        // Data for the ListView
        String[] cities = {"Edmonton", "Vancouver", "Calgary", "Toronto", "Montreal"};

        // Initialize the ArrayList
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        // Initialize the ArrayAdapter
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);

        // Set the adapter for the ListView
        cityList.setAdapter(cityAdapter);
    }
}
