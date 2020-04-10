package parkinglot;

import parkingstrategy.Vehicle;

import java.util.Objects;

public class ParkingSlot {
    public ParkingLot parkingLot;
    public Vehicle vehicle;
    private final long vehicleParkingTime;
    public ParkingSlot(ParkingLot parkingLotObject, Object vehicle) {
        this.vehicleParkingTime = System.currentTimeMillis();
        this.vehicle = (Vehicle) vehicle;
        this.parkingLot = parkingLotObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this.vehicle == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot that = (ParkingSlot) o;
        return vehicleParkingTime == that.vehicleParkingTime &&
                Objects.equals(vehicle, that.vehicle);
    }
}
