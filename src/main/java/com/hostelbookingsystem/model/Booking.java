// Booking.java (Model Class)
package com.hostelbookingsystem.model;

import java.sql.Date;

public class Booking {
    private String username;
    private String block;
    private int roomNumber;
    private Date bookingDate;
    private String payment;

    public Booking(String username, String block, int roomNumber, Date bookingDate, String payment) {
        this.username = username;
        this.block = block;
        this.roomNumber = roomNumber;
        this.bookingDate = bookingDate;
        this.payment = payment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
