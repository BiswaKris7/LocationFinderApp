package com.example.locationfinder;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText editTextAddress, editTextLatitude, editTextLongitude;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        editTextAddress = findViewById(R.id.editTextAddress);
        editTextLatitude = findViewById(R.id.editTextLatitude);
        editTextLongitude = findViewById(R.id.editTextLongitude);
        textViewResult = findViewById(R.id.textViewResult);

        Button buttonQuery = findViewById(R.id.buttonQuery);
        buttonQuery.setOnClickListener(view -> queryLocation());
        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(view -> addLocation());
        Button buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(view -> updateLocation());
        Button buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(view -> deleteLocation());
    }

    private void queryLocation() {

        String address = editTextAddress.getText().toString().trim();

        if (address.isEmpty()) {
            Toast.makeText(this, "Please enter an address to query.", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d("MainActivity", "Querying location for address: " + address);
        Cursor cur = databaseHelper.queryLocation(address);

        if (cur != null && cur.moveToFirst()) {

            double latitude = cur.getDouble(cur.getColumnIndex("latitude"));
            double longitude = cur.getDouble(cur.getColumnIndex("longitude"));
            textViewResult.setText("Latitude: " + latitude + ", Longitude: " + longitude);
            Log.d("MainActivity", "Location found: Latitude = " + latitude + ", Longitude = " + longitude);
        } else {

            textViewResult.setText("Location not found.");
            Log.d("MainActivity", "Location not found for address: " + address);
        }

        if (cur != null) {
            cur.close();
        }
    }

    private void addLocation() {

        String address = editTextAddress.getText().toString().trim();
        if (address.isEmpty()) {
            Toast.makeText(this, "Please enter an address to add.", Toast.LENGTH_SHORT).show();
            return;
        }
        double latitude = getDoubleFromEditText(editTextLatitude);
        double longitude = getDoubleFromEditText(editTextLongitude);

        boolean result = databaseHelper.insertLocation(address, latitude, longitude);
        Toast.makeText(this, result ? "Location added successfully" : "Failed to add location", Toast.LENGTH_SHORT).show();
    }

    private void updateLocation() {
        String address = editTextAddress.getText().toString().trim();
        if (address.isEmpty()) {
            Toast.makeText(this, "Please enter an address to update.", Toast.LENGTH_SHORT).show();
            return;
        }

        double latitude = getDoubleFromEditText(editTextLatitude);
        double longitude = getDoubleFromEditText(editTextLongitude);

        boolean result = databaseHelper.updateLocation(address, latitude, longitude);
        Toast.makeText(this, result ? "Location updated successfully" : "Failed to update location", Toast.LENGTH_SHORT).show();
    }

    private void deleteLocation() {
        String address = editTextAddress.getText().toString().trim();
        if (address.isEmpty()) {
            Toast.makeText(this, "Please enter an address to delete.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean result = databaseHelper.deleteLocation(address);
        Toast.makeText(this, result ? "Location deleted successfully" : "Failed to delete location", Toast.LENGTH_SHORT).show();
    }

    private double getDoubleFromEditText(EditText editText) {
        try {
            return Double.parseDouble(editText.getText().toString().trim());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
            return 0.0;
        }
    }
}
