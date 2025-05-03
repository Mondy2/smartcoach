package com.example.smartcoach;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TermsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        // Ініціалізація TextView
        TextView termsText = findViewById(R.id.terms_text);

        // Встановлення тексту з HTML-форматуванням
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            termsText.setText(Html.fromHtml(getString(R.string.terms_of_use_full_text), Html.FROM_HTML_MODE_COMPACT));
        } else {
            termsText.setText(Html.fromHtml(getString(R.string.terms_of_use_full_text)));
        }

        // Кнопка "Назад"
        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        // Настройка Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_settings);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(TermsActivity.this, HomeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_generate_workout) {
                startActivity(new Intent(TermsActivity.this, GenerateWorkoutActivity.class));
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