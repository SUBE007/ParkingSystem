package parkingstrategy;

import exception.ParkingLotException;
import parkinglot.ParkingLot;

public class AssignLot {
    ParkingStrategy strategy = new ParkingStrategy();

    public ParkingLot getLot(Enum parkingBasedOn) throws ParkingLotException {
        if (parkingBasedOn.equals(DriverType.HANDICAP))
            return strategy.getLotForHandicapDriver();
        else if (parkingBasedOn.equals(Vehicle.VehicleSize.LARGE))
            return strategy.getLotForLargeVehicle();
        else
            return strategy.getLotForNormalDriver();
    }
}
