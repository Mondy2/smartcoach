package com.example.smartcoach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Кнопки
        CardView btnAbout = findViewById(R.id.btn_about);
        CardView btnFAQ = findViewById(R.id.btn_faq);
        CardView btnTerms = findViewById(R.id.btn_terms);
        CardView btnPrivacy = findViewById(R.id.btn_privacy);
        View btnBack = findViewById(R.id.btn_back);

        // Обробники кліків
        btnAbout.setOnClickListener(v -> startActivity(new Intent(this, AboutActivity.class)));
        btnFAQ.setOnClickListener(v -> startActivity(new Intent(this, FAQActivity.class)));
        btnTerms.setOnClickListener(v -> startActivity(new Intent(this, TermsActivity.class)));
        btnPrivacy.setOnClickListener(v -> startActivity(new Intent(this, PrivacyPolicyActivity.class)));
        btnBack.setOnClickListener(v -> finish());

        // Настройка Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_settings);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(InfoActivity.this, HomeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_generate_workout) {
                startActivity(new Intent(InfoActivity.this, GenerateWorkoutActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_settings) {
                // Уже находимся на этой странице
                return true;
            }
            return false;
        });
    }
}