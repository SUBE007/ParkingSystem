package parkinglot;

import dtoclasses.VehicleDTO;
import exception.ParkingLotException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parkingstrategy.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    ParkingLotOwner owner = null;
    private Vehicle vehicle;

    @Before
    public void setUp() throws Exception {
        vehicle = new Vehicle();
        owner = new ParkingLotOwner();
    }

  /*  @Test
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

    @Test
    public void givenCars_WhenPark_ItShouldAbleToFindBMWCars() throws ParkingLotException {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 4);
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle, new NormalParkingStrategy());
            Vehicle vehicle2 = new Vehicle("UP44 S007", Vehicle.VehicleColor.BLUE, Vehicle.VehicleType.BMW);
            parkingLotSystem.park(vehicle2, new HandicapParkingStrategy());
            Vehicle vehicle3 = new Vehicle("UP44 U007", Vehicle.VehicleColor.OTHER, Vehicle.VehicleType.TOYOTA);
            parkingLotSystem.park(vehicle3, new NormalParkingStrategy());
            Vehicle vehicle4 = new Vehicle("UP44 B007", Vehicle.VehicleColor.WHITE, Vehicle.VehicleType.TOYOTA);
            parkingLotSystem.park(vehicle4, new NormalParkingStrategy());
            ArrayList<VehicleDTO> listOfVehicles = parkingLotSystem.findCarsWithColor(Vehicle.VehicleColor.BLUE, Vehicle.VehicleType.TOYOTA);
            Assert.assertEquals(vehicle2.vehicleColor, listOfVehicles.get(0).vehicleType);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }

    }

    @Test
    public void givenCarWhenPark_ItShouldReturnCarThatHasBeenParkedInTheLast30Minutes() throws ParkingLotException {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1, 4);
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle, new NormalParkingStrategy());
            Vehicle vehicle2 = new Vehicle("UP44 S007", Vehicle.VehicleColor.BLUE, Vehicle.VehicleType.BMW, LocalTime.now());
            parkingLotSystem.park(vehicle2, new HandicapParkingStrategy());
            Vehicle vehicle3 = new Vehicle("UP44 U007", Vehicle.VehicleColor.OTHER, Vehicle.VehicleType.TOYOTA,LocalTime.now());
            parkingLotSystem.park(vehicle3, new NormalParkingStrategy());
            Vehicle vehicle4 = new Vehicle("UP44 B007", Vehicle.VehicleColor.WHITE, Vehicle.VehicleType.TOYOTA,LocalTime.now());
            parkingLotSystem.park(vehicle4, new NormalParkingStrategy());
            vehicle2.time= LocalTime.now().minusMinutes(30);
            vehicle3.time=LocalTime.now().minusMinutes(40);
            List<Integer> carWithin30Min = parkingLotSystem.getCarWithin30Min();
            Assert.assertTrue(carWithin30Min.contains(3));
            Assert.assertTrue(carWithin30Min.contains(40));
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenCars_whenParkedCars_shouldReturnPark1OrPark5Lot() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle( "UP44 B007",Vehicle.VehicleColor.BLUE, Vehicle.VehicleType.BMW,LocalTime.now());
        parkingLotSystem.park(vehicle1,new NormalParkingStrategy());
        Map<Integer, Vehicle> lotData = parkingLotSystem.getLotData(1,DriverType.HANDICAP);
        Assert.assertEquals(1,lotData.size());
    }
    */

    @Test
    public void givenAVehicle_whenParked_shouldReturn_AllVehicleDetails() throws ParkingLotException {
        parkingLotSystem.park(new Vehicle(DriverType.HANDICAP, Vehicle.VehicleColor.BLUE, Vehicle.VehicleType.LARGE));
        parkingLotSystem.park(new Vehicle(DriverType.HANDICAP, Vehicle.VehicleColor.WHITE, Vehicle.VehicleType.SMALL));
        parkingLotSystem.park(new Vehicle(DriverType.NORMAL, Vehicle.VehicleColor.BLUE, Vehicle.VehicleType.LARGE));
        parkingLotSystem.park(new Vehicle(DriverType.NORMAL,Vehicle.VehicleColor.BLUE, Vehicle.VehicleType.SMALL));
        parkingLotSystem.park(new Vehicle(DriverType.NORMAL, Vehicle.VehicleColor.OTHER, Vehicle.VehicleType.LARGE));
        Map<Integer, Vehicle> allVehicleDetails = parkingLotSystem.getAllDetailsOfVehicles();
        Assert.assertEquals(5,allVehicleDetails.size());
    }
}


