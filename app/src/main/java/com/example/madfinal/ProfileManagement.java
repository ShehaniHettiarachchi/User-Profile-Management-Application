package com.example.madfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.madfinal.Database.DBHandler;

public class ProfileManagement extends AppCompatActivity {

    EditText username,dob,password;
    Button add,update;
    RadioButton male,female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        username = findViewById(R.id.etusernamePM);
        dob = findViewById(R.id.etdobPM);
        password = findViewById(R.id.etpasswordPM);
        add = findViewById(R.id.btnaddPM);
        update = findViewById(R.id.btnupdateprofilePM);
        male = findViewById(R.id.rbmalePM);
        female = findViewById(R.id.rbfemalePM);

      update.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(),EditProfile.class);
            startActivity(i);
          }
      });

      add.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              if (male.isChecked()){
                  gender = "Male";
              }
              else{
                  gender = "Female";
              }

              DBHandler dbHelper = new DBHandler(getApplicationContext());
              long newID = dbHelper.addInfo(username.getText().toString(), dob.getText().toString(), password.getText().toString(), gender);

              //toast
              Toast.makeText(ProfileManagement.this, "User Added Successfully : " + newID, Toast.LENGTH_SHORT).show();

              Intent i = new Intent(getApplicationContext(),EditProfile.class);
              startActivity(i);

              username.setText(null);
              dob.setText(null);
              password.setText(null);
              male.setChecked(false);
              female.setChecked(true);
          }
      });

    }

}