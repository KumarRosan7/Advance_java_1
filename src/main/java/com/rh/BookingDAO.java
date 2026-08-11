package com.rh;

import java.sql.*;

public class BookingDAO {
    Connection con = DBConnection.getConnection();
    // Book Ticket
    public void bookTicket(String passengerName, int age, int trainId, int seats) {

        try {
            String checkTrain = "SELECT available_seats FROM trains WHERE train_id=?";
            PreparedStatement ps1 = con.prepareStatement(checkTrain);
            ps1.setInt(1, trainId);

            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                int available = rs.getInt("available_seats");
                if (available >= seats) {

                    String book = "INSERT INTO bookings(passenger_name,age,train_id,seats) VALUES(?,?,?,?)";
                    PreparedStatement ps2 = con.prepareStatement(book);

                    ps2.setString(1, passengerName);
                    ps2.setInt(2, age);
                    ps2.setInt(3, trainId);
                    ps2.setInt(4, seats);

                    ps2.executeUpdate();

                    String update = "UPDATE trains SET available_seats=available_seats-? WHERE train_id=?";
                    PreparedStatement ps3 = con.prepareStatement(update);

                    ps3.setInt(1, seats);
                    ps3.setInt(2, trainId);

                    ps3.executeUpdate();

                    System.out.println("Ticket Booked Successfully.");

                } else {

                    System.out.println("Seats Not Available.");

                }

            } else {

                System.out.println("Train Not Found.");

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // View Bookings
    public void viewBookings() {

        String sql = "SELECT b.booking_id,b.passenger_name,b.age,"
                + "t.train_name,b.seats,b.booking_date "
                + "FROM bookings b JOIN trains t "
                + "ON b.train_id=t.train_id";

        try {

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n========== BOOKINGS ==========");

            while (rs.next()) {

                System.out.println("Booking ID : " + rs.getInt("booking_id"));
                System.out.println("Passenger : " + rs.getString("passenger_name"));
                System.out.println("Age : " + rs.getInt("age"));
                System.out.println("Train : " + rs.getString("train_name"));
                System.out.println("Seats : " + rs.getInt("seats"));
                System.out.println("Date : " + rs.getTimestamp("booking_date"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Cancel Ticket
    public void cancelTicket(int bookingId) {

        try {

            String getBooking = "SELECT train_id,seats FROM bookings WHERE booking_id=?";
            PreparedStatement ps1 = con.prepareStatement(getBooking);
            ps1.setInt(1, bookingId);

            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {

                int trainId = rs.getInt("train_id");
                int seats = rs.getInt("seats");

                PreparedStatement ps2 = con.prepareStatement(
                        "DELETE FROM bookings WHERE booking_id=?");
                ps2.setInt(1, bookingId);
                ps2.executeUpdate();
                PreparedStatement ps3 = con.prepareStatement(
                        "UPDATE trains SET available_seats=available_seats+? WHERE train_id=?");
                ps3.setInt(1, seats);
                ps3.setInt(2, trainId);

                ps3.executeUpdate();

                System.out.println("Ticket Cancelled Successfully.");

            } else {
                System.out.println("Booking Not Found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}