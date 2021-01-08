package ar.ungs.shop.services;

public class PdfException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5934126934059977360L;
	private static final String DESCRIPTION = "File exception";

	public PdfException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}

}
