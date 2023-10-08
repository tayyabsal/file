package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.myapplication.databinding.ActivityMainBinding;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText edUsername,edPassword;
    Button btn;
    TextView tv,tv1;
    private FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activityl);

        edUsername=findViewById(R.id.editTextEmail);
        edPassword=findViewById(R.id.editTextLoginPassword);
        btn=findViewById(R.id.buttonReset);
        tv=findViewById(R.id.textViewNewUser);
        tv1=findViewById(R.id.Forgotpassword);

        auth=FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email=edUsername.getText().toString();
                String txt_password=edPassword.getText().toString();
                UserName(txt_email,txt_password);

//                String username= edUsername.getText().toString();
//                String password=edPassword.getText().toString();
//                Database db= new Database(getApplicationContext(),"healthcare",null,1);
//
//                if(username.length()==0 || password.length()==0){
//                    Toast.makeText(getApplicationContext(),"Please Fill All Details",Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    if(db.login(username,password)==1){
//                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
//                        SharedPreferences sharedPreferences=getSharedPreferences("shared-prefs", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor= sharedPreferences.edit();
//                        editor.putString("username",username);
//                        editor.apply();
//                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(),"Invalid Username and password",Toast.LENGTH_SHORT).show();
//                    }
//                }
            }

        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ForgetActivity.class));
            }
        });

    }

    private void UserName(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginActivity.this, "LOG In Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,loginscreenActivity .class));
                finish();

            }
        });
    }
}