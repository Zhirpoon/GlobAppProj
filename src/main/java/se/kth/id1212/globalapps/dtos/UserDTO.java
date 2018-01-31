package se.kth.id1212.globalapps.dtos;

import java.util.Date;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
public interface UserDTO {
    public Date getRegistrationDate();
    public String getFirstname();
    public String getLastname();
}
