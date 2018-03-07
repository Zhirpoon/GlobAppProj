package se.kth.id1212.globalapps.view.DTOs;

import java.util.Date;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
public class RegistrationDTO extends LoginCredentialsDTO implements se.kth.id1212.globalapps.dtos.RegistrationDTO {

    private final String firstname;
    private final String Lastname;
    private final String mail;
    private final Date dateOfBirth;

    /**
     * Constructor of <code>RegistrationDTO</code> with specified first name, last name, email, username, password, and date of birth.
     * @param firstname The first name of the user to be registered.
     * @param Lastname The last name of the user to be registered.
     * @param mail The email of the user to be registered.
     * @param username The username of the user to be registered.
     * @param password The password of the user to be registered.
     * @param dateOfBirth The date of birth of the user to be registered.
     */
    public RegistrationDTO(String firstname, String Lastname, String mail, String username, String password, Date dateOfBirth) {
        super(username, password);
        this.firstname = firstname;
        this.Lastname = Lastname;
        this.mail = mail;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String getFirstname() {
        return firstname;
    }

    @Override
    public String getLastname() {
        return Lastname;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public Date getDateOfBirth() {
        return dateOfBirth;
    }    
}
