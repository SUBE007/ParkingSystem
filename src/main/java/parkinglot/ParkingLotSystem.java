package parkinglot;

import exception.ParkingLotException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ParkingLotSystem {
    private int actualCapacity;
    private List vehicles;
    private Object vehicle1 = null;
    private List<ParkingLotObserver> observers;
    Map<Integer, Object> mapData = new HashMap<>();
    public static int key = 0;
    ParkingLotOwner owner = new ParkingLotOwner();
    List<Integer> unOccupiedSlotList;
    static Map<Integer, Object> vehicleSlotMap;
    public ParkingLotSystem parkingLot;

        public ParkingLotSystem(int capacity) {
            this.observers = new ArrayList<>();
            this.vehicles = new ArrayList();
            this.actualCapacity = capacity;

            unOccupiedSlotList = new ArrayList<Integer>();
            for (int slotPositions = 0; slotPositions < capacity; slotPositions++) {
                unOccupiedSlotList.add(slotPositions);
            }
        }

        public void parkVehicle(Integer slotPosition, Object vehicle) {
            vehicleSlotMap.put(slotPosition, vehicle);
            vehicleSlotMap.put(slotPosition, new ParkingSlot(vehicle));
            unOccupiedSlotList.remove(slotPosition);
        }

        public void setCapacity(int capacity) {
            this.actualCapacity = capacity;
        }

        public void registerParkingLotObserver(ParkingLotObserver observer) {
            this.observers.add(observer);

        }

        public void park(Object vehicle) throws ParkingLotException {
            if (isVehicleParked(vehicle))
                throw new ParkingLotException("Parking Lot is Full");
            if (mapData.size() == this.actualCapacity) {
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsFull();
                }
                throw new ParkingLotException("Vehicle already Parked", ParkingLotException.ExceptionType.PARKING_LOT_FULL);
            }
            this.vehicle1 = vehicle;
            if (mapData.size() < actualCapacity) {
                mapData.put(key, vehicle);
                mapData.put(key, vehicle);
                key++;
            }
        }

        public void park(Integer slotPosition, Object vehicle) throws ParkingLotException {
            if (parkingLot.vehicleSlotMap.size() == this.actualCapacity)
                throw new ParkingLotException("Parking lot is full", ParkingLotException.ExceptionType.PARKING_LOT_FULL);
            parkingLot.parkVehicle(slotPosition, vehicle);
        }

        public Object unPark(Object vehicle) throws ParkingLotException {
            Integer positionOfVehicle = parkingLot.vehicleLocation(vehicle);
            ParkingSlot slot = (ParkingSlot) parkingLot.vehicleSlotMap.remove(positionOfVehicle);
            parkingLot.unOccupiedSlotList.add(positionOfVehicle);
            parkingLot.unOccupiedSlotList.sort(Integer::compareTo);
            return slot.vehicle;
        }

        public boolean isVehicleParked(Object vehicle) {
            for (int i = 0; i < mapData.size(); i++) {
                if (mapData.get(i) == vehicle)
                    return true;
            }
            return false;
        }
        public int getSlotNo (Object vehicle){
            for (Map.Entry<Integer, Object> entry : mapData.entrySet()) {
                if (vehicle.equals(entry.getValue())) {
                    key = entry.getKey();
                }
            }
            return key;
        }

        public Integer findMyCar (Object vehicle){
            return ParkingLotSystem.vehicleSlotMap
                    .entrySet()
                    .stream()
                    .filter(a -> a.getValue().equals(vehicle))
                    .findFirst().orElse(null).getKey();
        }

        public Integer vehicleLocation (Object vehicle) throws ParkingLotException {
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
    }




