package Customer;

import phoneValidator.PhoneCheckExceptionHandler;

public class phoneValidator {
	
	public static boolean validatePhoneNumber(String phoneNumber) throws PhoneCheckExceptionHandler {
        if (!phoneNumber.matches("\\d{10}")) {
            throw new PhoneCheckExceptionHandler("Phone number must be exactly 10 digits and contain only integers");
        }
        return true; // Phone number is valid
    }

    static class PhoneCheckExceptionHandler extends Exception {
        public PhoneCheckExceptionHandler(String message) {
            super(message);
        }
    }
}