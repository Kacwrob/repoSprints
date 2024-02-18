package Newsagent;

public class Newsagent {

	private int id;
	private String userName;
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public Newsagent(String userName, String password) throws NewsagentExceptionHandler {
//		// Validate Input
//		try {
//			validateUserName(userName);
//			validatePassword(password);
//
//		} catch (NewsagentExceptionHandler e) {
//			throw e;
//		}
//
//		// Set Attributes
//		this.userName = userName;
//		this.password = password;
//	}

	public static String validateUserName(String userName) throws NewsagentExceptionHandler {
		if (userName.isBlank() || userName.isEmpty())
			throw new NewsagentExceptionHandler("Newsagent username NOT specified");
		else if (userName.length() < 6)
			throw new NewsagentExceptionHandler("Newsagent username does not meet minimum length requirements");
		else if (userName.length() > 16)
			throw new NewsagentExceptionHandler("Newsagent username exceeds the maximum length requirements");
		else
			return userName;

	}

	public static String validatePassword(String password) throws NewsagentExceptionHandler {
		if (password.isBlank() || password.isEmpty())
			throw new NewsagentExceptionHandler("Newsagent password NOT specified");
		else if (password.length() < 6)
			throw new NewsagentExceptionHandler("Newsagent password does not meet minimum length requirements");
		else if (password.length() > 30)
			throw new NewsagentExceptionHandler("Newsagent password exceeds the maximum length requirements");
		else
			return password;

	}

	public static String validateFirstName(String firstName) throws NewsagentExceptionHandler {
		if (firstName.isBlank() || firstName.isEmpty())
			throw new NewsagentExceptionHandler("Newsagent first name NOT specified");
		else if (firstName.length() < 3)
			throw new NewsagentExceptionHandler("Newsagent first name does not meet minimum length requirements");
		else if (firstName.length() > 20)
			throw new NewsagentExceptionHandler("Newsagent first name exceeds the maximum length requirements");
		else
			return firstName;

	}

	public static String validateLastName(String lastName) throws NewsagentExceptionHandler {
		if (lastName.isBlank() || lastName.isEmpty())
			throw new NewsagentExceptionHandler("Newsagent last name NOT specified");
		else if (lastName.length() < 3)
			throw new NewsagentExceptionHandler("Newsagent last name does not meet minimum length requirements");
		else if (lastName.length() > 20)
			throw new NewsagentExceptionHandler("Newsagent last name exceeds the maximum length requirements");
		else
			return lastName;
	}

	public static String validateEmail(String email) throws NewsagentExceptionHandler {
		if (email.isBlank() || email.isEmpty())
			throw new NewsagentExceptionHandler("Email NOT specified");
		else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
			throw new NewsagentExceptionHandler("Invalid email format");
		else
			return email;
	}

	public static String validateContactNumber(String contactNumber) throws NewsagentExceptionHandler {
		if (contactNumber.isBlank() || contactNumber.isEmpty())
			throw new NewsagentExceptionHandler("Contact number NOT specified");
		else if (!contactNumber.matches("\\d{10}")) // checks if the contact number is a 10 digit number
			throw new NewsagentExceptionHandler("Invalid contact number format, 10 digits are required");
		else
			return contactNumber;
	}

}
