package parkinglot;

import exception.ParkingLotException;

import java.util.*;
import java.util.stream.IntStream;


public class ParkingLotSystem {
    private int actualCapacity;
    private int noOfLots;
    private ArrayList<ParkingLot> parkingLots;
    private List vehicles;
    private List<ParkingLotObserver> parkingLotObserver;
    Map<Integer, Object> mapData = new HashMap<>();
    public static int key = 0;
    List<Integer> unOccupiedSlotList;
    public ParkingLot parkingLot;

        public ParkingLotSystem(int noOfLots,int capacity) {
            this.parkingLotObserver = new ArrayList();
            unOccupiedSlotList = new ArrayList<Integer>();
            parkingLots = new ArrayList<ParkingLot>();
            parkingLot = new ParkingLot(capacity);
            this.actualCapacity = capacity*noOfLots;
            this.noOfLots = noOfLots;
            IntStream.range(0, noOfLots).forEach(slotNumber -> this.parkingLots.add(new ParkingLot(actualCapacity)));
        }

        public void setCapacity(int capacity) {
            this.actualCapacity = capacity;
        }

        public void registerParkingLotObserver(ParkingLotObserver observer) {
            this.parkingLotObserver.add(observer);

        }

        public void park(Object vehicle) throws ParkingLotException {
             if (mapData.size() == this.actualCapacity) {
                for (ParkingLotObserver observer : parkingLotObserver) {
                    observer.capacityIsFull();
                }
                throw new ParkingLotException("Vehicle already Parked", ParkingLotException.ExceptionType.PARKING_LOT_FULL);
            }
            ArrayList<ParkingLot> lotListOfHighestEmptySlots = parkingLots;
            Collections.sort(lotListOfHighestEmptySlots,Comparator.comparing(list ->list.unOccupiedSlotList.size(),Comparator.reverseOrder()));
            parkingLot = lotListOfHighestEmptySlots.get(0);
            parkingLot.parkVehicle(vehicle);
            for (ParkingLot thisParkingLot: parkingLots )
                if (thisParkingLot.equals(parkingLot)) {
                    thisParkingLot = parkingLot;
                }
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
            return parkingLot.vehicleSlotMap
                    .entrySet()
                    .stream()
                    .filter(a -> a.getValue().equals(vehicle))
                    .findFirst()
                    .orElse(null)
                    .getKey();
        }


    }




