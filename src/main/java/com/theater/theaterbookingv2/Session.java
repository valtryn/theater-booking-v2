package com.theater.theaterbookingv2;

public class Session {
    public TheaterShow[] shows = new TheaterShow[5];

    public Session() {
        shows[0] = new TheaterShow("5 Centimeters Per Second", "NONE", 1000, "01/29/2025|02/01/2025|02/04/2025|02/05/2025", "2:00 PM|5:00 PM|8:00 PM|11:00 PM");
        shows[1] = new TheaterShow("Perfect Blue", "NONE", 1500, "01/30/2025|02/01/2025|02/02/2025|02/06/2025", "3:00 PM|4:30 PM|5:00 PM|6:30 PM");
        shows[2] = new TheaterShow("Look Back", "NONE", 2200, "02/29/2025|03/01/2025|03/05/2025|03/08/2025", "4:00 PM|5:00 PM|8:00 PM|9:00 PM");
        shows[3] = new TheaterShow("Maquia", "NONE", 2000, "01/29/2025|02/01/2025|02/03/2025|02/06/2025", "3:00 PM|5:00 PM|7:00 PM|9:00 PM");
        shows[4] = new TheaterShow("Paprika", "NONE", 1900, "01/28/2025|02/01/2025|02/02/2025|02/05/2025", "1:00 PM|3:00 PM|5:00 PM|7:00 PM");
    }
}
