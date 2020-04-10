package parkinglot;

import exception.ParkingLotException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parkingstrategy.*;

import java.util.ArrayList;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    ParkingLotOwner owner = null;
    private Vehicle vehicle;

    @Before
    public void setUp() throws Exception {
        vehicle = new Vehicle();
        owner = new ParkingLotOwner();
    }

    @Test
    public void givenMultipleParkingLotsWithCars_IfWhiteCarFound_ShouldReturnVehicle() throws ParkingLotException {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(2, 4);
        parkingLotSystem.registerParkingLotObserver(owner);
        parkingLotSystem.park(vehicle, new NormalParkingStrategy());
        try {
            Vehicle vehicle2 = new Vehicle(Vehicle.VehicleColor.OTHER);
            parkingLotSystem.park(vehicle2, new HandicapParkingStrategy());

            Vehicle vehicle3 = new Vehicle(Vehicle.VehicleColor.WHITE);
            parkingLotSystem.park(vehicle3, new LargeVehicleParkingStrategy());

            Vehicle vehicle4 = new Vehicle(Vehicle.VehicleColor.OTHER);
            parkingLotSystem.park(vehicle4, new LargeVehicleParkingStrategy());

            ArrayList<ParkingSlot> listOfVehicles = parkingLotSystem.findCarsWithColor(Vehicle.VehicleColor.WHITE);
            Assert.assertEquals(vehicle, listOfVehicles.get(0).vehicle);
            Assert.assertEquals(vehicle3, listOfVehicles.get(1).vehicle);
        } catch (ParkingLotException |NullPointerException e) {
            e.printStackTrace();
        }

    }
}


