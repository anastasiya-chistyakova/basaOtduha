package com.example.basaotduha;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basaotduha.data.AppData;
import com.example.basaotduha.model.Service;

public class ServiceEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_edit);

        EditText etName = findViewById(R.id.etServiceName);
        EditText etPrice = findViewById(R.id.etServicePrice);
        Button btnSave = findViewById(R.id.btnSaveService);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String priceText = etPrice.getText().toString().trim();

            if (name.isEmpty() || priceText.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }

            int price = Integer.parseInt(priceText);
            AppData.services.add(new Service(name, price));
            finish();
        });
    }
}
