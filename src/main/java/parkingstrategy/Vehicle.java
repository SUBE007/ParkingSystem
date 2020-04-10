package parkingstrategy;

public class Vehicle {

    public Vehicle() {

    }

    public enum VehicleColor {WHITE,OTHER}
         public VehicleColor vehicleColor;

         public Vehicle(VehicleColor color){
            this.vehicleColor = color;
         }
}

