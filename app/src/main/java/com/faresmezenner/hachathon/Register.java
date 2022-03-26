package com.faresmezenner.hachathon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    boolean org;
    TextView login_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("infos", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Intent register_intent = new Intent(getApplicationContext(), Home.class);

        Button indv_btn = findViewById(R.id.indv), org_btn = findViewById(R.id.org), login_guest = findViewById(R.id.login_as_guest);
        indv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                org = false;
                register_intent.putExtra("org", 0);
                startActivity(register_intent);
                finish();
            }
        });
        org_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                org = true;
                register_intent.putExtra("org", 1);
                startActivity(register_intent);
                finish();
            }
        });
        login_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                register_intent.putExtra("guest", 1);
                startActivity(register_intent);
            }
        });

        login_text = findViewById(R.id.login_text);
        String text = "Already have an account? Log in!";

        SpannableString ss = new SpannableString(text);

        ClickableSpan login = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent register = new Intent(Register.this, LogIn.class);
                startActivity(register);
                finish();
            }



        };
        ss.setSpan(login,25 , 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        login_text.setText(ss);
        login_text.setMovementMethod(LinkMovementMethod.getInstance());



    }

}