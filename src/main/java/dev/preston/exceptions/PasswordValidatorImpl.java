package dev.preston.exceptions;

public class PasswordValidatorImpl implements PasswordValidator {

	public boolean validPassword(String password) throws MustContainNumberException, PasswordTooShortException {
		
		if(true) {
			// creating count to check for numbers
			int count = 0;
			
			for (int i = 0; i <= 9; i++) {
			//convert to string
			String str1 = Integer.toString(i);
			
			if(password.contains(str1)) {
				count = 1;
			}
		}
		if (count == 0) {
			MustContainNumberException mcne = new MustContainNumberException(password);
			throw mcne;
		}
		}
		if(password.length() < 8) {
			PasswordTooShortException ptse = new PasswordTooShortException(password.length());
			throw ptse;
		}
		return true;
	}

}
