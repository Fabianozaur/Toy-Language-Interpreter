package Model.Exceptions;

public class EmptyProgramStateException extends RuntimeException{
    public EmptyProgramStateException(String errorMessage){
        super(errorMessage);
    }

}
