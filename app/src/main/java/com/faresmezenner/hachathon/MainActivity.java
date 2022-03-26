package com.faresmezenner.hachathon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_reg_in);
        Button login = findViewById(R.id.login_btn);
        TextView register_text = findViewById(R.id.register_text);
        String text = "You dont have an account? Register!";

        SpannableString ss = new SpannableString(text);

        ClickableSpan register = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                //Intent register = new Intent(MainActivity.this, Register.class);
                //startActivity(register);
                //finish();
            }



        };
        ss.setSpan(register,26 , 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        register_text.setText(ss);
        register_text.setMovementMethod(LinkMovementMethod.getInstance());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(getApplicationContext(), Home.class);
                startActivity(login);
                finish();
            }
        });
    }
}