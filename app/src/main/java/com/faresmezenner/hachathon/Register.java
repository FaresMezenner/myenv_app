package com.faresmezenner.hachathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.location.LocationManagerCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Register extends AppCompatActivity {
    public static final String provider = null;
    EditText[] info = new EditText[4];
    ImageView search;
    Button next;
    Boolean[] elements = new Boolean[]{false, false, false, false, false, false, false};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("infos", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        info[0] = findViewById(R.id.name);
        info[1] = findViewById(R.id.email);
        info[2] = findViewById(R.id.num);
        info[3] = findViewById(R.id.addr);
        next = findViewById(R.id.next);
        search = findViewById(R.id.search);
        String addressFinal = null;


        /**Alert dialog making***/
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(Register.this,R.style.CustomAlertDialog);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(Register.this).inflate(R.layout.warning_missing_info, viewGroup, false);
        Button buttonOk=dialogView.findViewById(R.id.ok);
        alertBuilder.setView(dialogView);


        AlertDialog alerdialog = alertBuilder.create();

        Address address = getAdr();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean procced = true;
                for(int i =0; i<4;i++){
                    if(info[i].getText().toString().isEmpty()){
                        procced = false;
                        break;
                    } else{
                        switch (i){
                            case 0: editor.putString("Name", info[i].getText().toString());
                            case 1: editor.putString("Email", info[i].getText().toString());
                            case 2: editor.putString("Num", info[i].getText().toString());
                            case 3: editor.putString("Location", info[i].getText().toString());
                        }
                    }
                }
                if(procced){
                    Intent next = new Intent(Register.this, Password.class);
                    startActivity(next);
                } else {

                    alerdialog.show();
                }
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alerdialog.dismiss();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(info[3].getText().toString().length() >= 10 ){

                    Address address = getAdr();

                    if(address == null){
                        info[3].setText("no match!");
                    }else {
                        if(address.getFeatureName()!=null && address.getLocality() != null && address.getAdminArea() != null && address.getCountryName() != null){
                            Log.d("test", address.getFeatureName() + ", " + address.getLocality() +", " + address.getAdminArea() + ", " + address.getCountryName());
                            info[3].setText(address.getFeatureName() + ", " + address.getLocality() +", " + address.getAdminArea() + ", " + address.getCountryName());

                        } else {

                            info[3].setText("no match!");
                        }
                    }
                }

            }
        });
        /*
        try {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            Geocoder geo = new Geocoder(Register.this.getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geo.getFromLocation(latitude, longitude, 1);
            if (addresses.isEmpty()) {
                addr.setText("Waiting for Location");
            }
            else {
                if (addresses.size() > 0) {
                    addr.setText(addresses.get(0).getFeatureName() + ", " + addresses.get(0).getLocality() +", " + addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName());
                    //Toast.makeText(getApplicationContext(), "Address:- " + addresses.get(0).getFeatureName() + addresses.get(0).getAdminArea() + addresses.get(0).getLocality(), Toast.LENGTH_LONG).show();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace(); // getFromLocation() may sometimes fail
        }

         */
    }

    Address getAdr(){
        Address address = null;
        String LocationName = info[3].getText().toString();
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocationName(LocationName, 1);
            if(addressList.size() >0){
                address = addressList.get(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return address;
    }
}