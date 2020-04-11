package parkingstrategy;

public class Vehicle {

    public Vehicle() {

    }
    public  VehicleType vehicleType;
    public  String thisVehicleNumberPlate;
    public VehicleColor vehicleColor;

    public enum VehicleColor {WHITE, BLUE, OTHER}
    public enum VehicleType {TOYOTA,BMW}

    public Vehicle(String vehicleNumberPlate, VehicleColor color, VehicleType vehicleType) {
        this.thisVehicleNumberPlate = vehicleNumberPlate;
        this.vehicleColor = color;
        this.vehicleType = vehicleType;
    }

}

