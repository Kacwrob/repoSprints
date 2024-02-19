package Customer;

import customerTest.LoginCheckExceptionHandler;

public class customerTest {

	

	public static boolean validateUserName(String userName) throws LoginCheckExceptionHandler {
        boolean result = false;

        // Check for numbers in the userName
        if (userName.matches(".*\\d.*")) {
            throw new LoginCheckExceptionHandler("Username contains numbers");
        }

        // Validation for userName length
        if (userName.length() >= 1 && userName.length() <= 3)
            throw new LoginCheckExceptionHandler("Length less than 3");
        else if (userName.length() >= 4 && userName.length() <= 30)
            result = true;
        else if (userName.length() >= 31 && userName.length() <= 100)
            throw new LoginCheckExceptionHandler("Length greater than 30");

        return result;
    }
	
	static class LoginCheckExceptionHandler extends Exception {
        public LoginCheckExceptionHandler(String message) {
            super(message);
        }
    }
}


 








