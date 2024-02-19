package Customer;


public class emailValidator {

    public static boolean validateEmail(String email) throws EmailCheckExceptionHandler {
        if (email.length() < 3 || email.length() > 30 || !email.contains("@")) {
            throw new EmailCheckExceptionHandler("Email must be between 3 and 30 characters and contain '@'");
        }
        return true; // Email is valid
    }

    static class EmailCheckExceptionHandler extends Exception {
        public EmailCheckExceptionHandler(String message) {
            super(message);
        }
    }
}