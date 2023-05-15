package com.example.mobileecommerce.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.api.CustomerAPI;
import com.example.mobileecommerce.api.ProvinceAPI;
import com.example.mobileecommerce.model.CustomerModel;
import com.example.mobileecommerce.model.Province;
import com.example.mobileecommerce.model.dto.ResponseDTO;
import com.example.mobileecommerce.model.dto.ResponseObject;
import com.example.mobileecommerce.retrofit.RetrofitClient;
import com.example.mobileecommerce.retrofit.RetrofitProvince;
import com.example.mobileecommerce.sharedpreferences.SharedPreferencesManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    TextView title;
    ImageView iv_back;

    TextView tvUsername;

    EditText edtFullname;
    EditText edtPhone;
    EditText edtAddress;

    Spinner spi_province;

    Spinner spi_district;

    Spinner spi_sub_district;

    ImageView imgAvatar;

    Button btnLoadImage;
    Button btnChangePassword;
    Button btnUpdateProfile;

    Button btnLogout;
    Context context;

    CustomerAPI customerAPI = RetrofitClient.getRetrofit().create(CustomerAPI.class);
    ProvinceAPI provinceAPI = RetrofitProvince.getRetrofit().create(ProvinceAPI.class);

    private int codeProvince, codeDistrict, codeSubDistrict;

    private CustomerModel customer;

    private ProgressDialog progressDialog;

    private String username;
    static SharedPreferences pres;
    SharedPreferencesManager SharedPreferences = SharedPreferencesManager.getInstance(pres);

    SharedPreferencesManager pres1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile);
        //
        this.title = (TextView) findViewById(R.id.title);
        this.iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");

        mapView();

        context = this;
        pres1 = SharedPreferencesManager
                .getInstance(getSharedPreferences("Username", MODE_PRIVATE));
        callAPIGetUserName();


