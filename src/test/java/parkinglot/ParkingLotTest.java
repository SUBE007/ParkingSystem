package parkinglot;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {
    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue(){
        ParkingLotSystem parkingLotSystem=new ParkingLotSystem();
        boolean isParked=parkingLotSystem.park(new Object());
        Assert.assertTrue(isParked);
    }
    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnTrue(){
        Object vehicle=new Object();
        ParkingLotSystem parkingLotSystem=new ParkingLotSystem();
        parkingLotSystem.park(vehicle);
        boolean isUnParked=parkingLotSystem.Unpark(vehicle);
        Assert.assertTrue(isUnParked);
    }
}
