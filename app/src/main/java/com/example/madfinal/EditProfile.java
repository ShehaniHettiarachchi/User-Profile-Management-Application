package com.example.madfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.madfinal.Database.DBHandler;

import java.util.List;

public class EditProfile extends AppCompatActivity {

    EditText username,dob,password;
    Button edit,delete, search;
    RadioButton male,female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        username = findViewById(R.id.etusernameEP);
        dob = findViewById(R.id.etdobEP);
        password = findViewById(R.id.etpasswordEP);
        edit = findViewById(R.id.btneditEP);
        delete = findViewById(R.id.btndeleteEP);
        search = findViewById(R.id.btnsearchEP);
        male = findViewById(R.id.rbmaleEp);
        female = findViewById(R.id.rbfemaleEP);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler dbHelper = new DBHandler(getApplicationContext());
                List user = dbHelper.readAllInfo(username.getText().toString());

                if (user.isEmpty()){
                    Toast.makeText(EditProfile.this, "No user available!", Toast.LENGTH_SHORT).show();
                    username.setText(null);
                }
                else{
                    Toast.makeText(EditProfile.this, "User found! User: "+user.get(0).toString(), Toast.LENGTH_SHORT).show();

                    username.setText(user.get(0).toString());
                    password.setText(user.get(2).toString());
                    dob.setText(user.get(1).toString());

                    if(user.get(3).toString().equals("Male")){
                        male.setChecked(true);
                    }
                    else{
                        female.setChecked(true);
                    }

                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (male.isChecked()){
                    gender = "Male";
                }
                else{
                    gender = "Female";
                }

                DBHandler dbHelper = new DBHandler(getApplicationContext());

                Boolean status = dbHelper.updateInfo(username.getText().toString(), dob.getText().toString(), password.getText().toString(), gender);
                if(status){
                    Toast.makeText(EditProfile.this, "User updated successfully!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EditProfile.this, "Update Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHelper = new DBHandler(getApplicationContext());
                dbHelper.deleteInfo(username.getText().toString());

                Toast.makeText(EditProfile.this, "User deleted!", Toast.LENGTH_SHORT).show();

                username.setText(null);
                password.setText(null);
                dob.setText(null);
                male.setChecked(false);
                female.setChecked(false);
            }
        });
    }

}