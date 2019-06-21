package exceptions;

public class InvalidDiscountRangeException extends Exception {
    public InvalidDiscountRangeException(){
        super("Discount percent must be between 0 and 100, inclusive");
    }
}
