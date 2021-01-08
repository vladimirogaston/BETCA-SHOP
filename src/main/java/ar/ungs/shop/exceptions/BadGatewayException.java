package ar.ungs.shop.exceptions;

public class BadGatewayException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 472808932799460595L;
	private static final String DESCRIPTION = "Bad Gateway Exception";

	public BadGatewayException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}

}
