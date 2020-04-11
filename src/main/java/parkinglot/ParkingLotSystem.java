package parkinglot;

import dtoclasses.VehicleDTO;
import exception.ParkingLotException;
import parkingstrategy.AssignLot;
import parkingstrategy.DriverType;
import parkingstrategy.ParkingStrategy;
import parkingstrategy.Vehicle;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class ParkingLotSystem {
    private int actualCapacity;
    private int noOfLots;
    private ArrayList<ParkingLot> parkingLots;
    public static List<ParkingLot> parkingLotsList;
    private List vehicles;
    private List<ParkingLotObserver> parkingLotObserver;
    Map<Integer, Object> mapData = new HashMap<>();
    List<Integer> timeList = new ArrayList<>();
    public static int key = 0;
    List<Integer> unOccupiedSlotList;
    public ParkingLot parkingLot;
    public AssignLot assignLot;

        public ParkingLotSystem(int noOfLots,int capacity) {
            this.parkingLotObserver = new ArrayList();
            unOccupiedSlotList = new ArrayList<Integer>();
            parkingLots = new ArrayList<ParkingLot>();
            parkingLot = new ParkingLot(capacity);
            this.actualCapacity = capacity*noOfLots;
            this.noOfLots = noOfLots;
            IntStream.range(0, noOfLots).forEach(slotNumber -> this.parkingLots.add(new ParkingLot(actualCapacity)));
        }

        public ParkingLotSystem() {
            this.parkingLots = new ArrayList<>();
            this.assignLot = new AssignLot();
        }

        public void setCapacity(int capacity) {
            this.actualCapacity = capacity;
        }

        public void registerParkingLotObserver(ParkingLotObserver observer) {
            this.parkingLotObserver.add(observer);

        }

        public void park(Vehicle vehicle, ParkingStrategy strategy) throws ParkingLotException {
            AtomicReference<Integer> totalSlotOccupied = new AtomicReference<>(0);
            parkingLots.stream().forEach(parkingLot1 -> totalSlotOccupied.updateAndGet(v -> v + parkingLot1.noOfVehicleParked));
            if (totalSlotOccupied.get() == actualCapacity) {
                for (ParkingLotObserver observer : parkingLotObserver)
                    observer.capacityIsFull();
                throw new ParkingLotException("Parking lot is full", ParkingLotException.ExceptionType.PARKING_LOT_FULL);
            }
            parkingLots = strategy.parkVehicle(parkingLots, vehicle);
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

    public ArrayList<VehicleDTO> findCarsWithColor(Vehicle.VehicleColor vehicleColor, Vehicle.VehicleType vehicleType)  {
        ArrayList<ParkingSlot> slotArrayList = new ArrayList<>();
        for (ParkingLot lot: parkingLots ) {
            List<ParkingSlot> parkingSlotList  = (lot.listOfOccupiedSlots).stream()
                    .filter(slot -> slot.vehicle != null
                            && slot.vehicle.vehicleColor == vehicleColor && slot.vehicle.vehicleType == vehicleType)
                    .collect(Collectors.toList());
            slotArrayList.addAll(parkingSlotList);
        }
        ArrayList<VehicleDTO> vehicleDTOS = new ArrayList<>();
        slotArrayList.stream().forEach(slot -> vehicleDTOS.add(new VehicleDTO(slot)));
        return vehicleDTOS;
    }

    public List<Integer> getCarWithin30Min() {
        for( int i=1;i<=actualCapacity ;i++ )
        {
            if(mapData.get(i)!=null)
            {
                Vehicle o = (Vehicle) mapData.get(i);
                LocalTime parkTime = o.time;
                LocalTime currentTimeThirtyMinAgo = LocalTime.now().minusMinutes(30);

                int compare = currentTimeThirtyMinAgo.compareTo(parkTime);
                if(compare==1)
                    timeList.add(i); }
        }
        return timeList;
    }
    public ParkingLot getParkedVehicleLot(Object vehicle) {
        ParkingLot parkingLotWithParkedVehicle = parkingLotsList.stream()
                .filter(parkingLot -> parkingLot.isVehiclePresent(vehicle))
                .findFirst()
                .orElse(null);
        return parkingLotWithParkedVehicle;
    }
}




