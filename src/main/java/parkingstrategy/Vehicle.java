package parkingstrategy;

import java.time.LocalTime;

public class Vehicle {
        public enum VehicleColor {WHITE, BLUE, OTHER}
        public enum VehicleType {TOYOTA,BMW,LARGE,SMALL}
        public Integer lot;
        public  VehicleType vehicleType;
        public  String thisVehicleNumberPlate;
        public VehicleColor vehicleColor;
        public LocalTime time;
        public DriverType type;

        public Vehicle() { }

        public Vehicle(DriverType type) {
        this.type=type;
        }
        public Vehicle(String vehicleNumberPlate, VehicleColor color, VehicleType vehicleType, LocalTime time) {
            this.thisVehicleNumberPlate = vehicleNumberPlate;
            this.vehicleColor = color;
            this.vehicleType = vehicleType;
            this.time=time;
        }

        public LocalTime getTime(){
            return time;
        }
    @Override
    public String toString() {
        return "VehicleInfo{" +
                ", Lot=" + lot +
                ", Colour=" + vehicleColor +
                ", CarType=" + vehicleType +
                ", PlateNo='" + thisVehicleNumberPlate + '\'' +
                ", Time='" + time + '\'' +
                '}';
    }

}

