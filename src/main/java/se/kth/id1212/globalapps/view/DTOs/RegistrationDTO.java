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
