package service;

import model.Vehicle;

public interface ParkingLot {
    int parkVehicle(Vehicle vehicle);
    boolean unParkVehicle(int spotID, boolean genReceipt);
    int getAvailableSpots();
    void displayLotStatus();
}
