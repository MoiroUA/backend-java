package ua.vision.moiro.vision.exception;

public class EmailNotFound extends RuntimeException {
    public EmailNotFound(String email) {
        super(String.format("User with %s not found", email));
    }
}
