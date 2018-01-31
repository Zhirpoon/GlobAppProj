package se.kth.id1212.globalapps.dtos;

/**
 *
 * @author Diaco Uthman
 */
public interface RegistrationDTO extends LoginCredentialsDTO {
    public String getFirstname();
    public String getLastname();
    public String getMail();
}
