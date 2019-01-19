package br.com.eduardosatyra.cervejaria.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eduardosatyra
 *
 */
public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> list = new ArrayList<>();

	/**
	 * @param status
	 * @param msg
	 * @param timeStamp
	 */
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessage> getErrors() {
		return list;
	}

	public void addError(String fieldName, String message) {
		list.add(new FieldMessage(fieldName, message));
	}

}
