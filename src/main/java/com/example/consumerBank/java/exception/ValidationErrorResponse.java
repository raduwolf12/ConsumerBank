package com.example.consumerBank.java.exception;

import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class ValidationErrorResponse.
 */
public class ValidationErrorResponse extends ErrorResponse {

	/** The errors. */
	private Map<String, String> errors = new HashMap<String, String>();

	/**
	 * Gets the errors.
	 *
	 * @return the errors
	 */
	public Map<String, String> getErrors() {
		return errors;
	}

	/**
	 * Sets the errors.
	 *
	 * @param errors the errors
	 */
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

}
