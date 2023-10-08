package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetActivity extends AppCompatActivity {
    Button btnReset;
    EditText edEmail;
    FirebaseAuth mAuth;
    String strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        btnReset = findViewById(R.id.buttonReset);
        edEmail = findViewById(R.id.editTextEmail);
        mAuth = FirebaseAuth.getInstance();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strEmail = edEmail.getText().toString().trim();
                if (!TextUtils.isEmpty(strEmail)) {
                    resetEmail();
                } else {
                    edEmail.setError("Email field can't be empty");
                }
            }
        });
    }

    private void resetEmail() {
        mAuth.sendPasswordResetEmail(strEmail).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ForgetActivity.this, "Reset password Email has been sent", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForgetActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ForgetActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                btnReset.setVisibility(View.INVISIBLE);
            }
        });
    }
}
































//package com.example.myapplication;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//
//public class ForgetActivity extends AppCompatActivity {
//    Button tnReset;
//    EditText edmail;
//    ProgressBar progressbar;
//        FirebaseAuth mAuth;
//    String strEmail;
//
//
//
//    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_forget);
//        tnReset =findViewById(R.id.buttonReset);
//        edmail=findViewById(R.id.Forgotpassword);
//        mAuth=FirebaseAuth.getInstance();
//        tnReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                strEmail=edmail.getText().toString().trim();
//                System.out.println(strEmail );
//                if(!TextUtils.isEmpty(strEmail)){
//                    ResetEmail();
//                }
//                else{
//                    edmail.setError("Email field can't be empty");
//                }
//            }
//        });
//
//    }
//
//    private void ResetEmail() {
//        mAuth.sendPasswordResetEmail(strEmail).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Toast.makeText(ForgetActivity.this, "Reset password Email has been send", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(ForgetActivity.this,LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(ForgetActivity.this, "Error :- "+ e.getMessage(), Toast.LENGTH_SHORT).show();
//                tnReset.setVisibility(View.INVISIBLE);
//
//            }
//        });
//    }
//}