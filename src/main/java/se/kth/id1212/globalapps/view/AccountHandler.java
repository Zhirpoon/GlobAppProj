package se.kth.id1212.globalapps.view;

import java.util.Date;
import se.kth.id1212.globalapps.view.DTOs.LoginCredentialsDTO;
import se.kth.id1212.globalapps.view.DTOs.RegistrationDTO;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
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
    /**
     * Creates a new instance of AccountHandler
     */
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Exception failure;
    private String userGroup;
    private Date dateOfBirth;

    public String getUserGroup() {
        return controller.getUsergroup(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
    }

    public void register() {
        try {
            controller.register(new RegistrationDTO(firstName, lastName, email, username, password, dateOfBirth));
        } catch (Exception e) {
            handleException(e);
        }
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFailureReason() {
        return failure.getMessage();
    }

    private void handleException(Exception e) {
        failure = e;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getSuccess() {
        return failure == null;
    }

}
