package parkinglot;

import exception.ParkingLotException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ParkingLotSystem {
    private int actualCapacity;
    private List vehicles;
    private Object vehicle1=null;
    private List<ParkingLotObserver> observers;
    Map<Integer, Object> mapData = new HashMap<>();
    public static int key=0;
    ParkingLotOwner owner = new ParkingLotOwner();

    public ParkingLotSystem(int capacity) {
        this.observers = new ArrayList<>();
        this.vehicles = new ArrayList();
        this.actualCapacity = capacity;
    }

    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }

    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);

    }

    public void park(Object vehicle) throws ParkingLotException {
        if(isVehicleParked(vehicle))
            throw new ParkingLotException("Parking Lot is Full");
        if(mapData.size()==this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException("Vehicle already Parked");
        }
        this.vehicle1=vehicle;
        if (mapData.size() < actualCapacity) {
            mapData.put(key,vehicle);
            mapData.put(key, vehicle);
            key++;
        }

    }

    public boolean unPark(Object vehicle) throws ParkingLotException {
        if (vehicle == null)
            throw new ParkingLotException("Enter Vehicle No.");
        boolean vehicleParked = isVehicleParked(vehicle);
        if (vehicleParked == true) {
            key = getSlotNo(vehicle);
            mapData.remove(key);
            if (mapData.size() < actualCapacity) {
                owner.isEmpty();
            }
            return true;
        }

        return false;
    }

    public boolean isVehicleParked(Object vehicle) {
        for (int i = 0; i < mapData.size(); i++) {
            if (mapData.get(i) == vehicle)
                return true;
        }
        return false;
    }

    public int getSlotNo(Object vehicle) {
        for (Map.Entry<Integer, Object> entry : mapData.entrySet()) {
            if (vehicle.equals(entry.getValue())) {
                key = entry.getKey();
            }
        }
        return key;
    }



}
