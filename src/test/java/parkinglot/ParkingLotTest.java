package parkinglot;

import exception.ParkingLotException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem=null;
    private Object vehicle;

    @Before
    public void setUp() throws Exception{
        vehicle=new Object();
        parkingLotSystem=new ParkingLotSystem();
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
            boolean isUnParked = parkingLotSystem.Unpark(vehicle);
            Assert.assertTrue(isUnParked);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }
}
