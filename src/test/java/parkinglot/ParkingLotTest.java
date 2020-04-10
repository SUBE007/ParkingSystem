package parkinglot;

import exception.ParkingLotException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parkingstrategy.DriverType;
import parkingstrategy.Vehicle;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem=null;
    ParkingLotOwner owner=null;
    private Object vehicle;

    @Before
    public void setUp() throws Exception{
         vehicle=new Object();
         owner=new ParkingLotOwner();
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue(){
      try {
          parkingLotSystem.park(vehicle);
          boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
          Assert.assertTrue(isParked);
      }catch (ParkingLotException e){
            e.printStackTrace();
      }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse(){
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        }catch (ParkingLotException e){
            Assert.assertEquals("Parking Lot is Full",e.getMessage());
            e.printStackTrace();
        }
   }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue(){
        try {
            parkingLotSystem.park(vehicle);
            boolean isUnParked = (boolean) parkingLotSystem.unPark(vehicle);
            Assert.assertTrue(isUnParked);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void GivenWhenParkingLotIsFull_ShouldInformOwnerFullMessage() {
       try {
           parkingLotSystem.registerParkingLotObserver(owner);
           parkingLotSystem.park(new Object());
       }catch (ParkingLotException e){
           boolean capacityFull=owner.isCapacityFull();
           Assert.assertTrue(capacityFull);
       }
    }

    @Test
    public void givenCapacityIs2_ThenShouldBeAbleToPark2Vehicle() {
        parkingLotSystem.setCapacity(2);
        Object vehicle2=new Object();
           try {
                parkingLotSystem.park(vehicle);
                parkingLotSystem.park(vehicle2);
                boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle);
                boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle2);
                Assert.assertTrue(isParked1 && isParked2);
           }catch (ParkingLotException e){
                e.printStackTrace();
        }
    }

    @Test
    public void GivenWhenParkingLotIsFull_ShouldInformSecurity() {
         AirportSecurity airportSecurity= new AirportSecurity();
          try {
              parkingLotSystem.park(vehicle);
              parkingLotSystem.park(new Object());
              parkingLotSystem.registerParkingLotObserver(owner);
         }catch (ParkingLotException e){
            boolean capacityFull=airportSecurity.isCapacityFull();
            Assert.assertTrue(capacityFull);
        }
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() throws ParkingLotException {
        ParkingLotOwner owner=new ParkingLotOwner();
        Object vehicle2=new Object();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
              parkingLotSystem.park(vehicle);
              parkingLotSystem.park(vehicle2);
        }catch (ParkingLotException e){
            parkingLotSystem.unPark(vehicle);
            boolean capacityFull=owner.isCapacityFull();
            Assert.assertFalse(capacityFull);
        }
    }

    @Test
    public void givenParkingLot_HavingAttendant_shouldBeAbleToParkCar() {
        try {
            parkingLotSystem.registerParkingLotObserver(owner);
            parkingLotSystem.park(vehicle);
            boolean isVehicleParked = parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertTrue(isVehicleParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
         }
      }

    @Test
    public void givenParkingLot_HavingAttendant_WhenParkingLotFullShouldThrowException() {
        try {
            parkingLotSystem.registerParkingLotObserver(owner);
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Parking Lot is Full", e.getMessage());
        }
    }

    @Test
    public void givenCarIfInsideParkingLot_ShouldReturnTrue() throws ParkingLotException {
        Object vehicle1 = new Object();
        parkingLotSystem.park(vehicle1);
        int slotNo = parkingLotSystem.getSlotNo(vehicle);
        Assert.assertEquals(1,slotNo);

    }

    @Test
    public void givenParkingSlots_ifVehicleParked_ShouldBeAbleToCharge() {
        ParkingLotSystem parkingLotSystem1 = new ParkingLotSystem(3,5);
        try {
            parkingLotSystem.registerParkingLotObserver(owner);
            parkingLotSystem.park(vehicle);
            Object returnVehicle =parkingLotSystem.unPark(vehicle);
            Assert.assertEquals(vehicle,returnVehicle);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenMultipleParkingLots_ifVehicleComes_ShouldUseEvenDistributionForParking() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(2,4);
        parkingLotSystem.registerParkingLotObserver(owner);
        try {

            parkingLotSystem.park(vehicle);
            Integer pos1 = parkingLotSystem.findMyCar(vehicle);

            Object vehicle2 = new Object();
            parkingLotSystem.park(vehicle2);
            Integer pos2 = parkingLotSystem.findMyCar(vehicle2);

            Object vehicle3 = new Object();
            parkingLotSystem.park(vehicle3);
            Integer pos3 = parkingLotSystem.findMyCar(vehicle3);

            Object vehicle4 = new Object();
            parkingLotSystem.park(vehicle4);
            Integer pos4 = parkingLotSystem.findMyCar(vehicle4);

            Assert.assertEquals((Integer) 0,pos1);
            Assert.assertEquals((Integer)0,pos2);
            Assert.assertEquals((Integer)1,pos3);
            Assert.assertEquals((Integer)1,pos4);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleWithHandicappedDriver_ShouldParkVehicleAtNearestFreeSpace() {
        try {
            ParkingLotSystem parkingLotSystem = new ParkingLotSystem(2,2);
            Object vehicle = new Object();
            parkingLotSystem.parkVehicle(DriverType.HANDICAP,vehicle);
            Object vehicle2 = new Object();
            parkingLotSystem.parkVehicle(DriverType.HANDICAP,vehicle2);
            ParkingLot parkedVehicleLot = parkingLotSystem.getParkedVehicleLot(vehicle2);
            Assert.assertEquals(parkingLotSystem.parkingLotsList.get(0),parkedVehicleLot);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenLargeVehicle_ShouldParkVehicleAtMoreFreeSpace() {
        try {
            parkingLotSystem.registerParkingLotObserver(owner);
            Object vehicle1 = new Object();
            Object vehicle2 = new Object();
            Object vehicle3 = new Object();
            Object vehicle4 = new Object();
            Object vehicle5 = new Object();
            Object vehicle6 = new Object();
            parkingLotSystem=new ParkingLotSystem(2,3);
            parkingLotSystem.parkVehicle(DriverType.NORMAL, vehicle1);
            parkingLotSystem.parkVehicle(DriverType.NORMAL, vehicle2);
            parkingLotSystem.parkVehicle(DriverType.NORMAL, vehicle3);
            parkingLotSystem.parkVehicle(DriverType.NORMAL, vehicle4);
            parkingLotSystem.parkVehicle(DriverType.NORMAL, vehicle5);
            parkingLotSystem.unPark(vehicle2);
            parkingLotSystem.unPark(vehicle3);
            parkingLotSystem.parkVehicle(Vehicle.VehicleSize.LARGE, vehicle6);
            ParkingLot parkedVehicleLot = parkingLotSystem.getParkedVehicleLot(vehicle5);
            Assert.assertEquals(parkingLotSystem.parkingLotsList.get(0), parkedVehicleLot);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}


