package parkingstrategy;

public class Vehicle {
    public enum VehicleSize {
        LARGE
    }

    private String model;
    private String attendantName;
    private String plateNumber;
    private String color;

    public Vehicle( String color,String model,String plateNumber, String attendantName) {
        this.color = color;
        this.plateNumber = plateNumber;
        this.attendantName = attendantName;
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public String getAttendantName() {
        return attendantName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "model='" + model + '\'' +
                ", attendantName='" + attendantName + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

}
