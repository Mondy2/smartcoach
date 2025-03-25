package com.example.smartcoach;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcoach.models.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {
    Button btnSign, btnRegister;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    RelativeLayout root;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSign = findViewById(R.id.btnSignIn);
        btnRegister=findViewById(R.id.btnRegister);
        root = findViewById(R.id.root_element);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterWindow();
            }

            
        });
    }

    private void showRegisterWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Register");
        dialog.setMessage("Please fill out the following form:");
        LayoutInflater inflater = LayoutInflater.from(this);
        View registerWindow = inflater.inflate(R.layout.register_window, null);
        dialog.setView(registerWindow);
        final MaterialEditText email = registerWindow.findViewById(R.id.emailField);
        final MaterialEditText password = registerWindow.findViewById(R.id.passwordField);
        final MaterialEditText name = registerWindow.findViewById(R.id.nameField);
       dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               dialogInterface.dismiss();
           }
       });

       dialog.setPositiveButton("Register", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               if (TextUtils.isEmpty(email.getText().toString())) {
                   Snackbar.make(root, "Please enter email", Snackbar.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(name.getText().toString())) {
                   Snackbar.make(root, "Please enter name", Snackbar.LENGTH_SHORT).show();
                   return;
               }
               if (password.getText().toString().length() < 5) {
                   Snackbar.make(root, "Please enter password with more than 5 characters", Snackbar.LENGTH_SHORT).show();
                   return;
               }
               auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                       .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                           @Override
                           public void onSuccess(AuthResult authResult) {
                               User user = new User();
                               user.setName(name.getText().toString());
                               user.setEmail(email.getText().toString());
                               user.setPassword(password.getText().toString());

                               users.child(user.getEmail()).setValue(user)
                                       .addOnSuccessListener(new OnSuccessListener<Void>() {
                                           @Override
                                           public void onSuccess(Void avoid) {
                                               Snackbar.make(root, "Registration successful", Snackbar.LENGTH_SHORT).show();
                                           }


                                       });
                           }
                       });
           }
       });

       dialog.show();
    }
}
