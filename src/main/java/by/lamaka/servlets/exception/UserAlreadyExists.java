package by.lamaka.servlets.exception;

public class UserAlreadyExists extends Exception{

    public UserAlreadyExists(String message) {
        super(message);
    }
}
