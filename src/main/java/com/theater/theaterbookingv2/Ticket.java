package com.theater.theaterbookingv2;

public class Ticket {
    private String title;
    private String date;
    private String time;
    private String seat;
    private int cost;

    // Constructor
    public Ticket(String title, String date, String time, String seat, int cost) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.seat = seat;
        this.cost = cost;
    }

    // Function to concatenate and pad the ticket data
    public String formatTicket() {
        return String.format("%s               |               %s              |               %d              |               %s              |               %s              ", date,time,cost,seat,title);
    }
}
