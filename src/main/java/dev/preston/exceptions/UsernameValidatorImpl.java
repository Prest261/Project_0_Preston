package dev.preston.exceptions;

public class UsernameValidatorImpl implements UsernameValidator {

	public boolean validUsername(String username)
			throws UsernameIncorrectLengthException, ChararacterNotAllowedException {
		
		if(username.contains(" ")) {
			ChararacterNotAllowedException cnae = new ChararacterNotAllowedException(username);
			throw cnae;
		}
		
		if(username.length() < 8) {
			UsernameIncorrectLengthException uile = new UsernameIncorrectLengthException(username.length());
			throw uile;
		}
		return true;
	}

	
}
