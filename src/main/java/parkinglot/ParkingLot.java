package parkinglot;

import exception.ParkingLotException;
import parkingstrategy.Slot;

import java.util.*;
import java.util.stream.IntStream;

public class ParkingLot {
    Map<Integer, ParkingSlot> vehicleSlotMap;
    public List<Integer> unOccupiedSlotList;
    public List<ParkingSlot> listOfOccupiedSlots;
    public List<Slot> slots;
    private int vehicleCount;
    private int actualCapacity;
    public int noOfVehicleParked;


    ParkingLot(Integer parkingLotSize) {
        vehicleSlotMap = new HashMap<>();
        unOccupiedSlotList = new ArrayList<Integer>();
        IntStream.range(0, parkingLotSize).forEach(slotNumber -> this.unOccupiedSlotList.add(slotNumber));
     }

    public  Integer vehicleLocation (Object vehicle) throws ParkingLotException {
        Integer position;
        try {
            position = vehicleSlotMap
                    .entrySet()
                    .stream()
                    .filter(a -> a.getValue().equals(vehicle))
                    .findFirst().orElse(null)
                    .getKey();
        } catch (NullPointerException e) {
            throw new ParkingLotException("Unparking wrong vehicle", ParkingLotException.ExceptionType.UNPARKING_WRONG_VEHICLE);
        }

        return position;
    }

    private Slot getSlot(Object vehicle) throws ParkingLotException {

        Slot vehicleSlot = slots.stream()
                .filter(slot -> slot.getVehicle() != null && slot.getVehicle().equals(vehicle))
                .findFirst()
                .orElseThrow(() -> new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND, "vehicle not found"));
        return vehicleSlot;
    }

    public boolean isVehiclePresent(Object vehicle) {
        try {
            return getSlot(vehicle).getVehicle().equals(vehicle);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List getEmptySlots() {
        List emptySlots = new ArrayList();
        IntStream.range(0, slots.size()).filter(slot -> this.slots.get(slot).getVehicle() == null).forEach(slots -> {
            emptySlots.add(slots);
        });
        return emptySlots;
    }

    public int getNumberOfVehiclesParked() {
        return this.vehicleCount;
    }

    public int getNumberOfEmptySlots() {
        return this.actualCapacity - vehicleCount;
    }

}

