// Room.java (Model Class)
package com.hostelbookingsystem.model;

public class Room {
    private String block;
    private int roomNumber;
    private int numberOfBeds;
    private String availability;

    public Room(String block, int roomNumber, int numberOfBeds, String availability) {
        this.block = block;
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
        this.availability = availability;
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

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
