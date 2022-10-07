package Model.Exceptions;

public class NonexistentKeyException extends RuntimeException{
    public NonexistentKeyException() {
        super ("Key does not exist!");
    }

    public NonexistentKeyException(String message) {
        super (message);
    }
}
