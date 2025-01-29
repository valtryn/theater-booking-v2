package com.theater.theaterbookingv2;

import java.util.ArrayList;
import java.util.List;

public class TheaterShow {
    public String name;
    public String description;
    public int cost;
    public String[] date;
    public String[] time;
    public boolean[] seats;

    public TheaterShow(String name, String description, int cost, String date, String time) {
        this.name          = name;
        this.description   = description;
        this.cost          = cost;
        this.seats         = new boolean[65];
        this.date          = date.split("\\|");;
        this.time          = time.split("\\|");;;
    }

    public int seatPrice(int index) {
        if (index < 0 || index >= 65) {
            return -1;
        }

        int row = index / 13;
        return this.cost + (200 * (4 - row));
    }

    public boolean bookSeat(int index) {
        if (index < 0 || index >= 65) {
            return false;
        } else {
            seats[index] = true;
            return true;
        }
    }

    public void cancelBooking(int index) {
        if (index >= 0 && index < 65) { // Correct condition
            seats[index] = false;
        }
    }

    public List<Integer> checkAvailableSeats() {
        List<Integer> availableSeats = new ArrayList<>();
        for (int i = 0; i < 65; i++) {
            if (seats[i] == false) {
                availableSeats.add(i);
            }
        }
        return availableSeats;
    };
}
