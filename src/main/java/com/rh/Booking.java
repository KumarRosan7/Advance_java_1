package com.rh;

public class Booking {
    private int bookingId;
    private String passengerName;
    private int age;
    private int trainId;
    private int seats;

    public Booking() {
    }

    public Booking(int bookingId, String passengerName, int age, int trainId, int seats) {
        this.bookingId = bookingId;
        this.passengerName = passengerName;
        this.age = age;
        this.trainId = trainId;
        this.seats = seats;
    }

    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public String getPassengerName() {
        return passengerName;
    }
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getTrainId() {
        return trainId;
    }
    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }
    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }
}