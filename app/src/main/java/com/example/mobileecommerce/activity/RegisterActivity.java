package com.example.mobileecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.api.SignUpAPI;
import com.example.mobileecommerce.model.UserModel;
import com.example.mobileecommerce.model.dto.ResponseDTO;
import com.example.mobileecommerce.retrofit.RetrofitClient;
import com.example.mobileecommerce.service.UserService;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        // Component
        EditText username = (EditText) findViewById(R.id.username);
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);
        EditText re_password = (EditText) findViewById(R.id.repassword);
        MaterialButton regbtn = (MaterialButton) findViewById(R.id.signupbtn);

        regbtn.setOnClickListener(new View.OnClickListener() {
            UserService userService = new UserService();
            @Override
            public void onClick(View v) {
                if(userService.isGmailAddress(email.getText().toString().trim()))
                {
                    if(userService.checkPassword(password.getText().toString().trim(),
                            re_password.getText().toString().trim())) {
                        UserModel user = new UserModel(
                                username.getText().toString().trim(),
                                email.getText().toString().trim(),
                                password.getText().toString().trim()
                        );
                        CallSignUpAPI(user);
                    } else {
                        Toast.makeText(RegisterActivity.this,"Password Confirmation Error",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this,"This is not GMAIL",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void CallSignUpAPI(UserModel user) {

        SignUpAPI signUpAPI = RetrofitClient.getRetrofit().create(SignUpAPI.class);

        signUpAPI.SignUp(user).enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                if(response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RegisterActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}