package exceptions;

public class InvalidToolTypeException extends Exception {
    public InvalidToolTypeException(String type){
        super("Tool type "+type+" was not found");
    }
}