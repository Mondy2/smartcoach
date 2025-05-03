package com.example.smartcoach;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.smartcoach.databinding.ActivityProfileBinding;
import com.example.smartcoach.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private FirebaseAuth auth;
    private DatabaseReference database;
    private StorageReference storage;
    private Uri selectedImageUri;
    private static final int REQUEST_IMAGE_PICK = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance().getReference();

        setupUI();
        loadUserData();

        // Настройка Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_settings);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_generate_workout) {
                startActivity(new Intent(ProfileActivity.this, GenerateWorkoutActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_settings) {
                // Уже находимся на этой странице
                return true;
            }
            return false;
        });
    }

    private void setupUI() {
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.button_scale);

        binding.btnUploadPhoto.setOnClickListener(v -> openImagePicker());
        binding.btnSave.setOnClickListener(v -> {
            v.startAnimation(scaleAnimation);
            saveProfile();
        });
        binding.btnBack.setOnClickListener(v -> {
            v.startAnimation(scaleAnimation);
            finish();
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                Glide.with(this).load(selectedImageUri).circleCrop().into(binding.ivProfile);
                uploadImage(selectedImageUri);
            }
        }
    }

    private void loadUserData() {
        String userId = auth.getUid();
        if (userId == null) return;

        database.child("Users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user == null) return;

                binding.etName.setText(user.getName());
                binding.etHeight.setText(String.valueOf(user.getHeight()));
                binding.etWeight.setText(String.valueOf(user.getWeight()));
                binding.etBirthYear.setText(String.valueOf(user.getBirthYear()));

                if (user.getProfileImageUrl() != null) {
                    Glide.with(ProfileActivity.this)
                            .load(user.getProfileImageUrl())
                            .circleCrop()
                            .into(binding.ivProfile);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ProfileActivity", "Помилка завантаження даних: " + error.getMessage());
                showToast("Помилка завантаження даних");
            }
        });
    }

    private void saveProfile() {
        String userId = auth.getUid();
        if (userId == null) return;

        // Перевірка на порожні поля
        if (binding.etHeight.getText().toString().isEmpty() ||
                binding.etWeight.getText().toString().isEmpty() ||
                binding.etBirthYear.getText().toString().isEmpty()) {
            showToast("Заповніть всі поля");
            return;
        }

        try {
            String name = binding.etName.getText().toString().trim();
            int height = Integer.parseInt(binding.etHeight.getText().toString());
            float weight = Float.parseFloat(binding.etWeight.getText().toString());
            int birthYear = Integer.parseInt(binding.etBirthYear.getText().toString());

            // Валідація даних
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            if (name.isEmpty() || height < 50 || height > 300 || weight < 20 || weight > 300 || birthYear < 1900 || birthYear > currentYear) {
                showToast("Невірні дані");
                return;
            }

            // Збереження даних
            User user = new User();
            user.setName(binding.etName.getText().toString().trim());
            user.setHeight(height);
            user.setWeight(weight);
            user.setBirthYear(birthYear);
            user.setEmail(auth.getCurrentUser().getEmail());

            database.child("Users").child(userId).setValue(user)
                    .addOnSuccessListener(aVoid -> showToast("Дані збережено"))
                    .addOnFailureListener(e -> showToast("Помилка: " + e.getMessage()));

        } catch (NumberFormatException e) {
            showToast("Невірний формат даних");
            Log.e("ProfileActivity", "Помилка парсингу: ", e);
        }
    }

    private void uploadImage(Uri uri) {
        String userId = auth.getUid();
        if (userId == null) return;

        // Додайте розширення файлу
        StorageReference imageRef = storage.child("profile_images/" + userId + ".jpg"); // Змінено тут

        imageRef.putFile(uri)
                .addOnSuccessListener(taskSnapshot -> {
                    imageRef.getDownloadUrl().addOnSuccessListener(downloadUri -> {
                        database.child("Users").child(userId)
                                .child("profileImageUrl")
                                .setValue(downloadUri.toString());
                    });
                })
                .addOnFailureListener(e -> {
                    Log.e("Upload", "Помилка: " + e.getMessage()); // Додайте логування
                    showToast("Помилка: " + e.getMessage());
                });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}