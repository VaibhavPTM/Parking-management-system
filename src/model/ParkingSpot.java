package model;

public class ParkingSpot {
    private int spotID;
    private boolean isOccupied;
    private Vehicle currentVehicle;
    private Ticket ticket;

    public ParkingSpot(int spotID, Vehicle currentVehicle, boolean isOccupied) {
        this.spotID = spotID;
        this.currentVehicle = currentVehicle;
        this.isOccupied = isOccupied;
    }

    public void occupieVehical(Vehicle vehicle) {
        this.currentVehicle = vehicle;
        this.isOccupied = true;
        ticket = new Ticket(currentVehicle.getVehicleNumber(), spotID);
    }

    public Vehicle unOccupiVehical() {
        Vehicle vehicleToBeRemoved = currentVehicle;
        this.currentVehicle = null;
        this.isOccupied = false;
        ticket = null;
        return vehicleToBeRemoved;
    }

    public int getSpotID() {
        return spotID;
    }

    public void setSpotID(int spotID) {
        this.spotID = spotID;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    public void setCurrentVehicle(Vehicle currentVehicle) {
        this.currentVehicle = currentVehicle;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Ticket getTicket() {
        return ticket;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "spotID=" + spotID +
                ", isOccupied=" + isOccupied +
                ", currentVehicle=" + currentVehicle +
                '}';
    }
}
