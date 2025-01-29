package com.theater.theaterbookingv2;

import javafx.event.ActionEvent;

import java.io.IOException;

public class Controller {

    public Session session = new Session();
    public void button1(ActionEvent event) {
        SeatReservationWindow stage = new SeatReservationWindow(session.shows[0]);
        try {
            stage.openWindow();
            System.out.println("New window opened");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void button2(ActionEvent event) {
        SeatReservationWindow stage = new SeatReservationWindow(session.shows[1]);
        try {
            stage.openWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void button3(ActionEvent event) {
        SeatReservationWindow stage = new SeatReservationWindow(session.shows[2]);
        try {
            stage.openWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void button4(ActionEvent event) {
        SeatReservationWindow stage = new SeatReservationWindow(session.shows[3]);
        try {
            stage.openWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void button5(ActionEvent event) {
        SeatReservationWindow stage = new SeatReservationWindow(session.shows[4]);
        try {
            stage.openWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
