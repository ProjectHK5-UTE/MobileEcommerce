package com.example.mobileecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mobileecommerce.R;

public class AddBrandDialogActivity extends Dialog implements View.OnClickListener {

    private TextView title;
    private EditText edtName;
    private EditText edtImageURL;
    private Button btnSave;
    private Button btnCancel;

    public AddBrandDialogActivity(Context context) {
        super(context);
        setContentView(R.layout.dialog_add_brand);

        title = findViewById(R.id.dialog_title);
        title.setText("Add a new Brand");
        edtName = findViewById(R.id.edtName_);
        edtImageURL = findViewById(R.id.edtImageURL_);

        btnSave = findViewById(R.id.btnSave_);
        btnCancel = findViewById(R.id.btnCancel_);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave_:
                String name = edtName.getText().toString();
                String imageURL = edtImageURL.getText().toString();
                // Lưu thông tin Thương hiệu vào cơ sở dữ liệu của bạn
                dismiss();
                break;

            case R.id.btnCancel_:
                dismiss();
                break;
        }
    }
}