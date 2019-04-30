package com.dmall.order.model;

public class OrderEvent {

    public enum Values{
        CREATED,
        PAID,
        CANCEL;
    }


    private Long id;

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    private Long ticketId;

    public String getTicketName() {
        return ticketName;
    }

    private String ticketName;

    public String getName() {
        return name;
    }

}

