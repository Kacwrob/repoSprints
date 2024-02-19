package Customer;

public class addressValidator {

    private static void testValidateAddress(String line1, String line2, String postCode) throws LoginCheckExceptionHandler {
        boolean isValid = validateAddressLinesAndPostCode(line1, line2, postCode);
        System.out.println("Address Line 1: '" + line1 + "', Line 2: '" + line2 + "', Post Code: '" + postCode + "' is valid: " + isValid);
    }

    public static boolean validateAddressLinesAndPostCode(String line1, String line2, String postCode) throws LoginCheckExceptionHandler {
        // Validate Address Line 1
        if (line1.length() < 3 || line1.length() > 20) {
            throw new LoginCheckExceptionHandler("Address Line 1 must be between 3 and 20 characters");
        }

        // Validate Address Line 2
        if (line2.length() < 3 || line2.length() > 20) {
            throw new LoginCheckExceptionHandler("Address Line 2 must be between 3 and 20 characters");
        }

        // Validate Post Code
        if (postCode.length() != 7) {
            throw new LoginCheckExceptionHandler("Post Code must be exactly 7 characters");
        }

        return true; // Address lines and post code are valid
    }

    static class LoginCheckExceptionHandler extends Exception {
        public LoginCheckExceptionHandler(String message) {
            super(message);
        }
    }
}