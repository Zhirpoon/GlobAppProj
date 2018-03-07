package se.kth.id1212.globalapps.common.exception;

import se.kth.id1212.globalapps.model.Constants.ErrorConstants;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 * Exception class for specific exceptions to be send from controller to view.
 */
public class CodedException extends Exception {
    private ExceptionEnumerator errorCode;
    
    /**
     * Constructor for <code>CodedException</code> with specified error message.
     * @param message 
     */
    public CodedException(String message) {
        super(message);
    }
    
    /**
     * Sets an error code to be used in the view layer for presentation.
     * @param errorCode The <code>CodedError</code>'s error code.
     */
    public void setErrorCode(ExceptionEnumerator errorCode) {
        this.errorCode = errorCode;
    }
    
    /**
     * Gets the error code to be handled in the view layer for presentation.
     * @return  The <code>CodedError</code>'s error code.
     */
    public ExceptionEnumerator getErrorCode() {
        return errorCode;
    }
    
    /**
     * Categorizes the exception given so it can be handled in the right way in the view layer.
     * @param exception The exception to be categorized.
     */
    public void categorizeException(Exception exception) {
        if(exception.getMessage() == null) {
            setErrorCode(ExceptionEnumerator.OTHER);
            return;
        }
        switch(exception.getMessage()) {
            case ErrorConstants.DUPLICATE_KEY:
                setErrorCode(ExceptionEnumerator.DUPLICATE_KEY);
                break;
            case ErrorConstants.CONSTRAINT:
                setErrorCode(ExceptionEnumerator.CONSTRAINT);
                break;
            case ErrorConstants.TIMEOUT:
                setErrorCode(ExceptionEnumerator.TIMEOUT);
                break;
            case ErrorConstants.OUTDATED_VERSION:
                setErrorCode(ExceptionEnumerator.OUTDATED_VERSION);
                break;
            default:
                setErrorCode(ExceptionEnumerator.OTHER);
                break;
        }
    }
}
