package com.example.billingapp;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;

public class EditMenuActivity extends AppCompatActivity {

    private LinearLayout layoutMenuItems;
    private EditText etEnterItem, etEnterPrice;
    private Button btnAddItem, btnSaveChanges;
    private Map<String, Double> menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);

        // Initialize views
        layoutMenuItems = findViewById(R.id.layoutMenuItems);
        etEnterItem = findViewById(R.id.etEnterItem);
        etEnterPrice = findViewById(R.id.etEnterPrice);
        btnAddItem = findViewById(R.id.btnAddItem);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);

        // Retrieve menu from SharedPreferences (assuming it was saved during SetupActivity)
        SharedPreferences prefs = getSharedPreferences("bills_prefs", MODE_PRIVATE);
        menu = new HashMap<>(); // Initialize menu dictionary

        // Example initialization (replace with actual retrieval logic)
        // Assuming menu was stored as a serialized string and needs to be deserialized
        // Example: String menuJson = prefs.getString("menu", "");
        // Example: menu = deserializeMenu(menuJson);

        // Populate menu items in the layout
        populateMenuItems();

        // Add Item button click listener
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = etEnterItem.getText().toString().trim().toUpperCase();
                String priceStr = etEnterPrice.getText().toString().trim();
                if (!itemName.isEmpty() && !priceStr.isEmpty()) {
                    double price = Double.parseDouble(priceStr);
                    menu.put(itemName, price);
                    addMenuItemToLayout(itemName, price);
                    etEnterItem.setText("");
                    etEnterPrice.setText("");
                }
            }
        });

        // Save Changes button click listener
        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save menu changes to SharedPreferences
                saveMenuToSharedPreferences();

                // Optional: Show confirmation message
                // Example: Toast.makeText(EditMenuActivity.this, "Changes saved!", Toast.LENGTH_SHORT).show();
                finish(); // Finish activity
            }
        });
    }

    private void populateMenuItems() {
        // Clear existing views
        layoutMenuItems.removeAllViews();

        // Iterate through menu items and add TextViews dynamically
        for (String itemName : menu.keySet()) {
            double price = menu.get(itemName);
            addMenuItemToLayout(itemName, price);
        }
    }

    private void addMenuItemToLayout(String itemName, double price) {
        // Create a TextView for each menu item
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setText(String.format("%s: $%.2f", itemName, price));
        textView.setTextSize(16);
        textView.setPadding(0, 8, 0, 8);

        // Add TextView to the layout
        layoutMenuItems.addView(textView);
    }

    private void saveMenuToSharedPreferences() {
        // Convert menu to a serialized format (e.g., JSON) and save to SharedPreferences
        // Example: String menuJson = serializeMenu(menu);
        // Example: prefs.edit().putString("menu", menuJson).apply();

        // For demonstration, let's assume we save a single item in this example
        SharedPreferences.Editor editor = getSharedPreferences("bills_prefs", MODE_PRIVATE).edit();
        editor.putString("menu", menu.toString()); // Serialize menu to string
        editor.apply();
    }
}
