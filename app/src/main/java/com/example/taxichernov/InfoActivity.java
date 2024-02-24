package com.example.taxichernov;

import androidx.appcompat.app.AppCompatActivity;
import com.example.taxichernov.databinding.ActivityInfoBinding;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

public class InfoActivity extends AppCompatActivity {
    private String name;
    private String surname;
    private String phone;
    private String streetA;
    private String houseA;
    private String flatA;
    private String streetB;
    private String houseB;
    private String flatB;
    private String messageUser;
    private String messageRoute;
    private ActivityInfoBinding binding;
    SharedPreferences sPref;


    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("UserInfo", binding.textViewUserInfo.getText().toString());
        editor.putString("RouteInfo", binding.textViewRouteInfo.getText().toString());
        editor.commit();

    }
    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
            messageUser = sPref.getString("UserInfo", "");
            binding.textViewUserInfo.setText(messageUser);
            messageRoute = sPref.getString("RouteInfo", "");
            binding.textViewRouteInfo.setText(messageRoute);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        binding = ActivityInfoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        sPref = getPreferences(MODE_PRIVATE);
        if (Objects.equals(intent.getStringExtra("PreviousActivity"), "Main")) {
            name = intent.getStringExtra("Name");
            surname = intent.getStringExtra("Surname");
            phone = intent.getStringExtra("Phone");
            messageUser = name + " "+ surname + "\n"+ phone;
            if (streetA == null || houseA == null || flatA == null || streetB == null || houseB == null || flatB == null) {
                binding.buttonCallTaxi.setEnabled(false);
                messageRoute = "Set Path to call a taxi";
            }
            else {
                messageRoute = sPref.getString("RouteInfo", "");
                binding.buttonCallTaxi.setEnabled(true);
            }

        }
        else {
            streetA = intent.getStringExtra("StreetA");
            streetB = intent.getStringExtra("StreetB");
            houseA = intent.getStringExtra("HouseA");
            houseB = intent.getStringExtra("HouseB");
            flatA = intent.getStringExtra("FlatA");
            flatB = intent.getStringExtra("FlatB");
            messageUser = sPref.getString("UserInfo", "");
            if (isUncorrect(streetA)  || isUncorrect(houseA) || isUncorrect(flatA) || isUncorrect(streetB)  || isUncorrect(houseB) || isUncorrect(flatB)) {
                binding.buttonCallTaxi.setEnabled(false);
                messageRoute = "Set Path to call a taxi";
            }
            else {
                binding.buttonCallTaxi.setEnabled(true);
                messageRoute = "From: " + streetA + ", " + houseA + ", " + flatA + "\n" + "To: " + streetB + ", " + houseB + ", " + flatB;
            }

        }


        binding.textViewUserInfo.setText(messageUser);
        binding.textViewRouteInfo.setText(messageRoute);
        Intent intentToMain = new Intent(this, MainActivity.class);
        Intent intentToRoute = new Intent(this, RouteActivity.class);
        binding.buttonToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                intentToMain.putExtra("Name",name);
//                intentToMain.putExtra("Surname", surname);
//                intentToMain.putExtra("Phone", phone);
                startActivity(intentToMain);
            }
        });
        binding.buttonToRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentToRoute);
            }
        });
        binding.buttonCallTaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InfoActivity.this, "Taxi called", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveText();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        saveText();
    }
    static boolean isUncorrect(String s){
        return s == null || s.isEmpty();
    }
}