package parkingstrategy;

import exception.ParkingLotException;
import parkinglot.ParkingLot;
import parkinglot.ParkingLotSystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface ParkingStrategy {
   /* public ParkingLot getLotForHandicapDriver() throws ParkingLotException {
       return ParkingLotSystem.parkingLotsList.stream().filter(parkingLot -> parkingLot.getEmptySlots().size() > 0)
                .findFirst().orElseThrow(() -> new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_CAPACITY_FULL,""));
    }
    public ParkingLot getLotForNormalDriver() {
        List<ParkingLot> tempLots = new ArrayList<>(ParkingLotSystem.parkingLotsList);
         return tempLots.get(0);
    }
    public ParkingLot getLotForLargeVehicle() {
        List<ParkingLot> tempLots = new ArrayList<>(ParkingLotSystem.parkingLotsList);
        tempLots.sort(Comparator.comparing(lot->lot.getNumberOfEmptySlots()));
        return tempLots.get(0);
    }
*/
   public ArrayList<ParkingLot> parkVehicle(ArrayList<ParkingLot> parkingLots, Vehicle vehicle) throws ParkingLotException;
}
