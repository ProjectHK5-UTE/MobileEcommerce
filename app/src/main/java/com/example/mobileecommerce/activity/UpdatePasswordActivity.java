package com.example.mobileecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.api.CustomerAPI;
import com.example.mobileecommerce.model.UserModel;
import com.example.mobileecommerce.retrofit.RetrofitClient;
import com.example.mobileecommerce.sharedpreferences.SharedPreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePasswordActivity extends AppCompatActivity {
    Button btnReset;
    Button btnChangePassword;
    EditText edtOldPassword;
    EditText edtNewPassword;
    EditText edtRepeatPassword;
    private String username;
    static android.content.SharedPreferences pres;

    SharedPreferencesManager SharedPreferences = SharedPreferencesManager.getInstance(pres);

    CustomerAPI customerAPI = RetrofitClient.getRetrofit().create(CustomerAPI.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_update_password);
        mapView();
        username = SharedPreferences.getUsername();
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPassword = edtNewPassword.getText().toString();
                String repeatPassword = edtRepeatPassword.getText().toString();
                String oldPassword = edtOldPassword.getText().toString();
                if(newPassword.length() < 6) {
                    Toast.makeText(getApplicationContext(), "New password must be longer than 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!newPassword.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*()-+={}\\[\\]|;:'\"<>,.?/~`]).+$")) {
                    Toast.makeText(getApplicationContext(), "Password must contain 3 uppercase, lowercase, and special characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!newPassword.equals(repeatPassword)) {
                    Toast.makeText(getApplicationContext(), "New password and repeat password do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                customerAPI.updatePassword(username, oldPassword, newPassword).enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if(response.isSuccessful()) {
                            UserModel userNew = response.body();
                            System.out.println(userNew);
                            Toast.makeText(getApplicationContext(), "update password successfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            System.out.println("Password entered is incorrect");
                            Toast.makeText(getApplicationContext(), "Password entered is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        System.out.println(t.getMessage());
                        System.out.println("Password entered is incorrect");
                        Toast.makeText(getApplicationContext(), "Password entered is incorrect", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtNewPassword.setText("");
                edtOldPassword.setText("");
                edtRepeatPassword.setText("");
            }
        });
    }

    private void mapView() {
        btnReset = findViewById(R.id.btn_reset_password);
        btnChangePassword = findViewById(R.id.btn_change_password);
        edtOldPassword = findViewById(R.id.edt_old_password);
        edtNewPassword = findViewById(R.id.edt_new_password);
        edtRepeatPassword = findViewById(R.id.edt_repeat_password);
    }
}