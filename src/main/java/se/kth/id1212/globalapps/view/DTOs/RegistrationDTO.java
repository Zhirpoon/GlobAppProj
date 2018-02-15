package se.kth.id1212.globalapps.view.DTOs;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
public class RegistrationDTO extends LoginCredentialsDTO implements se.kth.id1212.globalapps.dtos.RegistrationDTO {

    private final String firstname;
    private final String Lastname;
    private final String mail;

    public RegistrationDTO(String firstname, String Lastname, String mail, String username, String password) {
        super(username, password);
        this.firstname = firstname;
        this.Lastname = Lastname;
        this.mail = mail;
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
    

    
}
