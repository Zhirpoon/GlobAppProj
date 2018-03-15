package se.kth.id1212.globalapps.dtos;

import java.util.Date;

/**
 *
 * @author Diaco Uthman
 */
public interface RegistrationDTO extends LoginCredentialsDTO {
    
    /**
     * @return The <code>RegistrationDTO</code>'s first name entry. 
     */
    public String getFirstname();
    
    /**
     * @return The <code>RegistrationDTO</code>'s last name entry.
     */
    public String getLastname();
    
    /**
     * @return The <code>RegistrationDTO</code>'s email entry.
     */
    public String getMail();
    
    /**
     * @return The <code>RegistrationDTO</code>'s date of birth entry.
     */
    public Date getDateOfBirth();
}
