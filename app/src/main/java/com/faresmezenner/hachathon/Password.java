package com.faresmezenner.hachathon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("infos", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Button next = findViewById(R.id.next_final);
        EditText password = findViewById(R.id.reg_pass);

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(Password.this,R.style.CustomAlertDialog);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(Password.this).inflate(R.layout.warning_missing_password, viewGroup, false);
        Button buttonOk=dialogView.findViewById(R.id.ok);
        alertBuilder.setView(dialogView);


        AlertDialog alerdialog = alertBuilder.create();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().toString().isEmpty()){
                    alerdialog.show();
                } else {
                    editor.putString("Password", password.getText().toString());
                    Intent next = new Intent(Password.this, Home.class);
                    startActivity(next);
                }
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alerdialog.dismiss();
            }
        });
    }
}