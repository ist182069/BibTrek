package inesc_id.gsd.bibtrek.app.exceptions;

public class PublicationSearchException extends Exception {
	
	public PublicationSearchException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public PublicationSearchException(String errorMessage) {
        super(errorMessage);
    }
}
