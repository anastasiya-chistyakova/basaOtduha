package com.example.basaotduha;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.basaotduha.data.AppData;
import com.example.basaotduha.model.Booking;
import java.util.ArrayList;

public class BookingListActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);

        ListView listView = findViewById(R.id.listBookings);
        Button btnAdd = findViewById(R.id.btnAddBooking);

        updateList();

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(v ->
                startActivity(new Intent(this, BookingEditActivity.class)));

        listView.setOnItemLongClickListener((a, b, pos, id) -> {
            AppData.bookings.remove(pos);
            updateList();
            adapter.notifyDataSetChanged();
            return true;
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, BookingEditActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        });

    }

    void updateList() {
        list.clear();
        for (Booking b : AppData.bookings) {
            list.add(b.clientName + " | " + b.room + " | " + b.date);
        }
    }
}
