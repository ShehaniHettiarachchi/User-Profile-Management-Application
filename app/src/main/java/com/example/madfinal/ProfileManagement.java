package com.example.madfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class ProfileManagement extends AppCompatActivity {

    EditText username,dob,password;
    Button search,edit,delete;
    RadioButton male,female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        username = findViewById(R.id.etusernameEP);



        //comment
      search.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

          }
      });

    }

}