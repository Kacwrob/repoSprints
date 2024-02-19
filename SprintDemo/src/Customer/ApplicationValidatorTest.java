package Customer;

import junit.framework.TestCase;

public class ApplicationValidatorTest extends TestCase {

    public static void main(String[] args) {
        testAddressValidation();
        testUsernameValidation();
        testPhoneNumberValidation();
        testEmailValidation();
        testCustomerIdValidation();
        }

    public static void testAddressValidation() {
        System.out.println("Address Validation Tests:");
        // Include the address test cases here
		testValidateAddressAndPostCode("123 Main", "Apt 4", "1234567");
		testValidateAddressAndPostCode("123 Main Street", "Suite 5", "12345");
		testValidateAddressAndPostCode("456 Elm Street", "Unit 8", "12345678");
		testValidateAddressAndPostCode("Ab", "Suite 5", "1234567");
		testValidateAddressAndPostCode("789 Pine", "Box 9", "7654321");
        System.out.println();
    }

    public static void testUsernameValidation() {
        System.out.println("Username Validation Tests:");
        // Test cases for username validation
        testValidateUserName("ValidUsername");
        testValidateUserName("Yo");
        testValidateUserName("ThisIsAReallyLongUsernameThatExceedsTheLimit");
        // More test cases as needed...
        System.out.println();
    }
    
    public static void testPhoneNumberValidation() {
        System.out.println("Phone Number Validation Tests:");
        testValidatePhoneNumber("1234567890");
        testValidatePhoneNumber("123456"); // Invalid: too short
        testValidatePhoneNumber("12345678901"); // Invalid: too long
        testValidatePhoneNumber("12345abcde"); // Invalid: contains non-numeric characters
        System.out.println();
    }
    
   public static void testEmailValidation() {
        System.out.println("Email Validation Tests:");
        testValidateEmail("test@example.com");
        testValidateEmail("invalid_email"); // Invalid: missing '@'
        testValidateEmail("email@example.com"); // Invalid: too long (31 characters)
        // More email test cases as needed...
        System.out.println();
    }

    public static void testCustomerIdValidation() {
        System.out.println("Customer ID Validation Tests:");
        testValidateCustomerId("12345");
        testValidateCustomerId("abcde"); // Invalid: non-numeric
        testValidateCustomerId("123456"); // Invalid: too long
        // More customer ID test cases as needed...
        System.out.println();
    }

    public static void testValidateUserName(String userName) {
        try {
            boolean isValid = customerTest.validateUserName(userName);
            System.out.println("Username '" + userName + "' is valid: " + isValid);
        } catch (customerTest.LoginCheckExceptionHandler e) {
            System.out.println("Validation failed for '" + userName + "': " + e.getMessage());
        }
    }

    public static void testValidateAddressAndPostCode(String line1, String line2, String postCode) {
        try {
            boolean isValid = addressValidator.validateAddressLinesAndPostCode(line1, line2, postCode);
            System.out.println("Validation passed for: Line 1: '" + line1 + "', Line 2: '" + line2 + "', Post Code: '" + postCode + "'");
        } catch (addressValidator.LoginCheckExceptionHandler e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }
    
    public static void testValidatePhoneNumber(String phoneNumber) {
        try {
            boolean isValid = phoneValidator.validatePhoneNumber(phoneNumber);
            System.out.println("Phone number '" + phoneNumber + "' is valid: " + isValid);
        } catch (phoneValidator.PhoneCheckExceptionHandler e) {
            System.out.println("Validation failed for phone number '" + phoneNumber + "': " + e.getMessage());
        }
    }
    
    public static void testValidateEmail(String email) {
        try {
            boolean isValid = emailValidator.validateEmail(email);
            System.out.println("Email '" + email + "' is valid: " + isValid);
        } catch (emailValidator.EmailCheckExceptionHandler e) {
            System.out.println("Validation failed for email '" + email + "': " + e.getMessage());
        }
    }

    public static void testValidateCustomerId(String customerId) {
        try {
            boolean isValid = idValidator.validateCustomerId(customerId);
            System.out.println("Customer ID '" + customerId + "' is valid: " + isValid);
        } catch (idValidator.CustomerIdCheckExceptionHandler e) {
            System.out.println("Validation failed for customer ID '" + customerId + "': " + e.getMessage());
        }
    }
}

