package parkinglot;

import exception.ParkingLotException;

import java.util.ArrayList;
import java.util.List;


public class ParkingLotSystem {
    private int actualCapacity;
    private List vehicles;
   // private List <ParkingLotObserver> observers;
    private ParkingLotOwner owner;
    private AirportSecurity security;

    public ParkingLotSystem(int capacity){
     //   this.observers=new ArrayList<>();
         this.vehicles=new ArrayList();
         this.actualCapacity=capacity;
    }

    public void setCapacity(int capacity) {
        this.actualCapacity=capacity;
    }

    public void registerOwner(ParkingLotOwner owner) {
        this.owner=owner;

    }
    public void registerSecurity(AirportSecurity airportSecurity) {
        this.security=airportSecurity;
    }

    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicles.size()==this.actualCapacity) {
            owner.isCapacityFull();
            security.isCapacityFull();
            throw new ParkingLotException("Parking Lot is Full");
        }
        if(isVehicleParked(vehicle))
          throw new ParkingLotException(" Vehicle already Parked");
        this.vehicles.add(vehicle);
    }

    public boolean Unpark(Object vehicle) {
        if (vehicle==null) return false;
        if (this.vehicles.contains(vehicle)){
            this.vehicles.remove(vehicle);
            return true;
        }
        return false;
    }
    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicles.contains(vehicle))
            return true;
        return false;
    }



}
