package com.example.billingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etCustomerName, etCustomerAddress, etItem, etQuantity;
    private TextView tvTotalCost;
    private Button btnAddOrder, btnFinalizeOrder, btnHistory;
    private ImageButton btnProfile;
    private Map<String, Float> menu; // Menu dictionary
    private ArrayList<Object> purchase; // Purchase list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        etCustomerName = findViewById(R.id.etCustomerName);
        etCustomerAddress = findViewById(R.id.etCustomerAddress);
        etItem = findViewById(R.id.etItem);
        etQuantity = findViewById(R.id.etQuantity);
        tvTotalCost = findViewById(R.id.tvTotalCost);
        btnAddOrder = findViewById(R.id.btnAddOrder);
        btnFinalizeOrder = findViewById(R.id.btnFinalizeOrder);
        btnHistory = findViewById(R.id.btnHistory);
        btnProfile = findViewById(R.id.btnProfile);

        // Initialize menu dictionary
        menu = new HashMap<>();

        // Initialize purchase list
        purchase = new ArrayList<>();

        // Add item button click listener
        btnAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemToOrder();
            }
        });

        // Finalize order button click listener
        btnFinalizeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalizeOrder();
            }
        });

        // History button click listener
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            }
        });

        // Profile button click listener
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });
    }

    // Method to add item to order
    private void addItemToOrder() {
        String itemName = etItem.getText().toString().trim().toUpperCase();
        int quantity = Integer.parseInt(etQuantity.getText().toString().trim());

        // Check if item exists in menu
        if (menu.containsKey(itemName)) {
            float itemPrice = menu.get(itemName);
            float individualCost = itemPrice * quantity;

            // Add item to purchase list
            ArrayList<Object> itemDetails = new ArrayList<>();
            itemDetails.add(itemName);
            itemDetails.add(quantity);
            itemDetails.add(individualCost);
            purchase.add(itemDetails);

            // Calculate total cost
            float totalCost = calculateTotalCost();
            tvTotalCost.setText(String.format(Locale.getDefault(), "Total Cost: ₹%.2f", totalCost));

            // Clear item and quantity fields
            etItem.setText("");
            etQuantity.setText("");
        } else {
            // Display error message if item not found in menu
            etItem.setError("Item not found in menu!");
        }
    }

    // Method to calculate total cost of order
    private float calculateTotalCost() {
        float total = 0.0f;
        for (Object item : purchase) {
            if (item instanceof ArrayList) {
                ArrayList<Object> details = (ArrayList<Object>) item;
                if (details.size() == 3 && details.get(2) instanceof Float) {
                    total += (Float) details.get(2);
                }
            }
        }
        return total;
    }

    // Method to finalize order and proceed to BillActivity
    private void finalizeOrder() {
        // Save purchase details in SharedPreferences or pass it to BillActivity
        savePurchaseDetails();

        // Proceed to BillActivity
        startActivity(new Intent(MainActivity.this, BillActivity.class));
    }

    // Method to save purchase details in SharedPreferences or local storage
    private void savePurchaseDetails() {
        // Get current date and time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String currentDateAndTime = sdf.format(new Date());

        // Get customer details
        String customerName = etCustomerName.getText().toString().trim();
        String customerAddress = etCustomerAddress.getText().toString().trim();

        // Create purchase entry
        ArrayList<Object> orderDetails = new ArrayList<>();
        orderDetails.add(currentDateAndTime);
        orderDetails.add(customerName);
        orderDetails.add(customerAddress);
        orderDetails.add(purchase);
        orderDetails.add(calculateTotalCost());

        // Save or send purchase details as required
        // For demonstration, printing purchase details
        System.out.println("Purchase Details:");
        System.out.println("Date: " + currentDateAndTime);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Customer Address: " + customerAddress);
        System.out.println("Items Ordered: " + purchase);
        System.out.println("Total Cost: ₹" + calculateTotalCost());
    }
}
