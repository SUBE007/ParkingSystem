package parkingstrategy;

import parkinglot.ParkingLotSystem;

public enum DriverType {
    HANDICAP, NORMAL;

    public void carParking(ParkingLotSystem parkingLotSystem, Vehicle vehicle) {
        ParkingLotSystem park = new ParkingLotSystem();
        int lots = park.actualCapacity;
        int count = 0,changeSlot=0;
        int i = 0;
        if (count == lots) {
             changeSlot = changeSlot + 1;
            i = changeSlot;
            count = 1;
        }
        park.mapData.putIfAbsent(i, vehicle);
        i = i + park.noOfLots;
        count++;
    }
}