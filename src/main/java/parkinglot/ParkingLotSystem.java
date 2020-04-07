package parkinglot;

public class ParkingLotSystem {
    private Object vehicle;

    public ParkingLotSystem(){ }

    public boolean park(Object vehicle)   {
        this.vehicle=vehicle;
        return true;
    }
    public boolean Unpark(Object vehicle) {
        if (vehicle==null) return false;
        if (this.vehicle.equals(vehicle)){
            this.vehicle=null;
            return true;
        }
        return false;
    }
}
