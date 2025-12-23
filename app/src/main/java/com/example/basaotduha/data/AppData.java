package com.example.basaotduha.data;

import com.example.basaotduha.model.Booking;
import com.example.basaotduha.model.Room;
import com.example.basaotduha.model.Service;

import java.util.ArrayList;

public class AppData {
    public static ArrayList<Booking> bookings = new ArrayList<>();
    public static ArrayList<Service> services = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();

    static {
        rooms.add(new Room("Маленький дом", true));
        rooms.add(new Room("Средний дом", true));
        rooms.add(new Room("Большой дом", true));
        rooms.add(new Room("Маленькая беседка", true));
        rooms.add(new Room("Средняя беседка", true));
        rooms.add(new Room("Большая беседка", true));
        rooms.add(new Room("Баня", true));
        rooms.add(new Room("VIP дом 1", true));
        rooms.add(new Room("VIP дом 2", true));
    }
}
