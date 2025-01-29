package com.theater.theaterbookingv2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private ListView<String> listView;

    public ObservableList<String> items = FXCollections.observableArrayList();
    private Session session = new Session();

    @FXML
    public void initialize() {
        listView.setItems(items);
        items.add("Date                          |                  Time                |               Price              |              Seat             |               Title              ");
        items.add("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        listView.setOnMouseClicked(event -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 2) {
                listView.setOnKeyPressed(keyEvent -> {
                    if (keyEvent.getCode().toString().equals("D")) {
                        String[] data = items.get(selectedIndex).split("\\|");
                        for (int i = 0; i < data.length; i++) {
                            data[i] = data[i].trim();
                        }
                        String title = data[4]; // Get the title from the correct index
                        for (TheaterShow show : session.shows) {
                            if (show.name.equalsIgnoreCase(title)) {
                                int index = SeatReservationWindow.getSeatIndex(data[3]);
                                if (index != -1) {
                                    show.cancelBooking(index);
                                }
                                break;
                            }
                        }
                        items.remove(selectedIndex);
                    }
                });
            }
        });

    }

    private void openReservationWindow(int showIndex) {
        if (showIndex >= 0 && showIndex < session.shows.length) {
            try {
                SeatReservationWindow stage = new SeatReservationWindow(session.shows[showIndex], this);
                stage.openWindow();
                System.out.println("New window opened");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid show index: " + showIndex);
        }
    }

    // Button event methods
    public void button1(ActionEvent event) {
        openReservationWindow(0);
    }

    public void button2(ActionEvent event) {
        openReservationWindow(1);
    }

    public void button3(ActionEvent event) {
        openReservationWindow(2);
    }

    public void button4(ActionEvent event) {
        openReservationWindow(3);
    }

    public void button5(ActionEvent event) {
        openReservationWindow(4);
    }
}
