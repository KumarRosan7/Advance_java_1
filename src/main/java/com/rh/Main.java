package com.rh;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TrainDAO trainDAO = new TrainDAO();
        BookingDAO bookingDAO = new BookingDAO();

        while (true) {
            System.out.println("   --------------RAILWAY TICKET BOOKING SYSTEM--------------");
            System.out.println("1. Add Train");
            System.out.println("2. View All Trains");
            System.out.println("3. Search Train");
            System.out.println("4. Book Ticket");
            System.out.println("5. View Bookings");
            System.out.println("6. Cancel Ticket");
            System.out.println("7. Exit");
            System.out.print("Enter Your Choice : ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:

                    sc.nextLine();

                    Train train = new Train();

                    System.out.print("Train Name : ");
                    train.setTrainName(sc.nextLine());

                    System.out.print("Source : ");
                    train.setSource(sc.nextLine());

                    System.out.print("Destination : ");
                    train.setDestination(sc.nextLine());

                    System.out.print("Total Seats : ");
                    int seats = sc.nextInt();

                    train.setTotalSeats(seats);
                    train.setAvailableSeats(seats);

                    trainDAO.addTrain(train);

                    break;
                case 2:
                    trainDAO.viewTrains();
                    break;
                case 3:
                    System.out.print("Enter Train ID : ");
                    int id = sc.nextInt();
                    trainDAO.searchTrain(id);
                    break;
                case 4:
                    sc.nextLine();
                    System.out.print("Passenger Name : ");
                    String name = sc.nextLine();

                    System.out.print("Age : ");
                    int age = sc.nextInt();

                    System.out.print("Train ID : ");
                    int trainId = sc.nextInt();

                    System.out.print("Number of Seats : ");
                    int bookSeats = sc.nextInt();

                    bookingDAO.bookTicket(name, age, trainId, bookSeats);
                    break;
                case 5:
                    bookingDAO.viewBookings();
                    break;
                case 6:
                    System.out.print("Enter Booking ID : ");
                    int bookingId = sc.nextInt();
                    bookingDAO.cancelTicket(bookingId);
                    break;
                case 7:
                    System.out.println("Thank You for Using Railway Ticket Booking System.");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice!");

            }

        }

    }
}