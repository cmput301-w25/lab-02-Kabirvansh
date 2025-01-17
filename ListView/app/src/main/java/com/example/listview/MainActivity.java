package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText cityInput;
    int selectedPosition = -1;
    Button addButton;
    Button removeButton;
    Button confirmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.city_input);
        addButton = findViewById(R.id.add_button);
        removeButton = findViewById(R.id.remove_button);
        confirmButton = findViewById(R.id.confirm_button);


        cityInput.setVisibility(View.GONE);
        confirmButton.setVisibility(View.GONE);


        String[] cities = {"Edmonton", "Vancouver", "Calgary", "Toronto", "Montreal"};


        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));


        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);


        cityList.setAdapter(cityAdapter);
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            cityAdapter.notifyDataSetChanged();
        });

        addButton.setOnClickListener(v -> {
            if (cityInput.getVisibility() == View.VISIBLE) {
                cityInput.setVisibility(View.GONE);
                confirmButton.setVisibility(View.GONE);
            } else {
                cityInput.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
                cityInput.requestFocus();
            }
        });

        confirmButton.setOnClickListener(v -> {
            String newCity = cityInput.getText().toString().trim();
            if (!newCity.isEmpty()) {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();
                cityInput.setText("");
                cityInput.setVisibility(View.GONE);
                confirmButton.setVisibility(View.GONE);
            }
        });

        removeButton.setOnClickListener(v -> {
            if (selectedPosition != -1) {
                dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                selectedPosition = -1;
            }
        });
    }
}
