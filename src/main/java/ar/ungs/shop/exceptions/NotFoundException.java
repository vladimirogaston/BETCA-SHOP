package ar.ungs.shop.exceptions;

public class NotFoundException extends RuntimeException {

    private static final String DESCRIPTION = "Not Found Exception (404)";

    public NotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

    public NotFoundException() {
    	super(DESCRIPTION);
    }
}
