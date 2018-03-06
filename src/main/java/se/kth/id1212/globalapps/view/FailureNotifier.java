package se.kth.id1212.globalapps.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Provides error handling for the managed beans and prints out error messages
 * to the client
 *
 */
public class FailureNotifier {

    private String failureMessage;
    private String failedAttribute;
    private boolean success = true;

    /**
     * Informs the client that a failure has occurred
     *
     */
    public void notifyClient() {
        notifyClient("Something went wrong, please try again");
    }

    /**
     * Displays an error message at the client
     *
     * @param failureMessage The error message to display at the client
     */
    public void notifyClient(String failureMessage) {
        notifyClient(failureMessage, "base");
    }

    /**
     * Displays an error message at the client
     *
     * @param failureMessage The error message to display to the client
     * @param failedAttribute The <code>EL</code> name of the failed attribute
     */
    public void notifyClient(String failureMessage, String failedAttribute) {
        success = false;
        this.failureMessage = failureMessage;
        this.failedAttribute =  "form:"+failedAttribute;
        displayFailureMessage();
    }

    /**
     * Provides information if an error has occured at the managed bean
     *
     * @return <code>boolean</code>
     */
    public boolean getSuccess() {
        return success;
    }

    private void displayFailureMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(failureMessage);
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(failedAttribute, msg);
    }

}
