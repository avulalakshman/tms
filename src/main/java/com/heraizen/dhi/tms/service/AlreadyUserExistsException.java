package com.heraizen.dhi.tms.service;

public class AlreadyUserExistsException extends Exception {
	private static final long serialVersionUID = 3227560139043008187L;

	public AlreadyUserExistsException() {
		super("User already registred with given email or username"); 
	}
	public AlreadyUserExistsException(String message) {
		super(message);
	}
}
