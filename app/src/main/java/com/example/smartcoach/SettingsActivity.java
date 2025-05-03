package com.example.smartcoach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    private CardView profileButton, emailButton, changePasswordButton,
            usefulInfoButton, supportButton, logoutButton;
    private Animation scaleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initializeViews();
        setupAnimations();
        setupClickListeners();

        // Настройка Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_settings);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(SettingsActivity.this, HomeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_generate_workout) {
                startActivity(new Intent(SettingsActivity.this, GenerateWorkoutActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_settings) {
                // Уже находимся на этой странице
                return true;
            }
            return false;
        });
    }

    private void initializeViews() {
        profileButton = findViewById(R.id.profile_button);
        emailButton = findViewById(R.id.email_button);
        changePasswordButton = findViewById(R.id.change_password_button);
        usefulInfoButton = findViewById(R.id.useful_info_button);
        supportButton = findViewById(R.id.support_button);
        logoutButton = findViewById(R.id.logout_button);
    }

    private void setupAnimations() {
        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.button_scale);
    }

    private void setupClickListeners() {
        // Профіль
        profileButton.setOnClickListener(v -> {
            v.startAnimation(scaleAnimation);
            startActivityWithAnimation(ProfileActivity.class);
        });

        // Мій email
        emailButton.setOnClickListener(v -> {
            v.startAnimation(scaleAnimation);
            // Можна додати логіку для відображення email
            showEmailInfo();
        });

        // Зміна пароля
        changePasswordButton.setOnClickListener(v -> {
            v.startAnimation(scaleAnimation);
            startActivityWithAnimation(ChangePasswordActivity.class);
        });

        // Корисна інформація
        usefulInfoButton.setOnClickListener(v -> {
            v.startAnimation(scaleAnimation);
            startActivityWithAnimation(InfoActivity.class);
        });

        // Підтримка
        supportButton.setOnClickListener(v -> {
            v.startAnimation(scaleAnimation);
            startActivityWithAnimation(SupportActivity.class);
        });

        // Вийти з акаунта
        logoutButton.setOnClickListener(v -> {
            v.startAnimation(scaleAnimation);
            performLogout();
        });
    }

    private void startActivityWithAnimation(Class<?> cls) {
        Intent intent = new Intent(SettingsActivity.this, cls);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void showEmailInfo() {
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        if (email != null) {
            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Ваш email")
                    .setMessage(email)
                    .setPositiveButton("OK", null)
                    .show();
        }
    }

    private void performLogout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finishAffinity();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}