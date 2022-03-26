package com.faresmezenner.hachathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        int guest = getIntent().getIntExtra("guest", 1), org = getIntent().getIntExtra("org", 0);
        if(guest == 1){
            CardView add_view = findViewById(R.id.add);
            add_view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.grey));
        }
        Button add = findViewById(R.id.add_btn);
        ImageView[] navig = new ImageView[]{findViewById(R.id.main),findViewById(R.id.notif)};
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment[] fragments = new Fragment[]{new Main(), new Add(), new Notif()};
        fragmentTransaction.replace(R.id.fragment, fragments[0]);
        fragmentTransaction.commit();

        if(guest == 1){
            CardView add_view = findViewById(R.id.add);
            add_view.setVisibility(View.INVISIBLE);
            String notif_guest_path = "@drawable/notif_guest";
            int notif_guest_id = getResources().getIdentifier(notif_guest_path, null, getClass().getPackage().getName());
            Drawable notif_guest = getResources().getDrawable(notif_guest_id, null);
            navig[1].setImageDrawable(notif_guest);
        }
        for(int i = 0; i<2;i++){
            int finalI = i;
            navig[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(guest != 1){

                        replaceFragment(fragments[finalI]);
                    }
                }
            });
        }
        LinearLayout org_add = findViewById(R.id.org_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(org == 0){

                    replaceFragment(fragments[1]);
                } else {
                    if(org_add.getVisibility() == View.GONE){
                        org_add.setVisibility(View.VISIBLE);
                    } else {
                        org_add.setVisibility(View.GONE);
                    }
                }
            }
        });

        TextView add_post = findViewById(R.id.add_post);
        add_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragment(fragments[1]);
                org_add.setVisibility(View.GONE);
            }
        });

        TextView add_compaign = findViewById(R.id.add_comp);
        add_compaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                org_add.setVisibility(View.GONE);
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