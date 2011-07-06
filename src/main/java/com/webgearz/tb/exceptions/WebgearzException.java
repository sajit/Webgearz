package com.webgearz.tb.exceptions;

public class WebgearzException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -55619506785215740L;
	public WebgearzException(String message){
		super(message);
	}
	public WebgearzException(){
		super();
	}

}
