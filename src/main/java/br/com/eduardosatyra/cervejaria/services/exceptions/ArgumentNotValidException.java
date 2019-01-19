package br.com.eduardosatyra.cervejaria.services.exceptions;

public class ArgumentNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ArgumentNotValidException(String msg) {
		super(msg);
	}

	public ArgumentNotValidException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
