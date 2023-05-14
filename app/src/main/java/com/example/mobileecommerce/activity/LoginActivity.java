package com.example.mobileecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.api.LoginAPI;
import com.example.mobileecommerce.model.UserModel;
import com.example.mobileecommerce.model.dto.ResponseDTO;
import com.example.mobileecommerce.retrofit.RetrofitForLogin;
import com.example.mobileecommerce.service.JwtService;
import com.example.mobileecommerce.sharedpreferences.SharedPreferencesManager;
import com.example.mobileecommerce.retrofit.RetrofitClient;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    SharedPreferencesManager sharedPreferencesManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);
        TextView signuptxt = (TextView) findViewById(R.id.signup);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        sharedPreferencesManager = SharedPreferencesManager
                .getInstance(getSharedPreferences("jwt", MODE_PRIVATE));

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallLoginAPI(username.getText().toString().trim(), password.getText().toString().trim());
            }
        });

        signuptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void CallLoginAPI(String username, String password) {

        // Get data
        UserModel user = new UserModel(username, password);
        LoginAPI loginAPI = RetrofitForLogin.getRetrofitForLogin().create(LoginAPI.class);
        loginAPI.Login(user).enqueue(new Callback<ResponseDTO>() {

            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                if(response.isSuccessful()){
                    ResponseDTO responseDTO = response.body();
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    saveJWT(responseDTO.getMessage());
                    gotoHome(responseDTO.getMessage());
                } else{
                    Toast.makeText(LoginActivity.this, "Wrong When You Login!!!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {

            }
        });
    }

    private void gotoHome(String token) {
        String role = JwtService.getRoleFromToken(token);
        Log.e("ROLE", "là "+ role);
        Intent intent;
        if(role.equals("ROLE_ADMIN")){
            intent = new Intent(this, AdminPanelHomeActivity.class);
        }else {
            intent = new Intent(this, HomePageActivity.class);
        }
        startActivity(intent);
    }
    private void saveJWT(String token) {
        sharedPreferencesManager.saveJWT(token);
    }
}