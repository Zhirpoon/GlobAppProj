package se.kth.id1212.globalapps.dtos;

/**
 *
 * @author Diaco Uthman
 */
public interface LoginCredentialsDTO {
    
    /**
     * @return The <code>LoginCredentialsDTO</code>'s username credential entry.
     */
    public String getUsername();
    
    /**
     * @return The <code>LoginCredentialsDTO</code>'s password credential entry.
     */
    public String getPassword();
}
