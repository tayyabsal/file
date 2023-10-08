package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class loginscreenActivity extends AppCompatActivity {
    Spinner spinner,spinner1;
    Button next;
    String cate[]={"Doctor","Assistant","Nurse"};
    private boolean[] selectedHealthOptions = new boolean[5]; // To store selected health options
    private EditText feeEditText;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);
        spinner=findViewById(R.id.Spinner);

        spinner1=findViewById(R.id.spinner);
        next=findViewById(R.id.Next);


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_options,
                android.R.layout.simple_spinner_item
        );

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedOption = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });



        // Initialize your UI components


        // Show the first dialog to select doctor, assistant, or nurse


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(loginscreenActivity.this,android.R.layout.simple_spinner_item,cate);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value= adapterView.getItemAtPosition(i).toString();
                Toast.makeText(loginscreenActivity.this, value, Toast.LENGTH_SHORT).show();}

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginscreenActivity.this,feeactivity.class));
            }
        });

    }




}



