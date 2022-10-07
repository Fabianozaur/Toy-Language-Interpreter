package Model.Exceptions;

public class InterpreterException extends RuntimeException{
    public InterpreterException(String errorMessage){
        super(errorMessage);
    }
}
