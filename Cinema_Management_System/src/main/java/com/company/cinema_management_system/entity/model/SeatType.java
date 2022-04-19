package com.company.cinema_management_system.entity.model;

public enum SeatType {
    FREE("Green","This seat is available to book"),
    RESERVED("Blue","This seat is reserved"),
    BOUGHT("Red","This seat is already bought"),
    NOT_SERVED("Yellow","This seat booked but did not served yet");
    private String colour;
    private String description;
    SeatType(String colour, String description) {
        this.colour = colour;
        this.description = description;
    }
}
