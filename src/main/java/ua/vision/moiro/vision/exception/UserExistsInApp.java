package ua.vision.moiro.vision.exception;

public class UserExistsInApp extends RuntimeException{
    public UserExistsInApp(String email) {
        super(String.format("User with email (%s) is exist in App", email));
    }
}
