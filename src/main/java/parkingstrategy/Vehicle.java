package parkingstrategy;

import java.time.LocalTime;

public class Vehicle {
        public enum VehicleColor {WHITE, BLUE, OTHER}
        public enum VehicleType {TOYOTA,BMW}

        public  VehicleType vehicleType;
        public  String thisVehicleNumberPlate;
        public VehicleColor vehicleColor;
        public LocalTime time;

        public Vehicle() { }

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

                ", colour=" + vehicleColor +
                ", carType=" + vehicleType +
                ", plateNo='" + thisVehicleNumberPlate + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

}

