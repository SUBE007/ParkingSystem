package exception;

public class ParkingLotException extends Exception {

    public ParkingLotException(ExceptionType vehicleNotFound, String vehicle_not_found) {
    }

    public enum ExceptionType {UNPARKING_WRONG_VEHICLE, PARKING_LOT_FULL,
        PARKING_LOT_CAPACITY_FULL ,VEHICLE_NOT_FOUND;
    }

    ExceptionType type;
    public ParkingLotException(String message) {
            super(message);
        }

    public ParkingLotException(String message, ExceptionType exceptionType) {
        super(message);
        this.type = exceptionType;
    }
}
