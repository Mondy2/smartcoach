package com.example.smartcoach;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SupportActivity extends AppCompatActivity {

    private TextInputEditText etTheme, etName, etMessage;
    private TextView tvCounter;
    private Button btnSend, btnBack;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        // Ініціалізація Firebase
        auth = FirebaseAuth.getInstance();

        // Прив'язка елементів
        etTheme = findViewById(R.id.et_theme);
        etName = findViewById(R.id.et_name);
        etMessage = findViewById(R.id.et_message);
        tvCounter = findViewById(R.id.tv_counter);
        btnSend = findViewById(R.id.btn_send);
        btnBack = findViewById(R.id.btn_back);
        TextInputEditText etEmail = findViewById(R.id.et_email);

        // Встановлення email користувача (нередагується)
        FirebaseUser user = auth.getCurrentUser();
        if (user != null && user.getEmail() != null) {
            etEmail.setText(user.getEmail());
        }

        // Лічильник символів для повідомлення
        etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Не використовується
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvCounter.setText(s.length() + "/500");
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 500) {
                    etMessage.setError("Максимум 500 символів!");
                }
            }
        });

        // Обробник кнопки "Назад"
        btnBack.setOnClickListener(v -> finish());

        // Обробник кнопки "Надіслати"
        btnSend.setOnClickListener(v -> {
            if (validateForm()) {
                sendSupportRequest();
            }
        });
        // Настройка Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_settings);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(SupportActivity.this, HomeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_generate_workout) {
                startActivity(new Intent(SupportActivity.this, GenerateWorkoutActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_settings) {
                // Уже находимся на этой странице
                return true;
            }
            return false;
        });
    }

    // Валідація форми
    private boolean validateForm() {
        String theme = etTheme.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String message = etMessage.getText().toString().trim();

        if (theme.isEmpty()) {
            etTheme.setError("Введіть тему!");
            return false;
        }

        if (name.isEmpty()) {
            etName.setError("Введіть ім’я!");
            return false;
        }

        if (message.isEmpty()) {
            etMessage.setError("Введіть повідомлення!");
            return false;
        }

        if (message.length() > 500) {
            etMessage.setError("Максимум 500 символів!");
            return false;
        }

        return true;
    }

    // Імітація відправки запиту
    private void sendSupportRequest() {
        Toast.makeText(this, "Повідомлення успішно відправлено!", Toast.LENGTH_SHORT).show();
        finish();
    }
}