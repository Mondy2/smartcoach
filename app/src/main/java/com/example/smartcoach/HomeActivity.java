package com.example.smartcoach;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Обработка нажатия кнопки "Generate Workout"
        findViewById(R.id.openGenerateWorkout).setOnClickListener(v -> {
            Log.d("HomeActivity", "Generate Workout button clicked");
            startActivity(new Intent(HomeActivity.this, GenerateWorkoutActivity.class));
        });

        // Настройка нижней навигации
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home); // Устанавливаем Home как выбранный по умолчанию
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                Log.d("HomeActivity", "Home selected");
                return true;
            } else if (itemId == R.id.nav_generate_workout) {
                Log.d("HomeActivity", "Generate Workout selected");
                startActivity(new Intent(HomeActivity.this, GenerateWorkoutActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_settings) {
                Log.d("HomeActivity", "Settings selected");
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }
}
/*package com.example.smartcoach;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.openGenerateWorkout).setOnClickListener(v -> {
            Log.d("HomeActivity", "Generate Workout button clicked");
            startActivity(new Intent(HomeActivity.this, GenerateWorkoutActivity.class));
        });
    }
}*/