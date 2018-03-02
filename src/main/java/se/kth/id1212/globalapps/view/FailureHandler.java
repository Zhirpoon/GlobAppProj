package se.kth.id1212.globalapps.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
public class FailureHandler {

    private Exception failure;
    private String failedAttribute;
    private boolean success = true;
    
    public void handleFailure(String failureMessage) {
        displayFailureMessage();
        success = false;
    }

    public void handleFailure(String failureMessage, String failedAttribute) {
        this.failedAttribute = failedAttribute;
        handleFailure(failureMessage);

    }

    private void displayFailureMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(failure.getMessage());
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(failedAttribute, msg);
    }

    public boolean getSuccess() {
        return success;
    }

}
