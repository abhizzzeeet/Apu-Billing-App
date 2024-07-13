package com.example.billingapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private LinearLayout layoutHistoryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Initialize views
        layoutHistoryItems = findViewById(R.id.layoutHistoryItems);

        // Retrieve purchase list from SharedPreferences (simulated data)
        List<String[]> purchaseList = retrievePurchaseListFromSharedPreferences();

        // Display purchase history
        displayPurchaseHistory(purchaseList);
    }

    private List<String[]> retrievePurchaseListFromSharedPreferences() {
        // Simulated method to retrieve purchase list from SharedPreferences
        // Replace with your actual implementation to retrieve data
        List<String[]> purchaseList = new ArrayList<>();
        purchaseList.add(new String[]{"2024-07-12", "10:30 AM", "John Doe", "1234567890", "[Item 1, 2, $20.00], [Item 2, 1, $5.00]", "$25.00"});
        purchaseList.add(new String[]{"2024-07-11", "09:45 AM", "Jane Smith", "9876543210", "[Item 3, 1, $15.00], [Item 4, 3, $30.00]", "$45.00"});
        return purchaseList;
    }

    private void displayPurchaseHistory(List<String[]> purchaseList) {
        // Clear existing views
        layoutHistoryItems.removeAllViews();

        // Iterate through purchase list and create TextViews dynamically
        for (String[] purchase : purchaseList) {
            String date = purchase[0];
            String time = purchase[1];
            String customerName = purchase[2];
            String customerPhone = purchase[3];
            String items = purchase[4];
            String totalCost = purchase[5];

            // Create a new TextView for each purchase entry
            TextView tvPurchaseEntry = new TextView(this);
            tvPurchaseEntry.setText(String.format("Date: %s\nTime: %s\nCustomer: %s (%s)\nItems: %s\nTotal Cost: %s\n",
                    date, time, customerName, customerPhone, items, totalCost));
            tvPurchaseEntry.setTextSize(16);
            tvPurchaseEntry.setPadding(0, 0, 0, 16); // Add some bottom padding for separation

            // Add TextView to the layout
            layoutHistoryItems.addView(tvPurchaseEntry);
        }
    }
}
