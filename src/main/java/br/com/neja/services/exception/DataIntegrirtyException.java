package br.com.neja.services.exception;

public class DataIntegrirtyException extends RuntimeException{

	 
	private static final long serialVersionUID = 1L;
	
	public DataIntegrirtyException(String msg) {
		super(msg);
	}
	
	public DataIntegrirtyException(String msg,Throwable cause) {
		super(msg,cause);
	}

}
