package se.kth.id1212.globalapps.dtos;

import java.util.Date;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
public interface UserDTO {
    
    /**
     * @return The <code>UserDTO</code>'s registration date entry.
     */
    public Date getRegistrationDate();
    
    /**
     * @return The <code>UserDTO</code>'s  first name entry. 
     */
    public String getFirstname();
    
    /**
     * @return The <code>UserDTO</code>'s last name entry. 
     */
    public String getLastname();
}
