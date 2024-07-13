package com.example.billingapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;

public class SetupActivity extends AppCompatActivity {

    private EditText etShopName, etShopAddress, etOwnerName, etOwnerPhone, etEnterItem, etEnterPrice;
    private Button btnAddItem, btnCompleteSetup;
    private Map<String, Float> menu; // Menu dictionary

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        // Initialize views
        etShopName = findViewById(R.id.etShopName);
        etShopAddress = findViewById(R.id.etShopAddress);
        etOwnerName = findViewById(R.id.etOwnerName);
        etOwnerPhone = findViewById(R.id.etOwnerPhone);
        etEnterItem = findViewById(R.id.etEnterItem);
        etEnterPrice = findViewById(R.id.etEnterPrice);
        btnAddItem = findViewById(R.id.btnAddItem);
        btnCompleteSetup = findViewById(R.id.btnCompleteSetup);

        // Initialize menu dictionary
        menu = new HashMap<>();

        // Add item button click listener
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemToMenu();
            }
        });

        // Complete setup button click listener
        btnCompleteSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeSetup();
            }
        });
    }

    // Method to add item to menu dictionary
    private void addItemToMenu() {
        String itemName = etEnterItem.getText().toString().trim().toUpperCase();
        float itemPrice = Float.parseFloat(etEnterPrice.getText().toString().trim());

        // Add item to menu dictionary
        menu.put(itemName, itemPrice);

        // Clear input fields
        etEnterItem.setText("");
        etEnterPrice.setText("");
    }

    // Method to complete setup and proceed to AgreementActivity
    private void completeSetup() {
        // Save setup completion status
        saveSetupComplete();

        // Proceed to AgreementActivity
        startActivity(new Intent(SetupActivity.this, AgreementActivity.class));
        finish();
    }

    // Method to save setup completion status
    private void saveSetupComplete() {
        SharedPreferences.Editor editor = getSharedPreferences("bills_prefs", MODE_PRIVATE).edit();
        editor.putBoolean("isSetupComplete", true);
        editor.apply();
    }
}
