package com.rh;

import java.sql.*;

public class TrainDAO {

    Connection con = DBConnection.getConnection();

    // Add Train
    public void addTrain(Train train) {

        String sql = "INSERT INTO trains(train_name,source,destination,total_seats,available_seats) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, train.getTrainName());
            ps.setString(2, train.getSource());
            ps.setString(3, train.getDestination());
            ps.setInt(4, train.getTotalSeats());
            ps.setInt(5, train.getAvailableSeats());

            ps.executeUpdate();
            System.out.println("Train Added Successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View All Trains
    public void viewTrains() {

        String sql = "SELECT * FROM trains";

        try {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Train ID : " + rs.getInt("train_id"));
                System.out.println("Train Name : " + rs.getString("train_name"));
                System.out.println("Source : " + rs.getString("source"));
                System.out.println("Destination : " + rs.getString("destination"));
                System.out.println("Available Seats : " + rs.getInt("available_seats"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Search Train
    public void searchTrain(int id) {

        String sql = "SELECT * FROM trains WHERE train_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                System.out.println("\nTrain Found");

                System.out.println("Train Name : " + rs.getString("train_name"));
                System.out.println("Source : " + rs.getString("source"));
                System.out.println("Destination : " + rs.getString("destination"));
                System.out.println("Available Seats : " + rs.getInt("available_seats"));
            } else {
                System.out.println("Train Not Found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}