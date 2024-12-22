package VThread;


import model.Vehicle;

import java.util.Random;

public class VehicleThread extends Thread {
    MainHelper helper;
    public VehicleThread(MainHelper helper) {
        this.helper = helper;
    }

    @Override
    public void run() {
        try {
            int spote = helper.parkVehicle();
            if(spote == 0) {
                return;
            }
            int randomExitTime = new Random().nextInt(6) + 8;
            Thread.sleep(randomExitTime * 1000);
            helper.unParkVehicle(spote);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
