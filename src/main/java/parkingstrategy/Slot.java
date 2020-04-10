package parkingstrategy;

import java.time.LocalDateTime;
import java.util.Objects;

public class Slot {
     private int slotNumber;
    public Vehicle vehicle;

    public Slot(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

//    public LocalDateTime getParkingTime() {
//        return parkingTime;
//    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        Slot slot = (Slot) that;
        Object vehicle = new Object();
        return Objects.equals(vehicle, slot.vehicle);
    }

    public Vehicle getVehicle() {
         return vehicle;
    }
}
