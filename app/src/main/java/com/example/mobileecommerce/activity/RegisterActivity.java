package com.example.mobileecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

        Spinner spinnerOptions = findViewById(R.id.spinner_options_role);
        String[] options = {"ROLE_USER", "ROLE_MANAGER", "ROLE_SHIPPER"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOptions.setAdapter(adapter);

        regbtn.setOnClickListener(new View.OnClickListener() {
            UserService userService = new UserService();

            @Override
            public void onClick(View v) {
                String selectedOption = spinnerOptions.getSelectedItem().toString();
                Log.e("Role", "là" + selectedOption);
                String isCheck = userService.checkFormSignUp(email.getText().toString().trim(),
                        password.getText().toString().trim(),
                        re_password.getText().toString().trim());
                if(isCheck == "Success")
                {
                    CheckSignUp(username.getText().toString().trim(),
                            email.getText().toString().trim(),
                            password.getText().toString().trim(), selectedOption);
                } else {
                    Toast.makeText(RegisterActivity.this,isCheck,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void CheckSignUp(String username, String email, String password, String role) {
        SignUpAPI signUpAPI = RetrofitClient.getRetrofit60TimeOut().create(SignUpAPI.class);
        signUpAPI.checkSignUp(username, email).enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                if(response.isSuccessful()){
                    UserModel user = new UserModel(
                            username,
                            email,
                            password, role);
                    CallSignUpAPI(user);
                } else{
                    Toast.makeText(RegisterActivity.this, "Username or Email already exists", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {

            }
        });

    }

    public void CallSignUpAPI(UserModel user) {
        SignUpAPI signUpAPI = RetrofitClient.getRetrofit60TimeOut().create(SignUpAPI.class);

        signUpAPI.SignUp(user).enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {

                if(response.isSuccessful()){
                    String message = response.body().getMessage();
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                    gotoOTPVerify(user);
                }else {
                    Toast.makeText(RegisterActivity.this, "Fail to Sent OTP", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error When You Register", Toast.LENGTH_SHORT).show();
                Log.e("error signup",t.getMessage());
            }
        });
    }

    private void gotoOTPVerify(UserModel user) {
        Intent intent = new Intent(this, OTPVerifyActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}