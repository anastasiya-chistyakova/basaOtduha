package com.example.basaotduha;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basaotduha.data.AppData;
import com.example.basaotduha.model.Room;

import java.util.ArrayList;

public class RoomStatusActivity extends AppCompatActivity {

    private ArrayList<String> roomList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_status);

        ListView listView = findViewById(R.id.listRooms);

        roomList = new ArrayList<>();
        updateList();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                roomList
        );
        listView.setAdapter(adapter);

        // Переключение статуса по нажатию
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Room room = AppData.rooms.get(position);
            room.free = !room.free;
            updateList();
            adapter.notifyDataSetChanged();
        });
    }

    private void updateList() {
        roomList.clear();
        for (Room room : AppData.rooms) {
            roomList.add(
                    room.name + " — " +
                            (room.free ? "свободно" : "занято")
            );
        }
    }
}
