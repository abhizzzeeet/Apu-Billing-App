package com.example.billingapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class BillActivity extends AppCompatActivity {

    private TextView tvShopName, tvShopAddress, tvOwnerName, tvOwnerPhone, tvTotalCost, tvThanksMessage;
    private TableLayout tableItems;
    private List<String[]> purchaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        // Initialize views
        tvShopName = findViewById(R.id.tvShopName);
        tvShopAddress = findViewById(R.id.tvShopAddress);
        tvOwnerName = findViewById(R.id.tvOwnerName);
        tvOwnerPhone = findViewById(R.id.tvOwnerPhone);
        tvTotalCost = findViewById(R.id.tvTotalCost);
        tvThanksMessage = findViewById(R.id.tvThanksMessage);
        tableItems = findViewById(R.id.tableItems);

        // Retrieve data from SharedPreferences (assuming these were saved during SetupActivity)
        SharedPreferences prefs = getSharedPreferences("bills_prefs", MODE_PRIVATE);
        String shopName = prefs.getString("shopName", "");
        String shopAddress = prefs.getString("shopAddress", "");
        String ownerName = prefs.getString("ownerName", "");
        String ownerPhone = prefs.getString("ownerPhone", "");

        // Retrieve purchase list (assuming it was saved during Main activity)
        // Example: purchaseList = retrievePurchaseListFromSharedPreferences();
        // For demonstration, initializing purchaseList with sample data
        purchaseList = new ArrayList<>();
        purchaseList.add(new String[]{"Item 1", "2", "10.00"});
        purchaseList.add(new String[]{"Item 2", "1", "5.00"});

        // Display shop and owner details
        tvShopName.setText(shopName);
        tvShopAddress.setText(shopAddress);
        tvOwnerName.setText(ownerName);
        tvOwnerPhone.setText(ownerPhone);

        // Display items in the table
        displayItems();

        // Calculate and display total cost
        calculateAndDisplayTotalCost();

        // Display thanks message
        tvThanksMessage.setText("Have a nice day, Customer Name! Visit again.");
        // Replace "Customer Name" dynamically with actual customer's name
    }

    private void displayItems() {
        // Clear existing rows
        tableItems.removeAllViews();

        // Iterate through purchase list and add rows dynamically
        for (String[] purchase : purchaseList) {
            String itemName = purchase[0];
            String quantity = purchase[1];
            String totalCost = purchase[2];

            // Create a new row
            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            // Item Name column
            TextView tvItemName = new TextView(this);
            tvItemName.setText(itemName);
            tvItemName.setPadding(8, 8, 8, 8);
            row.addView(tvItemName);

            // Quantity column
            TextView tvQuantity = new TextView(this);
            tvQuantity.setText(quantity);
            tvQuantity.setPadding(8, 8, 8, 8);
            row.addView(tvQuantity);

            // Total Cost column
            TextView tvTotalCost = new TextView(this);
            tvTotalCost.setText(totalCost);
            tvTotalCost.setPadding(8, 8, 8, 8);
            row.addView(tvTotalCost);

            // Add row to the table
            tableItems.addView(row);
        }
    }

    private void calculateAndDisplayTotalCost() {
        double totalCost = 0.0;
        for (String[] purchase : purchaseList) {
            totalCost += Double.parseDouble(purchase[2]); // Adding up total costs
        }
        tvTotalCost.setText(String.format("Total Cost: ₹%.2f", totalCost));
    }
}
