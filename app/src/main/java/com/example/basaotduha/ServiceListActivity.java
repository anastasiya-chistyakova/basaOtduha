package com.example.basaotduha;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basaotduha.data.AppData;
import com.example.basaotduha.model.Service;

import java.util.ArrayList;

public class ServiceListActivity extends AppCompatActivity {

    private ArrayList<String> serviceList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        ListView listView = findViewById(R.id.listServices);
        Button btnAdd = findViewById(R.id.btnAddService);

        serviceList = new ArrayList<>();
        updateList();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                serviceList
        );
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(v ->
                startActivity(new Intent(this, ServiceEditActivity.class)));

        // Удаление по долгому нажатию
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            AppData.services.remove(position);
            updateList();
            adapter.notifyDataSetChanged();
            return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
        adapter.notifyDataSetChanged();
    }

    private void updateList() {
        serviceList.clear();
        for (Service service : AppData.services) {
            serviceList.add(service.name + " — " + service.price + " ₽");
        }
    }
}
