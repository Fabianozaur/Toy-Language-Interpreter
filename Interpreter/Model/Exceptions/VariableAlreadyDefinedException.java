package Model.Exceptions;

public class VariableAlreadyDefinedException extends RuntimeException {
    public VariableAlreadyDefinedException(String message){
        super(message);
    }
}
