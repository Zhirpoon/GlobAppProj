package se.kth.id1212.globalapps.common.exception;

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
}
