package VThread;

import model.Vehicle;
import model.VehicleType;
import service.ParkingLot;
import service.ParkingLotImp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class MainHelper {
    private int totalSpot;
    private ParkingLot parkingLot;
//    private BufferedReader sc;

    public MainHelper(int totalSpot) {
        this.totalSpot = totalSpot;
        parkingLot = new ParkingLotImp(totalSpot);
//        sc = new BufferedReader(new InputStreamReader(System.in));
    }

    public synchronized int parkVehicle() {
        try {
//            System.out.println("Enter vehicle number : ");
            int randomVehicleNumber = new Random().nextInt(1000) + 1000000;
            String vehicleNumber = Integer.toString(randomVehicleNumber);
            int randomTypeIndex = new Random().nextInt(3);
            VehicleType type = VehicleType.class.getEnumConstants()[randomTypeIndex];


            Vehicle vehicle = new Vehicle(vehicleNumber, type);
            int spotNumber = parkingLot.parkVehicle(vehicle);
            return spotNumber;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return 0;
        }
    }

    public synchronized void unParkVehicle(int spotNumber) {
        try {
//            System.out.println("Enter 1 for printing receipt and 2 for not printing receipt");
//            int flg = Integer.parseInt(sc.readLine());
            int flg = 0;
            boolean isUnParked = parkingLot.unParkVehicle(spotNumber, flg == 1);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void printFreeParkingSpotNumber() {
        System.out.println("There are total " + parkingLot.getAvailableSpots() + " Parking spots");
    }

    public void printParkingDetails() {
        parkingLot.displayLotStatus();
    }
}
