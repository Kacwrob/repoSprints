package Customer;

public class LoginCheckExceptionHandler extends Exception {	
	String message;
	
	public LoginCheckExceptionHandler(String errMessage){
		message = errMessage;
	}
	
	public String getMessage() {
		return message;
	}

}