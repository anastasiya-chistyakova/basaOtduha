package com.example.basaotduha;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basaotduha.data.AppData;
import com.example.basaotduha.model.Booking;
import com.example.basaotduha.model.Service;

import java.util.ArrayList;

public class BookingEditActivity extends AppCompatActivity {

    private EditText etClient, etRoom, etDate, etTime;
    private EditText etHourPrice, etDuration;
    private TextView tvRoomPrice, tvServicesPrice, tvTotalPrice;
    private ListView listServices;
    private Button btnAddService, btnSave;

    private Booking booking;
    private int position = -1;

    private ArrayAdapter<String> serviceAdapter;
    private ArrayList<String> serviceNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_edit);

        // ===== Инициализация =====
        etClient = findViewById(R.id.etClient);
        etRoom = findViewById(R.id.etRoom);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);

        etHourPrice = findViewById(R.id.etHourPrice);
        etDuration = findViewById(R.id.etDuration);

        tvRoomPrice = findViewById(R.id.tvRoomPrice);
        tvServicesPrice = findViewById(R.id.tvServicesPrice);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);

        listServices = findViewById(R.id.listBookingServices);
        btnAddService = findViewById(R.id.btnAddServiceToBooking);
        btnSave = findViewById(R.id.btnSaveBooking);

        // ===== Определяем режим (создание / редактирование) =====
        position = getIntent().getIntExtra("position", -1);

        if (position != -1) {
            booking = AppData.bookings.get(position);

            etClient.setText(booking.clientName);
            etRoom.setText(booking.room);
            etDate.setText(booking.date);
            etTime.setText(booking.time);
            etHourPrice.setText(String.valueOf(booking.hourPrice));
            etDuration.setText(String.valueOf(booking.durationHours));

        } else {
            booking = new Booking("", "", "", "");
        }

        // ===== Список услуг =====
        serviceAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                serviceNames
        );
        listServices.setAdapter(serviceAdapter);
        updateServiceList();

        // ===== Добавление услуги =====
        btnAddService.setOnClickListener(v -> showServiceDialog());

        // ===== Удаление услуги =====
        listServices.setOnItemLongClickListener((parent, view, pos, id) -> {
            booking.services.remove(pos);
            updateServiceList();
            return true;
        });

        // ===== Сохранение брони =====
        btnSave.setOnClickListener(v -> {
            if (etClient.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Введите имя клиента", Toast.LENGTH_SHORT).show();
                return;
            }

            booking.clientName = etClient.getText().toString();
            booking.room = etRoom.getText().toString();
            booking.date = etDate.getText().toString();
            booking.time = etTime.getText().toString();

            booking.hourPrice = getInt(etHourPrice);
            booking.durationHours = getInt(etDuration);

            if (position == -1) {
                AppData.bookings.add(booking);
            }

            finish();
        });

        updatePrices();
    }

    // ===== Обновление списка услуг =====
    private void updateServiceList() {
        serviceNames.clear();
        for (Service s : booking.services) {
            serviceNames.add(s.name + " — " + s.price + " ₽");
        }
        serviceAdapter.notifyDataSetChanged();
        updatePrices();
    }

    // ===== Диалог выбора услуги =====
    private void showServiceDialog() {
        if (AppData.services.isEmpty()) {
            Toast.makeText(this, "Список услуг пуст", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] services = new String[AppData.services.size()];
        for (int i = 0; i < AppData.services.size(); i++) {
            services[i] = AppData.services.get(i).name +
                    " (" + AppData.services.get(i).price + " ₽)";
        }

        new AlertDialog.Builder(this)
                .setTitle("Добавить услугу")
                .setItems(services, (dialog, which) -> {
                    booking.services.add(AppData.services.get(which));
                    updateServiceList();
                })
                .show();
    }

    // ===== Обновление стоимости =====
    private void updatePrices() {
        booking.hourPrice = getInt(etHourPrice);
        booking.durationHours = getInt(etDuration);

        tvRoomPrice.setText("Аренда: " + booking.getRoomPrice() + " ₽");
        tvServicesPrice.setText("Услуги: " + booking.getServicesPrice() + " ₽");
        tvTotalPrice.setText("Итого: " + booking.getTotalPrice() + " ₽");
    }

    // ===== Безопасное получение int =====
    private int getInt(EditText et) {
        try {
            return Integer.parseInt(et.getText().toString());
        } catch (Exception e) {
            return 0;
        }
    }
}