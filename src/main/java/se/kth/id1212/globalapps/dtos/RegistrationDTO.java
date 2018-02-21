package se.kth.id1212.globalapps.dtos;

import java.util.Date;

/**
 *
 * @author Diaco Uthman
 */
public interface RegistrationDTO extends LoginCredentialsDTO {
    public String getFirstname();
    public String getLastname();
    public String getMail();
    public Date getDateOfBirth();
}
