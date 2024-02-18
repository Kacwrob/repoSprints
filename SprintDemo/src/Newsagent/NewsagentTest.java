package Newsagent;

import junit.framework.TestCase;

public class NewsagentTest extends TestCase {

	// Test #: 1
	// Test Objective: To catch an empty newsagent username
	// Inputs: userName = ""
	// Expected Output: Exception Message: "Newsagent username NOT specified"

	public void testValidateNewsagentUserName001() {
		try {
			// Call method under test
			Newsagent.validateUserName("");
			fail("Exception expected");
		} catch (NewsagentExceptionHandler e) {
			assertEquals("Newsagent username NOT specified", e.getMessage());
		}

	}

	// Test #: 2
	// Test Objective: To catch a newsagent username in range 1-5 chars
	// Inputs: userName = "abc"
	// Expected Output: Exception Message: "Newsagent username does not meet minimum
	// length requirements"

	public void testValidateNewsagentUserName002() {
		try {
			// Call method under test
			Newsagent.validateUserName("abc");
			fail("Exception expected");
		} catch (NewsagentExceptionHandler e) {
			assertEquals("Newsagent username does not meet minimum length requirements", e.getMessage());
		}

	}

	// Test #: 3
	// Test Objective: To catch a newsagent username in range 6-16 chars
	// Inputs: userName = "abcdefgh"
	// Expected Output: abcdefgh

	public void testValidateNewsagentUserName003() {
		try {
			String username = "abcdefgh";

			assertEquals(username, Newsagent.validateUserName(username));

		} catch (NewsagentExceptionHandler e) {
			fail("Exception NOT expected");
		}

	}

	// Test #: 4
	// Test Objective: To catch a newsagent username in range 16-MaxINT chars
	// Inputs: userName = "abcdeabcdeabcdeabcde"
	// Expected Output: Exception Message: "Newsagent username exceeds the maximum
	// length requirements

	public void testValidateNewsagentUserName004() {
		try {
			// Call method under test
			Newsagent.validateUserName("abcdeabcdeabcdeabcde"); // 20 chars
			fail("Exception expected");
		} catch (NewsagentExceptionHandler e) {
			assertEquals("Newsagent username exceeds the maximum length requirements", e.getMessage());
		}

	}

	// Test #: 5
	// Test Objective: To catch an empty newsagent password
	// Inputs: password = ""
	// Expected Output: Exception Message: "Newsagent password NOT specified

	public void testValidateNewsagentPassword001() {
		try {
			// Call method under test
			Newsagent.validatePassword("");
			fail("Exception expected");
		} catch (NewsagentExceptionHandler e) {
			assertEquals("Newsagent password NOT specified", e.getMessage());
		}

	}

	// Test #: 6
	// Test Objective: To catch a newsagent password in range 1-5 chars
	// Inputs: password = "abc12"
	// Expected Output: Exception Message: Newsagent password does not meet minimum
	// length requirements

	public void testValidateNewsagentPassword002() {
		try {
			// Call method under test
			Newsagent.validatePassword("abc12");
			fail("Exception expected");
		} catch (NewsagentExceptionHandler e) {
			assertEquals("Newsagent password does not meet minimum length requirements", e.getMessage());
		}

	}

	// Test #: 7
	// Test Objective: To catch a newsagent password in range 6-30 chars
	// Inputs: password = "abcdef123"
	// Expected Output: abcdef123

	public void testValidateNewsagentPassword003() {
		try {
			String password = "abcdef123";

			assertEquals(password, Newsagent.validatePassword(password));

		} catch (NewsagentExceptionHandler e) {
			fail("Exception NOT expected");
		}

	}

	// Test #: 8
	// Test Objective: To catch a newsagent password in range 31-MaxINT chars
	// Inputs: password = "abc12abc12abc12abc12abc12abc12abc12"
	// Expected Output: Exception Message: Newsagent password exceeds the maximum
	// length requirements

	public void testValidateNewsagentPassword004() {
		try {
			// Call method under test
			Newsagent.validatePassword("abc12abc12abc12abc12abc12abc12abc12"); // 35 chars
			fail("Exception expected");
		} catch (NewsagentExceptionHandler e) {
			assertEquals("Newsagent password exceeds the maximum length requirements", e.getMessage());
		}

	}

	// Test #: 9
	// Test Objective: To catch an empty newsagent first name
	// Inputs: firstName = ""
	// Expected Output: Exception Message: Newsagent first name NOT specified

	public void testValidateNewsagentFirstName001() {
		try {
			// Call method under test
			Newsagent.validateFirstName("");
			fail("Exception expected");
		} catch (NewsagentExceptionHandler e) {
			assertEquals("Newsagent first name NOT specified", e.getMessage());
		}

	}

	// Test #: 10
	// Test Objective: To catch a newsagent first name in range 1-2
	// Inputs: firstName = "J"
	// Expected Output: Exception Message: Newsagent first name does not meet
	// minimum
	// length requirements

	public void testValidateNewsagentFirstName002() {
		try {
			// Call method under test
			Newsagent.validateFirstName("J");
			fail("Exception expected");
		} catch (NewsagentExceptionHandler e) {
			assertEquals("Newsagent first name does not meet minimum length requirements", e.getMessage());
		}

	}

	// Test #: 11
	// Test Objective: To catch a newsagent first name in range 3-20
	// Inputs: firstName = "Jack"
	// Expected Output: Jack

