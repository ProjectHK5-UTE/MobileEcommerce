package com.example.mobileecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileecommerce.R;
import com.google.android.material.button.MaterialButton;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);


        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        EditText repassword = (EditText) findViewById(R.id.repassword);
        MaterialButton regbtn = (MaterialButton) findViewById(R.id.signupbtn);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username1 = username.getText().toString();
                String password1 = password.getText().toString();
                String repassword1 = repassword.getText().toString();
                if(password1.equals(repassword1)){
                    Toast.makeText(RegisterActivity.this,"Username is "+username1,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, OTPVerifyActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(RegisterActivity.this,"NOT OK!!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}