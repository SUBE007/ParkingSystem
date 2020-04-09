package parkinglot;

import exception.ParkingLotException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem=null;
    ParkingLotOwner owner=null;
    private Object vehicle;

    @Before
    public void setUp() throws Exception{
        vehicle=new Object();
        parkingLotSystem=new ParkingLotSystem(1);
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
            boolean isUnParked = parkingLotSystem.unPark(vehicle);
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
}

