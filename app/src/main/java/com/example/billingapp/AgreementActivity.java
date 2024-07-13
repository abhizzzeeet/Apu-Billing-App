package com.example.billingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AgreementActivity extends AppCompatActivity {

    private TextView tvAgreementText;
    private Button btnAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);

        // Initialize views
        tvAgreementText = findViewById(R.id.tvAgreementText);
        btnAgree = findViewById(R.id.btnAgree);

        // Get data from SetupActivity
        SharedPreferences prefs = getSharedPreferences("bills_prefs", MODE_PRIVATE);
        String ownerName = prefs.getString("ownerName", "");
        String shopName = prefs.getString("shopName", "");
        String shopAddress = prefs.getString("shopAddress", "");

        // Format current date and time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss", Locale.getDefault());
        String currentDateAndTime = sdf.format(new Date());

        // Construct agreement text
        String agreementText = "I, " + ownerName + ", owner of " + shopName + " located at " + shopAddress +
                " am purchasing the subscription for this app on " + currentDateAndTime + ".";
        tvAgreementText.setText(agreementText);

        // Agree button click listener
        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedToMainActivity();
            }
        });
    }

    // Method to proceed to MainActivity and save agreement completion status
    private void proceedToMainActivity() {
        // Save agreement completion status
        saveAgreementComplete();

        // Proceed to MainActivity
        startActivity(new Intent(AgreementActivity.this, MainActivity.class));
        finish();
    }

    // Method to save agreement completion status
    private void saveAgreementComplete() {
        SharedPreferences.Editor editor = getSharedPreferences("bills_prefs", MODE_PRIVATE).edit();
        editor.putBoolean("isAgreementComplete", true);
        editor.apply();
    }
}
