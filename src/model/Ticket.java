package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private int ticketId;
    private static int counter = 0;
    private String vehicleNumber;
    private int spotID;
    LocalDateTime entryTime;
    LocalDateTime exitTime;
    double parkingFee;

    public Ticket(String vehicleNumber, int spotID) {
        ticketId = counter;
        counter++;
        entryTime = LocalDateTime.now();
        this.vehicleNumber = vehicleNumber;
        this.spotID = spotID;
    }

    public double calculateFee(LocalDateTime exitTime, VehicleType type) {
        this.exitTime = exitTime;
        int initCost = 0;
        if(type.equals(VehicleType.BIKE)) {
            initCost = 10;
        }
        else if(type.equals(VehicleType.CAR)) {
            initCost = 50;
        }
        else if(type.equals(VehicleType.TRUCK)) {
            initCost = 100;
        }
        Duration duration = Duration.between(entryTime, exitTime);
        long hours = duration.getSeconds();
        parkingFee = hours * initCost;
        return parkingFee;
    }

    public String generateReceipt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Parking Receipt\n" +
                "-------------------\n" +
                "Ticket ID: " + ticketId + "\n" +
                "Vehicle Number: " + vehicleNumber + "\n" +
                "Spot ID: " + spotID + "\n" +
                "Entry Time: " + (entryTime != null ? entryTime.format(formatter) : "N/A") + "\n" +
                "Exit Time: " + (exitTime != null ? exitTime.format(formatter) : "N/A") + "\n" +
                "Parking Fee: ₹" + parkingFee + "\n" +
                "-------------------";
    }

}
