package com.example.taxichernov;

import androidx.appcompat.app.AppCompatActivity;
import com.example.taxichernov.databinding.ActivityRouteBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RouteActivity extends AppCompatActivity {
ActivityRouteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        binding = ActivityRouteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intentToInfo = new Intent(this, InfoActivity.class);
        binding.buttonToInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToInfo.putExtra("StreetA", binding.editTextStreetA.getText().toString());
                intentToInfo.putExtra("HouseA", binding.editTextHouseA.getText().toString());
                intentToInfo.putExtra("FlatA", binding.editTextFlatA.getText().toString());

                intentToInfo.putExtra("StreetB", binding.editTextStreetB.getText().toString());
                intentToInfo.putExtra("HouseB", binding.editTextHouseB.getText().toString());
                intentToInfo.putExtra("FlatB", binding.editTextFlatB.getText().toString());

                intentToInfo.putExtra("PreviousActivity", "Route");
                startActivity(intentToInfo);
            }
        });

    }
}