package com.example.billingapp;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 1000; // 1 second

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Logic to check if Setup and Agreement are complete
                boolean isSetupComplete = checkSetupComplete();
                boolean isAgreementComplete = checkAgreementComplete();

                if (!isSetupComplete) {
                    startActivity(new Intent(SplashActivity.this, SetupActivity.class));
                } else if (!isAgreementComplete) {
                    startActivity(new Intent(SplashActivity.this, AgreementActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
                finish();
            }
        }, SPLASH_DURATION);
    }

    private boolean checkSetupComplete() {
        // Logic to check if Setup is complete
        // This could be a shared preference or database check
        return getSharedPreferences("bills_prefs", MODE_PRIVATE).getBoolean("isSetupComplete", false);
    }

    private boolean checkAgreementComplete() {
        // Logic to check if Agreement is complete
        // This could be a shared preference or database check
        return getSharedPreferences("bills_prefs", MODE_PRIVATE).getBoolean("isAgreementComplete", false);
    }
}
