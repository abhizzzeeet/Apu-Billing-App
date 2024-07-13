package com.example.billingapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvShopName, tvShopAddress, tvOwnerName, tvOwnerAddress;
    private Button btnEditMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize views
        tvShopName = findViewById(R.id.tvShopName);
        tvShopAddress = findViewById(R.id.tvShopAddress);
        tvOwnerName = findViewById(R.id.tvOwnerName);
        tvOwnerAddress = findViewById(R.id.tvOwnerAddress);
        btnEditMenu = findViewById(R.id.btnEditMenu);

        // Retrieve data from SharedPreferences (assuming these were saved during SetupActivity)
        SharedPreferences prefs = getSharedPreferences("bills_prefs", MODE_PRIVATE);
        String shopName = prefs.getString("shopName", "");
        String shopAddress = prefs.getString("shopAddress", "");
        String ownerName = prefs.getString("ownerName", "");
        String ownerAddress = prefs.getString("ownerAddress", "");

        // Display shop and owner details
        tvShopName.setText("Shop Name: " + shopName);
        tvShopAddress.setText("Shop Address: " + shopAddress);
        tvOwnerName.setText("Owner Name: " + ownerName);
        tvOwnerAddress.setText("Owner Address: " + ownerAddress);

        // Edit Menu button click listener
        btnEditMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, EditMenuActivity.class));
            }
        });
    }
}
