package Newsagent;

public class NewsagentExceptionHandler extends Exception {

	String msg;

	public NewsagentExceptionHandler(String errorMsg) {
		msg = errorMsg;
	}

	public String getMessage() {
		return msg;
	}

}
