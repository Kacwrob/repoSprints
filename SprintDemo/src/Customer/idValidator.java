package Customer;



public class idValidator {

    public static boolean validateCustomerId(String customerId) throws CustomerIdCheckExceptionHandler {
        if (!customerId.matches("\\d{1,5}")) {
            throw new CustomerIdCheckExceptionHandler("Customer ID must be 1 to 5 positive integers");
        }
        return true; // Customer ID is valid
    }

    static class CustomerIdCheckExceptionHandler extends Exception {
        public CustomerIdCheckExceptionHandler(String message) {
            super(message);
        }
    }
}
