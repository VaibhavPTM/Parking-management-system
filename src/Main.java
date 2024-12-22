import VThread.MainHelper;
import VThread.VehicleThread;

public class Main {
    public static void main(String[] args) {
        MainHelper helper = new MainHelper(5);
        while (true) {
            try {
                VehicleThread vehicleThread = new VehicleThread(helper);
                vehicleThread.start();
                Thread.sleep(2000);
            }
            catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}