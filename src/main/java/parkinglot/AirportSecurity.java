package parkinglot;

public class AirportSecurity implements ParkingLotObserver {
    private boolean isFullCapacity;
    @Override
    public void capacityIsFull(){
        isFullCapacity=true;
    }

    @Override
    public void capacityIsAvailable() {
      isFullCapacity=false;
    }
    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }
}
