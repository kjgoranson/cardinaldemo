package exceptions;

public class InvalidToolCodeException extends Exception {
    public InvalidToolCodeException(String code){
        super("Tool code "+code+" was not found");
    }
}