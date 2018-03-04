package se.kth.id1212.globalapps.view;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Null;
import se.kth.id1212.globalapps.common.exception.CodedException;
import se.kth.id1212.globalapps.common.exception.ExceptionEnumerator;
import se.kth.id1212.globalapps.common.validation.DateNotSet;
import se.kth.id1212.globalapps.common.validation.GeneralStringSize;
import se.kth.id1212.globalapps.common.validation.ValidEmail;
import se.kth.id1212.globalapps.controller.Controller;
import se.kth.id1212.globalapps.view.DTOs.RegistrationDTO;

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

    private DateUtil dateOfBirth = new DateUtil();
    private FailureNotifier failureNotifier;

    @PostConstruct
    public void init() {
        //   this.dateUtilOfBirth = new DateUtil();
        this.failureNotifier = new FailureNotifier();
    }

    /**
     * Creates a <code>RegistrationDTO</code> with data obtained from the web
     * page and sends this to the controller to try and store a new
     * <code>UserEntity</code>.
     */
    public void register() {
        if(!getSuccess()) return;
        try {
            controller.register(new RegistrationDTO(firstName, lastName, email, username, password, dateOfBirth.getDate()));
        } catch (CodedException e) {
            System.out.println(e.getErrorCode().toString());
            if (e.getErrorCode() == ExceptionEnumerator.DUPLICATE_KEY) {
                failureNotifier.notifyClient("Username already exists", "firstName");
            } else {
                failureNotifier.notifyClient();
            }
        } catch (Exception e) {
            failureNotifier.notifyClient();
        }

    }

    /**
     * @return The date of birth entered by the user.
     */
    public String getDateOfBirth() {
        return this.dateOfBirth.getDateString();
//        return this.dateOfBirth;
    }

    /**
     * @param dateOfBirth The user's entered date of birth.
     */
    public void setDateOfBirth(String dateOfBirth) {
        try {
            this.dateOfBirth.setDatefromString(dateOfBirth);
            //     this.dateOfBirth = dateUtilOfBirth.getDateString();
        } catch (DateUtil.DateObjectParsingError ex) {
            StringBuilder sb = new StringBuilder("Enter a date on the form '");
            sb.append(this.dateOfBirth.DATEFORMAT.toLowerCase());
            sb.append("'");
            failureNotifier.notifyClient(sb.toString(), "dateOfBirth");
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
        return failureNotifier.getSuccess();
    }

}