//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override // android.view.View.OnClickListener
//            public void onClick(View view) {
//                ProfileActivity.this.finish();
//            }
//        });
        this.title.setText("My Profile");
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileActivity.this.startActivity(new Intent(ProfileActivity.this, UpdatePasswordActivity.class));
            }
        });
        btnLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileActivity.this.startActivity(new Intent(ProfileActivity.this, UploadImageActivity.class));
            }
        });

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeInfor();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeData();
                gotoLogin();
            }
        });
    }
    private void gotoLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void removeData() {
        SharedPreferences.removeJWT();
        SharedPreferences.removeEmail();
        //SharedPreferences.removeUsername();
    }
    private void callAPIGetUserName() {
        String email = SharedPreferences.getEmail();
        customerAPI.getUserName(email).enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                if(response.isSuccessful()){
                    username= response.body().getMessage();
                    pres1.saveUsername(username);
                    callAPI(username);
                    getAvatar(username);
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                Log.e("CALL API get Username","Fail");
            }
        });
    }


    private void mapView() {
        btnLoadImage = (Button) findViewById(R.id.btnLoadImage);
        btnChangePassword = (Button) findViewById(R.id.btnChangePwd);
        btnUpdateProfile = (Button) findViewById(R.id.btn_update_profile);
        tvUsername = findViewById(R.id.tv_username);
        edtFullname = findViewById(R.id.edt_fullname);
        edtAddress = findViewById(R.id.edt_address);
        edtPhone = findViewById(R.id.edt_sodienthoai);
        spi_province = findViewById(R.id.spi_province);
        spi_district = findViewById(R.id.spi_district);
        spi_sub_district = findViewById(R.id.spi_subdistrict);
        imgAvatar = findViewById(R.id.profileImage);
        btnLogout = findViewById(R.id.btn_logout_profile);
    }

    private void getProvince(Context context) {
        provinceAPI.getProvince().enqueue(new Callback<List<Province>>() {
            @Override
            public void onResponse(Call<List<Province>> call, Response<List<Province>> response) {
                List<Province> listProvince = response.body();
                ArrayAdapter<Province> adapter = new ArrayAdapter<Province>(context, android.R.layout.simple_spinner_item, listProvince);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spi_province.setAdapter(adapter);
                System.out.println(customer);
//                 Selected Spinner dua vao code province

                spi_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Province selectedProvinceDTO = (Province) adapterView.getItemAtPosition(i);
                        codeProvince = selectedProvinceDTO.getCode();
                        getDistrict(context, selectedProvinceDTO.getCode());
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                int codeProvinceValue = customer.getCodeProvince();
                // Selected vào Spinner dựa vào code province;

                int position = -1;
                for(int i = 0; i < listProvince.size(); i++) {
                    if(listProvince.get(i).getCode() == codeProvinceValue) {
                        position = i;
                        break;
                    }
                }
                if(position != -1) {
                    spi_province.setSelection(position);
                }
            }

            @Override
            public void onFailure(Call<List<Province>> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Hình ảnh không phù hợp", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private void getDistrict(Context context, Integer code) {
        provinceAPI.getDistricts(code).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject jsonObject = response.body();
                List<Province> listDistrict = new Gson().fromJson(jsonObject.get("districts"), new TypeToken<List<Province>>(){}.getType());
                ArrayAdapter<Province> adapter = new ArrayAdapter<Province>(context, android.R.layout.simple_spinner_item, listDistrict);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spi_district.setAdapter(adapter);
                spi_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Province selectedDistrict = (Province) adapterView.getItemAtPosition(i);
                        System.out.println(selectedDistrict.getCode() + " " + selectedDistrict.getName());
                        codeDistrict = selectedDistrict.getCode();
                        getSubDistrict(context, selectedDistrict.getCode());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                int codeDistrictValue = customer.getCodeDistrict();
                // Selected vào Spinner dựa vào code province;

                int position = -1;
                for(int i = 0; i < listDistrict.size(); i++) {
                    if(listDistrict.get(i).getCode() == codeDistrictValue) {
                        position = i;
                        break;
                    }
                }
                if(position != -1) {
                    spi_district.setSelection(position);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    private void getSubDistrict(Context context, Integer code) {
        provinceAPI.getSubDistricts(code).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject jsonObject = response.body();
                List<Province> listSubDistrict = new Gson().fromJson(jsonObject.get("wards"), new TypeToken<List<Province>>(){}.getType());
                ArrayAdapter<Province> adapter = new ArrayAdapter<Province>(context, android.R.layout.simple_spinner_item, listSubDistrict);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spi_sub_district.setAdapter(adapter);
                spi_sub_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Province selectedSubDistict = (Province) adapterView.getItemAtPosition(i);
                        codeSubDistrict = selectedSubDistict.getCode();
                        System.out.println(selectedSubDistict.getCode() + " " + selectedSubDistict.getName());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                int codeSubDistrictValue = customer.getCodeSubDistrict();
                int position = -1;
                for(int i = 0; i < listSubDistrict.size(); i++) {
                    if(listSubDistrict.get(i).getCode() == codeSubDistrictValue) {
                        position = i;
                        break;
                    }
                }
                if(position != -1) {
                    spi_sub_district.setSelection(position);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    private void callAPI(String username) {
        customerAPI.getCustomerInfor(username).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    String json = gson.toJson(response.body().getData());
                    customer = gson.fromJson(json, CustomerModel.class);
                    tvUsername.setText(customer.getUserName());
                    edtFullname.setText(customer.getFullname());
                    edtPhone.setText(customer.getPhonenumber());
                    edtAddress.setText(customer.getAddress());

                    getProvince(context);
                }
                else {
                    customer = new CustomerModel();
                    Toast.makeText(ProfileActivity.this, "Call API Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getAvatar(String username) {
        customerAPI.getImage(username).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    ResponseBody body = response.body();
                    InputStream inputStream = body.byteStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imgAvatar.setImageBitmap(bitmap);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Call API Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void changeInfor() {
        customer = new CustomerModel();
        customer.setAddress(edtAddress.getText().toString());
        customer.setUserName(tvUsername.getText().toString());
        customer.setFullname(edtFullname.getText().toString());
        customer.setPhonenumber(edtPhone.getText().toString());
        customer.setCodeDistrict(codeDistrict);
        customer.setCodeProvince(codeProvince);
        customer.setCodeSubDistrict(codeSubDistrict);
        customerAPI.updateCustomer(customer).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if(response.isSuccessful())
                {
                    System.out.println("B");
                    System.out.println("Cập nhật thông tin thành công");
                    Toast.makeText(ProfileActivity.this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                System.out.println(t.getMessage());
                System.out.println("A");
                System.out.println("Cập nhật thất bại");
                Toast.makeText(ProfileActivity.this, "Cập nhật thông tin thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}