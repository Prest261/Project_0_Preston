package dev.preston.exceptions;

public interface UsernameValidator  {

	boolean validUsername(String username) throws UsernameIncorrectLengthException, ChararacterNotAllowedException;
}
