package parkingstrategy;

import exception.ParkingLotException;
import parkinglot.ParkingLot;

public class AssignLot {
    ParkingStrategy strategy = new ParkingStrategy();

    public ParkingLot getLot(DriverType driverType) throws ParkingLotException {
        if(driverType.equals(DriverType.HANDICAP))
            return strategy.getLotForHandicapDriver();
        return strategy.getLotForNormalDriver();
    }
}
