package parkinglot;

import dtoclasses.VehicleDTO;
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

    @Test
    public void givenMultipleParkingLotsWithCars_IfFoundBlueToyota_ShouldReturnItsInformation() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(2, 4);
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle, new NormalParkingStrategy());

            Vehicle vehicle2 = new Vehicle("UP44 S007",Vehicle.VehicleColor.BLUE,Vehicle.VehicleType.TOYOTA);
            parkingLotSystem.park(vehicle2, new HandicapParkingStrategy());

            Vehicle vehicle3 = new Vehicle("UP44 U007", Vehicle.VehicleColor.OTHER, Vehicle.VehicleType.TOYOTA);
            parkingLotSystem.park(vehicle3, new NormalParkingStrategy());

            Vehicle vehicle4 = new Vehicle("UP44 B007", Vehicle.VehicleColor.BLUE, Vehicle.VehicleType.TOYOTA);
            parkingLotSystem.park(vehicle4, new NormalParkingStrategy());

            ArrayList<VehicleDTO> listOfVehicles = parkingLotSystem.findCarsWithColor(Vehicle.VehicleColor.BLUE, Vehicle.VehicleType.TOYOTA);
            Assert.assertEquals(vehicle2.vehicleType,listOfVehicles.get(0).vehicleType);
            Assert.assertEquals(vehicle4.vehicleType,listOfVehicles.get(1).vehicleType);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}


