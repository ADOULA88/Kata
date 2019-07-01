package com.kata.yatzi.exception;

/**
 * 
 * Classe exception
 *
 */
public class DiceException extends Exception {

	public DiceException() {
		super();
	}

	public DiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public DiceException(String message) {
		super(message);
	}

	public DiceException(Throwable cause) {
		super(cause);
	}
}
