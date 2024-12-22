package service;

import model.ParkingSpot;
import model.Vehicle;
import model.VehicleType;

import java.time.LocalDateTime;
import java.util.*;

public class ParkingLotImp implements ParkingLot{

    private List<ParkingSpot> parkingSpotList;
    private Set<Integer> availableParkingPost;
    private int totalSpots;

    public ParkingLotImp(int totalSpots) {
        this.totalSpots = totalSpots;
        parkingSpotList = Collections.synchronizedList(new ArrayList<ParkingSpot>());
        availableParkingPost = Collections.synchronizedSet(new HashSet<Integer>());
        initializeParkingLot(totalSpots);
    }

    private void initializeParkingLot(int totalSpots) {
        for(int i = 1; i <= totalSpots; i++) {
            availableParkingPost.add(i-1);
            parkingSpotList.add(new ParkingSpot(i, null, false));
        }
    }

    @Override
    public int parkVehicle(Vehicle vehicle) {
        if(availableParkingPost.isEmpty()) {
            System.out.println("Sorry, There is not any empty parking spot");
            return 0;
        }
        else {
            Integer freePost = availableParkingPost.iterator().next();
            availableParkingPost.remove(freePost);
            parkingSpotList.get(freePost).occupieVehical(vehicle);
            freePost = freePost + 1;
            System.out.println("Vehicle with vehicle number : " + vehicle.getVehicleNumber() + " parked at spot: " + freePost);
            return freePost;
        }
    }

    @Override
    public boolean unParkVehicle(int spotID, boolean genReceipt) {
        if (spotID <= 0 || spotID > totalSpots) {
            System.out.println("Please Enter valid spotID " + spotID);
            return false;
        }
        if(!availableParkingPost.contains(spotID - 1)) {
            availableParkingPost.add(spotID - 1);
            Vehicle parkedVehicle = parkingSpotList.get(spotID-1).getCurrentVehicle();
            if(genReceipt) {
                System.out.println(parkingSpotList.get(spotID-1).getTicket().calculateFee(LocalDateTime.now(), parkedVehicle.getType()));
            }
            Vehicle removedVehicle = parkingSpotList.get(spotID - 1).unOccupiVehical();
            System.out.println("Vehicle with number " + removedVehicle.getVehicleNumber() + " has left the spot.");
            return true;
        }
        else {
            System.out.println("Spot " + spotID + " is already empty.");
            return false;
        }
    }

    @Override
    public int getAvailableSpots() {
        return availableParkingPost.size();
    }

    @Override
    public void displayLotStatus() {
        System.out.println("Parking Lot Status:");
        for(ParkingSpot parkingSpot : parkingSpotList) {
            System.out.println("Spot " + parkingSpot.getSpotID() + ": " + (parkingSpot.isOccupied() ? "Occupied" : "Available"));
        }
    }
}
