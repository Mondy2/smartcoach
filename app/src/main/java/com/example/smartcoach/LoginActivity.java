//package com.example.smartcoach;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.material.snackbar.Snackbar;
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//
//public class LoginActivity extends AppCompatActivity {
//
//    private FirebaseAuth auth;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        auth = FirebaseAuth.getInstance();
//
//        final TextInputEditText email = findViewById(R.id.emailInput);
//        final TextInputEditText password = findViewById(R.id.passwordInput);
//
//        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (TextUtils.isEmpty(email.getText().toString())) {
//                    Snackbar.make(v, "Please enter email", Snackbar.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(password.getText().toString())) {
//                    Snackbar.make(v, "Please enter password", Snackbar.LENGTH_SHORT).show();
//                    return;
//                }
//                auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
//                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                            @Override
//                            public void onSuccess(AuthResult authResult) {
//                                Snackbar.make(v, "Login successful", Snackbar.LENGTH_SHORT).show();
//                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                                finish();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(Exception e) {
//                                Snackbar.make(v, "Login failed: " + e.getMessage(), Snackbar.LENGTH_SHORT).show();
//                            }
//                        });
//            }
//        });
//    }
//}
package com.example.smartcoach;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private TextInputEditText emailInput, passwordInput;
    private View loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        // Инициализация полей ввода
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);

        // Анимация для кнопки
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.button_scale);
        loginButton.startAnimation(scaleAnimation);

        // Обработчик нажатия на кнопку авторизации
        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Snackbar.make(v, "Будь ласка, введіть email", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Snackbar.make(v, "Будь ласка, введіть пароль", Snackbar.LENGTH_SHORT).show();
                return;
            }


            auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(authResult -> {
                        Snackbar.make(v, "Авторизація успішна", Snackbar.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Snackbar.make(v, "Помилка авторизації: " + e.getMessage(), Snackbar.LENGTH_LONG).show();
                    });
        });

        // Обработчик нажатия на "Немає акаунта? Зареєструватися"
        findViewById(R.id.registerLink).setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }
}