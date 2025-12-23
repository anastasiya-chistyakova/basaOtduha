package com.example.basaotduha;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBookings = findViewById(R.id.btnBookings);
        Button btnServices = findViewById(R.id.btnServices);
        Button btnRooms = findViewById(R.id.btnRooms);

        btnBookings.setOnClickListener(v ->
                startActivity(new Intent(this, BookingListActivity.class)));

        btnServices.setOnClickListener(v ->
                startActivity(new Intent(this, ServiceListActivity.class)));

        btnRooms.setOnClickListener(v ->
                startActivity(new Intent(this, RoomStatusActivity.class)));
    }
}
