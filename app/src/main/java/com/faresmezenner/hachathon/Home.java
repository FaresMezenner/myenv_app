package com.faresmezenner.hachathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button add = findViewById(R.id.add_btn);
        ImageView[] navig = new ImageView[]{findViewById(R.id.main),findViewById(R.id.notif)};
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment[] fragments = new Fragment[]{new Main(), new Add(), new Notif()};
        fragmentTransaction.replace(R.id.fragment, fragments[0]);
        fragmentTransaction.commit();
        for(int i = 0; i<2;i++){
            int finalI = i;
            navig[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    replaceFragment(fragments[finalI]);
                }
            });
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(fragments[1]);
            }
        });

        CardView pfp = findViewById(R.id.pfp);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        Log.d("widthPixels", String.valueOf(width));

        int left_margin = Integer.valueOf(width*59/1080);
        int top_margin = Integer.valueOf(width*55/1080);
        int b_dim = Integer.valueOf(width*200/1080);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(b_dim, b_dim);

        params.setMargins(left_margin, top_margin, 0 ,0);

        pfp.setLayoutParams(params);
        int add_dim = Integer.valueOf(width*170/1080);
        RelativeLayout.LayoutParams add_params = new RelativeLayout.LayoutParams(add_dim, add_dim);

        add_params.setMargins(left_margin, top_margin, 0 ,0);
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }
}