package com.example.smartcoach;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcoach.models.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Инициализация FirebaseAuth и ссылки на базу данных
        auth = FirebaseAuth.getInstance();
        users = FirebaseDatabase.getInstance().getReference("users");

        final TextInputEditText emailInput = findViewById(R.id.emailInput);
        final TextInputEditText passwordInput = findViewById(R.id.passwordInput);
        final TextInputEditText nameInput = findViewById(R.id.nameInput);

        findViewById(R.id.registerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получение и обрезка введённых значений
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();
                String name = nameInput.getText().toString().trim();

                // Проверка корректности введённых данных
                if (TextUtils.isEmpty(email)) {
                    Snackbar.make(v, "Пожалуйста, введите email", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Snackbar.make(v, "Пожалуйста, введите имя", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 5) {
                    Snackbar.make(v, "Пароль должен содержать более 5 символов", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                // Создание пользователя с email и паролем
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = auth.getCurrentUser();
                                if (firebaseUser != null) {
                                    // Создание объекта пользователя и установка данных
                                    User user = new User();
                                    user.setName(name);
                                    user.setEmail(email);

                                    // Запись объекта в базу данных по уникальному идентификатору пользователя
                                    users.child(firebaseUser.getUid()).setValue(user)
                                            .addOnSuccessListener(aVoid ->
                                                    Snackbar.make(v, "Регистрация прошла успешно", Snackbar.LENGTH_SHORT).show()
                                            )
                                            .addOnFailureListener(e ->
                                                    Snackbar.make(v, "Ошибка базы данных: " + e.getMessage(), Snackbar.LENGTH_LONG).show()
                                            );
                                } else {
                                    Snackbar.make(v, "Пользователь не найден, попробуйте еще раз", Snackbar.LENGTH_LONG).show();
                                }
                            } else {
                                Snackbar.make(v, "Ошибка регистрации: " + task.getException().getMessage(), Snackbar.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
}
