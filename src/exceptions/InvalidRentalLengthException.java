package exceptions;

public class InvalidRentalLengthException extends Exception {
    public InvalidRentalLengthException(){
        super("Rental days must be greater than 0");
    }
}
