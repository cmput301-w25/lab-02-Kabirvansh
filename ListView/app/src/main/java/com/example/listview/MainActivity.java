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
    Button addButton;    // Declare button variables
    Button removeButton;
    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the ListView
        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.city_input);
        addButton = findViewById(R.id.add_button);
        removeButton = findViewById(R.id.remove_button);
        cityInput.setVisibility(View.GONE);
        confirmButton.setVisibility(View.GONE);

        // Data for the ListView
        String[] cities = {"Edmonton", "Vancouver", "Calgary", "Toronto", "Montreal"};

        // Initialize the ArrayList
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        // Initialize the ArrayAdapter
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);

        // Set the adapter for the ListView
        cityList.setAdapter(cityAdapter);
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
        });

        // Add button click listener
        addButton.setOnClickListener(v -> {
            cityInput.setVisibility(View.VISIBLE);
            confirmButton.setVisibility(View.VISIBLE);
            cityInput.requestFocus(); // Focus on the input field
        });

        confirmButton.setOnClickListener(v -> {
            String newCity = cityInput.getText().toString().trim();
            if (!newCity.isEmpty()) {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();
                cityInput.setText(""); // Clear the input

                // Hide input fields
                cityInput.setVisibility(View.GONE);
                confirmButton.setVisibility(View.GONE);
            }
        });

        // Remove button click listener
        removeButton.setOnClickListener(v -> {
            if (selectedPosition != -1) {
                dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                selectedPosition = -1;
            }
        });
    }
}
