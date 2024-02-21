package deliveryArea;

import deliveryArea.DeliveryArea.DeliveryAreaInfo;
import junit.framework.TestCase;

public class DeliveryAreaTest extends TestCase{
	
	
	// Test No: 1
	// Objective: To test Area ID in range 1 to 5 digits
	// Input(s): AreaID = ""
	// Expected Output: Exception Msg: "false"	

	public void testValidAreaID001() {
		
		DeliveryArea testObj = new DeliveryArea();
		
		try {
			testObj.validateAreaID("");
			fail("Exception expected");
		} catch (DeliveryAreaExceptionHandler e) {
		 assertEquals("Area ID length can't be less than 1",e.getMessage());
		}
	
		
	}
	
	// Test No: 2
	// Objective: To test Area ID in range 1 to 5 digits
	// Input(s): AreaID = "123"
	// Expected Output: Exception Msg: "true"
	
	public void testValidAreaID002() {
		
		DeliveryArea testObj = new DeliveryArea();

		try {
			assertEquals(true,testObj.validateAreaID("123"));
		} catch (DeliveryAreaExceptionHandler e) {
			
			fail("Exception NOT expected");
			
		}
	
		
	}
	
	// Test No: 3
	// Objective: To test Area ID in range 1 to 5 digits
	// Input(s): AreaID = "12345678"
	// Expected Output: Exception Msg: "false"
	
	public void testValidAreaID003() {
		
		DeliveryArea testObj = new DeliveryArea();
		
		
		try {
			testObj.validateAreaID("12345678");
			fail("Exception expected");
		} catch (DeliveryAreaExceptionHandler e) {
			assertEquals("Area ID length can't be greater than 5",e.getMessage());
		}
		
		
	}
	
	// Test No: 4
	// Objective: To test Delivery Area is assigned to specific customers ID
	// Input(s): customerId = "100"
	// Expected Output: Exception Msg: "No exception"
		

	public void testValidateCustomerWithMatchingId001() {
        // Setup
        int areaId = 101;
        int customerId = 100; // Matching customer ID
        String town = "Athlone";
        DeliveryArea deliveryArea = new DeliveryArea(); // Assuming DeliveryArea has a no-argument constructor
        DeliveryArea.DeliveryAreaInfo deliveryAreaInfo = deliveryArea.new DeliveryAreaInfo(areaId, customerId, town);

        // Attempt to validate with the same customer ID
        try {
            deliveryAreaInfo.validateCustomer(100);
            // If no exception is thrown, the test passes
        } catch (DeliveryAreaExceptionHandler e) {
            fail("No exception should be thrown for matching customer IDs");
        }
    }
	
	
	// Test No: 5
	// Objective: To test Delivery Area is assigned to specific customers ID
	// Input(s): customerId = "200"
	// Expected Output: Exception Msg: "Customer ID does not match DeliveryAreaInfo's customer ID."
	public void testValidateCustomerWithMatchingId002() {
		
		int areaId = 101;
        int customerId = 100; // Matching customer ID
        String town = "Athlone";
        DeliveryArea deliveryArea = new DeliveryArea(); // Assuming DeliveryArea has a no-argument constructor
        DeliveryArea.DeliveryAreaInfo deliveryAreaInfo = deliveryArea.new DeliveryAreaInfo(areaId, customerId, town);

        try {
            deliveryAreaInfo.validateCustomer(200); // This ID does not match the Customer's ID
            fail("Expected CustomerIdMismatchException to be thrown");
        } catch (DeliveryAreaExceptionHandler e) {
            assertEquals("Customer ID does not match DeliveryAreaInfo's customer ID.", e.getMessage());
        } catch (Exception e) {
            fail("Expected CustomerIdMismatchException, but caught other Exception");
        }
    }
	
	// Test No: 6
	// Objective: To test Town is in range 3 to 30 letters
	// Input(s): Town = ""
	// Expected Output: Exception Msg: false, "Town length can't be less than 3."
	
	public void testValidateTown001() {
		DeliveryArea testObj = new DeliveryArea();
		
		try {
			testObj.validateTown("");
			fail("Exception expected");
		} catch (DeliveryAreaExceptionHandler e) {
		 assertEquals("Town length can't be less than 3",e.getMessage());
		}
			
	    }
	// Test No: 7
	// Objective: To test Town is in range 3 to 30 letters
	// Input(s): Town= "Athlone"
	// Expected Output: Exception Msg: "true"
	
	public void testValidateTown002() {
		DeliveryArea testObj = new DeliveryArea();
	
		try {
			assertEquals(true,testObj.validateTown("Athlone"));
		} catch (DeliveryAreaExceptionHandler e) {		
			fail("Exception NOT expected");		
		}
	 }
	
	// Test No: 8
	// Objective: To test Town is in range 3 to 30 letters
	// Input(s): Town = "ashdkdhfjkfhfjkdhkdjhskjhcjkbdkbfjkbskasbkfjbfkas"
	// Expected Output: Exception Msg: "Town length can't be greater than 3."
	
	public void testValidateTown003() {
		DeliveryArea testObj = new DeliveryArea();
		
		try {
			testObj.validateTown("ashdkdhfjkfhfjkdhkdjhskjhcjkbdkbfjkbskasbkfjbfkas");
			fail("Exception expected");
		} catch (DeliveryAreaExceptionHandler e) {
		 assertEquals("Town length can't be greater than 30",e.getMessage());
		}
			
	    }
	
	// Test No: 9
	// Objective: To test Town only contains letters
	// Input(s): Town = "Athlone23"
	// Expected Output: Exception Msg: false,"Town name must contain only letters."
		
	public void testValidateTownOnlyContainsLetters001() {
		DeliveryArea testObj = new DeliveryArea();
			
		try {
			testObj.validateTownContainsLetters("Athlone23");
			fail("Exception expected");
		} catch (DeliveryAreaExceptionHandler e) {
		 assertEquals("Town name must contain only letters.",e.getMessage());
		}
				
	 }
	
	// Test No: 10
	// Objective: To test Town contains only letters
	// Input(s): Town = "Istanbul"
	// Expected Output: Exception Msg: true
		
	public void testValidateTownOnlyContainsLetters002() {
		DeliveryArea testObj = new DeliveryArea();			
		
		try {
			assertEquals(true,testObj.validateTownContainsLetters("Istanbul"));
		} catch (DeliveryAreaExceptionHandler e) {		
			fail("Exception NOT expected");		
		}
				
	 }
	
	    


}

