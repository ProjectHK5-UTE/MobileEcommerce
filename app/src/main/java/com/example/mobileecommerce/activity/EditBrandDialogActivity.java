package com.example.mobileecommerce.activity;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.model.CategoriesListModellClass;

public class EditBrandDialogActivity extends Dialog implements View.OnClickListener {

    private TextView title;
    private EditText edtName;
    private Button btnSave;
    private Button btnCancel;

    private CategoriesListModellClass categoriesListModellClass;

    public EditBrandDialogActivity(Context context, CategoriesListModellClass categoriesListModellClass) {
        super(context);
        setContentView(R.layout.dialog_add_brand);

        this.categoriesListModellClass = categoriesListModellClass;

        title = findViewById(R.id.dialog_title);
        title.setText("Edit a Brand");
        edtName = findViewById(R.id.edtName_);
        btnSave = findViewById(R.id.btnSave_);
        btnCancel = findViewById(R.id.btnCancel_);

        edtName.setText(categoriesListModellClass.getTitle());

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave_:
                String name = edtName.getText().toString();
                // Lưu thông tin Thương hiệu vào cơ sở dữ liệu của bạn
                dismiss();
                break;

            case R.id.btnCancel_:
                dismiss();
                break;
        }
    }
}