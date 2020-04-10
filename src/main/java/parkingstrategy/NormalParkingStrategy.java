package parkingstrategy;

import exception.ParkingLotException;
import parkinglot.ParkingLot;

import java.util.ArrayList;

public class NormalParkingStrategy implements ParkingStrategy {
    @Override
    public ArrayList<ParkingLot> parkVehicle(ArrayList<ParkingLot> parkingLots, Vehicle vehicle) throws ParkingLotException {
        int noOfSlots = parkingLots.stream().findFirst().get().listOfOccupiedSlots.size();
        for (int slotNumber = 0; slotNumber < noOfSlots; slotNumber++)
            for (ParkingLot lot : parkingLots)
                if (lot.listOfOccupiedSlots.get(slotNumber).vehicle == null) {
                    lot.listOfOccupiedSlots.get(slotNumber).vehicle = vehicle;
                    return parkingLots;
                }
        throw new ParkingLotException("parkingLotFull",ParkingLotException.ExceptionType.PARKING_LOT_FULL);
    }
}
