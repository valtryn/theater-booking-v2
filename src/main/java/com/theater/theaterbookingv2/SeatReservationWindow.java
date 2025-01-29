package com.theater.theaterbookingv2;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.scene.control.ListView;

import java.io.IOException;

public class SeatReservationWindow {
    @FXML
    private Canvas seats;
    @FXML
    private Canvas markY;
    @FXML
    private Canvas markX;
    @FXML
    private Label title;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Label cost;
    @FXML
    private Button book;
    @FXML
    private TextField entry;
    @FXML
    private ComboBox<String> dateList;
    @FXML
    private ComboBox<String> timeList;
    @FXML
    private Label msgBox;

    private TheaterShow show;
    private int[] posx = new int[65];
    private int[] posy = new int[65];

    private int seatWidth = 25;
    private int seatHeight = 25;
    private Controller controller;

    public SeatReservationWindow(TheaterShow show, Controller controller) {
        this.show = show;
        this.controller = controller;
    }

    public void openWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("booking.fxml"));
        fxmlLoader.setController(this);

        javafx.stage.Stage stage = new javafx.stage.Stage();
        Scene scene = new Scene(fxmlLoader.load(), 800, 350);
        stage.setTitle("Seat Reservation");
        stage.setResizable(false);
        // NOTE: maybe disable this?
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    public void drawMarkY() {
        GraphicsContext gc = markY.getGraphicsContext2D();
        gc.setFill(Color.FLORALWHITE);  // Set text color
        gc.setFont(new Font("Arial", 16));  // Set font for the labels

        int baseY = 20;  // Starting y-coordinate for the labels
        int gap = 12;  // Gap between seats and labels
        char rowLabel = 'A';  // Start with "A" for the row labels

        for (int row = 0; row < 5; row++) {
            // Draw row label (A-E)
            gc.fillText(String.valueOf(rowLabel), 20, baseY + (row * (25 + gap)) + 12);
            rowLabel++;  // Increment to the next letter for each row
        }
    }

    public void drawMarkX() {
        GraphicsContext gc = markX.getGraphicsContext2D();

        // Draw labels for the columns (1-13)
        gc.setFill(Color.FLORALWHITE);  // Set text color
        gc.setFont(new Font("Arial", 15));  // Set font for the labels

        int baseX = 15;  // Starting x-coordinate for the labels
        int gap = 12;  // Gap between seats and labels

        for (int col = 0; col < 13; col++) {
            gc.fillText(String.valueOf(col + 1), baseX + (col * (25 + gap)) + 8, 15);
        }

    }

    public void drawSeats() {
        GraphicsContext gc = seats.getGraphicsContext2D();
        int gap = 12;
        int baseX = 15;
        int baseY = 15;

        int seatCount = 0;
        for (int row = 0; row < 5; row++) {
            int base = baseX;
            for (int i = 0; i < 13; i++) {
                posx[seatCount] = base;
                posy[seatCount] = baseY;
                if (show.seats[seatCount]) {
                    Painter.drawRectangle(gc, base, baseY, seatWidth, seatHeight, 1, Painter.GREEN, Painter.CRUST);
                } else {
                    Painter.drawRectangle(gc, base, baseY, seatWidth, seatHeight, 1, Painter.MANTLE, Painter.CRUST);
                }
                seatCount++;

                base += seatWidth + gap;
            }
            baseY += seatHeight + gap;
        }
    }

    public void editData() {
        title.setText(String.format("Title: %s", show.name));
    }

    public void editCost(int price) {
        if (price == -1) {
            cost.setText("Cost:");
        } else {
            cost.setText(String.format("Cost: %d", price));
        }
    }

    public static int getSeatIndex(String seat) {
        if (seat.length() < 2) return -1;
        char rowChar = Character.toUpperCase(seat.charAt(0));
        int colNum;

        try {
            colNum = Integer.parseInt(seat.substring(1));
        } catch (NumberFormatException e) {
            return -1;
        }

        if (rowChar < 'A' || rowChar > 'E' || colNum < 1 || colNum > 13) return -1;
        int rowIndex = rowChar - 'A';
        int colIndex = colNum - 1;
        return rowIndex * 13 + colIndex;
    }

    @FXML
    private void book() {
        GraphicsContext gc = seats.getGraphicsContext2D();
        String seatEntry = entry.getText();
        int seatIndex = getSeatIndex(seatEntry);
        if (seatIndex == -1) {
            msgBox.setText(String.format("Error: %s is not a valid seat.", seatEntry));
            msgBox.setStyle("-fx-text-fill: red;");
            return;
        }
        if (show.seats[seatIndex]) {
            msgBox.setText(String.format("Error: %s is already booked.", seatEntry));
            msgBox.setStyle("-fx-text-fill: red;");
            return;
        }
        show.seats[seatIndex] = true;
        Painter.drawRectangle(gc, posx[seatIndex], posy[seatIndex], seatWidth, seatHeight, 1, Painter.GREEN, Painter.CRUST);
        msgBox.setText(String.format("You have successfully booked %s.", seatEntry));
        msgBox.setStyle("-fx-text-fill: green;");

        Ticket ticket = new Ticket(show.name, dateList.getValue(), timeList.getValue(), seatEntry, show.seatPrice(seatIndex));
        controller.items.add(ticket.formatTicket());
        //  mainController.tableView.getItems().add(new Ticket(show.name, dateList.getValue(), timeList.getValue(), seatEntry, show.seatPrice(seatIndex)));

    }

    public void setDateTimeList() {
        for (int i = 0; i < 4; i++) {
            dateList.getItems().add(this.show.date[i]);
            timeList.getItems().add(this.show.time[i]);
        }
        dateList.setValue(this.show.date[0]);
        timeList.setValue(this.show.time[0]);
    }

    @FXML
    private void initialize() {
        drawSeats();
        drawMarkY();
        drawMarkX();
        editData();
        setDateTimeList();
        entry.textProperty().addListener((observable, oldValue, newValue) -> {
            msgBox.setText("");
            int seatIndex = getSeatIndex(newValue);
            if (seatIndex == -1) {
                editCost(-1);
                return;
            }
            int price = show.seatPrice(seatIndex);
            editCost(price);
        });
    }
}
