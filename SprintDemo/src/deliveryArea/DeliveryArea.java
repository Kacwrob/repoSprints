package deliveryArea;

public class DeliveryArea {
	  
public  boolean validateAreaID(String areaID) throws DeliveryAreaExceptionHandler  {
		
		// validateAreaID returns true if the userName is between 
		// 1 and 5 characters. Otherwise it returns false.
		boolean result = false;
				
				
		if (areaID.length() < 1)
			throw new DeliveryAreaExceptionHandler("Area ID length can't be less than 1");
		else if (areaID.length() >= 1  && areaID.length() <=5)
			result = true;
		else if (areaID.length() >5)
			throw new DeliveryAreaExceptionHandler("Area ID length can't be greater than 5");
				
			return result;
//	
//	 if (areaID.length() >= 1  && areaID.length() <=5) {
//			return true;
//	 }
//	 return false;
//		
	}
 /////////////////////////////////////////

public class DeliveryAreaInfo{
	private int areaId;
    private int customerId;
    private String town;

    public DeliveryAreaInfo(int areaId, int customerId, String town) {
        this.areaId = areaId;
        this.customerId = customerId;
        this.town = town;
    }
 // Method to validate Customer ID within DeliveryAreaInfo
    public void validateCustomer(int inputCustomerId) throws DeliveryAreaExceptionHandler {
        if (this.customerId != inputCustomerId) {
            throw new DeliveryAreaExceptionHandler("Customer ID does not match DeliveryAreaInfo's customer ID.");
        }
    }

}

boolean  validateTown(String inputTown) throws DeliveryAreaExceptionHandler {
	boolean result = false;
	if (inputTown.length() < 3)
		throw new DeliveryAreaExceptionHandler("Town length can't be less than 3");
	else if (inputTown.length() >= 3  && inputTown.length() <=30)
		result = true;
	else if (inputTown.length() >30)
		throw new DeliveryAreaExceptionHandler("Town length can't be greater than 30");
	
	return result;
}


boolean validateTownContainsLetters(String inputTown)  throws DeliveryAreaExceptionHandler {
	boolean result= false;
	if (!inputTown.matches("^[a-zA-Z\\s]+$")) {
        throw new DeliveryAreaExceptionHandler("Town name must contain only letters.");
    }
	else {
		result = true;
	}
	
	return result;
}




}

