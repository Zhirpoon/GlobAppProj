package se.kth.id1212.globalapps.view;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import se.kth.id1212.globalapps.common.validation.DateNotSet;
import se.kth.id1212.globalapps.common.validation.GeneralStringSize;
import se.kth.id1212.globalapps.common.validation.ValidEmail;
import se.kth.id1212.globalapps.controller.Controller;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
@Named(value = "accountHandler")
@RequestScoped
public class AccountHandler {

    @EJB
    Controller controller;
    

    @GeneralStringSize(message = "Enter a username")
    private String username;
    @GeneralStringSize(message = "Enter a password")
    private String password;
    @GeneralStringSize(message = "Enter a First Name")
    private String firstName;
    
    @GeneralStringSize(message = "Enter a Last Name")
    private String lastName;
    @ValidEmail(message = "Enter a valid email")
    private String email;
    private Exception failure;
    @DateNotSet(message = "Please enter a birth date")
    private DateUtil dateOfBirth;
    

    @PostConstruct
    public void init(){
        this.dateOfBirth = new DateUtil();
    }

    /**
     * Retrieves the name of the <code>AccountTypeEntity</code> for a logged in
     * user.
     *
     * @return The name of the <code>AccountTypeEntity</code>'s name for the
     * user.
     */
    public String getUserGroup() {
        return controller.getUsergroup(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
    }

    /**
     * Creates a <code>RegistrationDTO</code> with data obtained from the web
     * page and sends this to the controller to try and store a new
     * <code>UserEntity</code>.
     */
    public void register() {
        try {
            //throw new UnsupportedOperationException("Not supported yet.");
            //controller.register(new RegistrationDTO(firstName, lastName, email, username, password, dateOfBirth));
        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * @return The date of birth entered by the user.
     */
    public String getDateOfBirth() {
        
        
        return this.dateOfBirth.getDateString();
        
    }

    /**
     * @param dateOfBirth The user's entered date of birth.
     */
    public void setDateOfBirth(String dateOfBirth) {
        try {
            this.dateOfBirth.setDatefromString(dateOfBirth);
        } catch (DateUtil.DateObjectParsingError ex) {
            handleException(ex);
        }
    }

    /**
     * Retrieves the reason for an exception
     *
     * @return The exception message.
     */
    public String getFailureReason() {
        return failure.getMessage();
    }

    /**
     * Sends the exception message to the web browser.
     *
     * @param e The exception caught.
     */
    private void handleException(Exception e) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(e.getMessage());
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage("registerButton", msg);
        failure = e;
    }

    /**
     * @return The username entered by the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return The password entered by the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return The first name entered by the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return The last name entered by the user.
     */
    public String getLastName() {

        return lastName;
    }

    /**
     * @return The email entered by the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param username The username entered by the user.
     */
    public void setUsername(String username) {

        this.username = username;
    }

    /**
     * @param password The password entered by the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param firstName The first name entered by the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param lastName The last name entered by the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param email The email entered by the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return If no exceptions have been caught, used to check that the program
     * can proceed.
     */
    public boolean getSuccess() {
        return failure == null;
    }

}
