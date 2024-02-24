package com.example.taxichernov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import com.example.taxichernov.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private String name;
    private String surname;
    private String phone;
    private ActivityMainBinding binding;
    SharedPreferences sPref;
    final String keyName = "Name";
    final String keySurname = "Surname";
    final String keyPhone = "Phone";

    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(keyName, binding.editTextName.getText().toString());
        editor.putString(keySurname, binding.editTextSurname.getText().toString());
        editor.putString(keyPhone, binding.editTextPhoneNumber.getText().toString());
        editor.commit();
    }
    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        name = sPref.getString (keyName, "");
        surname = sPref.getString (keySurname, "");
        phone = sPref.getString (keyPhone, "");
        binding.editTextName.setText(name);
        binding.editTextSurname.setText(surname);
        binding.editTextPhoneNumber.setText(phone);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intentToInfo = new Intent(this, InfoActivity.class);
        Intent intent = getIntent();
//        name = intent.getStringExtra("Name");
//        surname = intent.getStringExtra("Surname");
//        phone = intent.getStringExtra("Phone");
        loadText();
        binding.editTextPhoneNumber.setText(phone);
        binding.editTextName.setText(name);
        binding.editTextSurname.setText(surname);
        checkLog();
        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (binding.editTextName.getText().toString().isEmpty() || binding.editTextSurname.getText().toString().isEmpty() || binding.editTextPhoneNumber.getText().toString().isEmpty()) {
                    binding.buttonToInfo.setEnabled(false);
                }
                else binding.buttonToInfo.setEnabled(true);
                name = binding.editTextName.getText().toString();
                surname = binding.editTextSurname.getText().toString();
                phone = binding.editTextPhoneNumber.getText().toString();
                checkLog();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        binding.editTextName.addTextChangedListener(tw);
        binding.editTextSurname.addTextChangedListener(tw);
        binding.editTextPhoneNumber.addTextChangedListener(tw);
        binding.buttonToInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToInfo.putExtra("Name", binding.editTextName.getText().toString());
                intentToInfo.putExtra("Surname", binding.editTextSurname.getText().toString());
                intentToInfo.putExtra("Phone", binding.editTextPhoneNumber.getText().toString());
                intentToInfo.putExtra("PreviousActivity", "Main");
                startActivity(intentToInfo);
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
        saveText();
    }

    public void checkLog(){
        if  (Objects.equals(name, sPref.getString(keyName, "")) && Objects.equals(surname, sPref.getString(keySurname, "")) && Objects.equals(phone, sPref.getString(keyPhone, "")))
        {
            binding.buttonToInfo.setText("Log in");
        }
        else{
            binding.buttonToInfo.setText("Registration");
        }
    }
}