package exception;

public class ParkingLotException extends Exception {

    public enum ExceptionType {UNPARKING_WRONG_VEHICLE, PARKING_LOT_FULL}

    ExceptionType type;
    public ParkingLotException(String message) {
            super(message);
        }

    public ParkingLotException(String message, ExceptionType exceptionType) {
        super(message);
        this.type = exceptionType;
    }
}