	public void testValidateNewsagentFirstName003() {
		try {
			String firstName = "Jack";

			assertEquals(firstName, Newsagent.validateFirstName(firstName));

		} catch (NewsagentExceptionHandler e) {
			fail("Exception NOT expected");

		}

	}

	// Test #: 12
	// Test Objective: To catch a newsagent first name in range 21-MaxString
	// Inputs: firstName = "JackJackJackJackJackJack"
	// Expected Output: Exception Message: Newsagent first name exceeds the maximum
	// length requirements

	public void testValidateNewsagentFirstName004() {
		try {
			// Call method under test
			Newsagent.validateFirstName("JackJackJackJackJackJack"); // 24 chars
			fail("Exception expected");
		} catch (NewsagentExceptionHandler e) {
			assertEquals("Newsagent first name exceeds the maximum length requirements", e.getMessage());
		}

	}

	// Test #: 13
	// Test Objective: To catch an empty newsagent last name
	// Inputs: lastName = ""
	// Expected Output: Exception Message: Newsagent last name NOT specified

	public void testValidateNewsagentLastName001() {
		try {
			// Call method under test
			Newsagent.validateLastName("");
			fail("Exception expected");
		} catch (NewsagentExceptionHandler e) {
			assertEquals("Newsagent last name NOT specified", e.getMessage());
		}

	}

	// Test #: 14
	// Test Objective: To catch a newsagent last name in range 1-2
	// Inputs: lastName = "S"
	// Expected Output: Exception Message: Newsagent last name does not meet
	// minimum
	// length requirements

	public void testValidateNewsagentLastName002() {
		try {
			// Call method under test
			Newsagent.validateLastName("S");
			fail("Exception expected");
		} catch (NewsagentExceptionHandler e) {
			assertEquals("Newsagent last name does not meet minimum length requirements", e.getMessage());
		}

	}

	// Test #: 15
	// Test Objective: To catch a newsagent last name in range 3-20
	// Inputs: lastName = "Smith"
	// Expected Output: Smith

	public void testValidateNewsagentLastName003() {
		try {
			String lastName = "Smith";

			assertEquals(lastName, Newsagent.validateLastName(lastName));

		} catch (NewsagentExceptionHandler e) {
			fail("Exception NOT expected");

		}

	}

	// Test #: 16
	// Test Objective: To catch a newsagent last name in range 21-MaxString
	// Inputs: lastName = "SmithSmithSmithSmithSmith"
	// Expected Output: Exception Message: Newsagent last name exceeds the maximum
	// length requirements

	public void testValidateNewsagentLastName004() {
		try {
			// Call method under test
			Newsagent.validateLastName("SmithSmithSmithSmithSmith"); // 25 chars
			fail("Exception expected");
		} catch (NewsagentExceptionHandler e) {
			assertEquals("Newsagent last name exceeds the maximum length requirements", e.getMessage());
		}

	}

	// Test #: 17
	// Test Objective: To catch an empty newsagent email
	// Inputs: email = ""
	// Expected Output: Exception Message: Email NOT specified

	public void testValidateNewsagentEmail001() {
		try {
			// Call method under test
			Newsagent.validateEmail("");
			fail("Exception expected");
		} catch (NewsagentExceptionHandler e) {
			assertEquals("Email NOT specified", e.getMessage());
		}
	}

	// Test #: 18
	// Test Objective: To catch an invalid newsagent email format
	// Inputs: email = "invalidemail"
	// Expected Output: Exception Message: Invalid email format

	public void testValidateNewsagentEmail002() {
		try {
			// Call method under test
			Newsagent.validateEmail("invalidemail");
			fail("Exception expected");
		} catch (NewsagentExceptionHandler e) {
			assertEquals("Invalid email format", e.getMessage());
		}
	}

	// Test #: 19
	// Test Objective: To validate a valid newsagent email
	// Inputs: email = "validemail@example.com"
	// Expected Output: validemail@example.com

	public void testValidateNewsagentEmail003() {
		try {
			String email = "validemail@example.com";
			assertEquals(email, Newsagent.validateEmail(email));
		} catch (NewsagentExceptionHandler e) {
			fail("Exception NOT expected");
		}
	}

	// Test #: 20
	// Test Objective: To catch an empty newsagent contact number
	// Inputs: contactNumber = ""
	// Expected Output: Exception Message: Contact number NOT specified

	public void testValidateNewsagentContactNumber001() {
		try {
			// Call method under test
			Newsagent.validateContactNumber("");
			fail("Exception expected");
		} catch (NewsagentExceptionHandler e) {
			assertEquals("Contact number NOT specified", e.getMessage());
		}
	}

	// Test #: 21
	// Test Objective: To catch an invalid newsagent contact
	// Inputs: contactNumber = "087123123"
	// Expected Output: Exception Message: Invalid contact number format, 10 digits
	// are required

	public void testValidateNewsagentContactNumber002() {
		try {
			// Call method under test
			Newsagent.validateContactNumber("087123123");
			fail("Exception expected");
		} catch (NewsagentExceptionHandler e) {
			assertEquals("Invalid contact number format, 10 digits are required", e.getMessage());
		}
	}

	// Test #: 22
	// Test Objective: To catch a valid newsagent contact
	// Inputs: contactNumber = "0871231234"
	// Expected Output: 0871231234

	public void testValidateNewsagentContactNumber003() {
		try {
			String contactNumber = "0871231234";

			assertEquals(contactNumber, Newsagent.validateContactNumber(contactNumber));

		} catch (NewsagentExceptionHandler e) {
			fail("Exception NOT expected");
		}
	}

}
