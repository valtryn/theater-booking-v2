package com.theater.theaterbookingv2;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Painter {
    public static final Color CRUST  = Color.web("#0C0B0B");       // Darkest background
    public static final Color MANTLE = Color.web("#232222");      // Slightly lighter background
    public static final Color TEXT   = Color.web("#FFFFFF");        // White text color
    public static final Color GREEN  = Color.web("#6DFF65");       // Green highlight
    public static final Color RED    = Color.web("#CC0000");

    public static void drawRectangle(GraphicsContext canvas, int posx, int posy, int width, int height, int borderWidth, Color fill, Color border) {
        canvas.setStroke(border);
        canvas.setFill(fill);
        canvas.fillRect(posx, posy, width, height);
        canvas.setLineWidth(borderWidth);
        canvas.strokeRect(posx, posy, width, height);
    }
}
