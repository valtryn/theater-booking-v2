module com.theater.theaterbookingv2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.theater.theaterbookingv2 to javafx.fxml;
    exports com.theater.theaterbookingv2;
}