package se.kth.id1212.globalapps.view.DTOs;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
public class LoginCredentialsDTO implements se.kth.id1212.globalapps.dtos.LoginCredentialsDTO{
    /**
     * Username to be tested.
     */
    protected final String username;
    
    /**
     * Password to be tested.
     */
    protected final String password;

    /**
     * Constructor of <code>LoginCredentialsDTO</code> with specified username and specified password.
     * @param username The <code>LoginCredentialsDTO</code>'s entered username.
     * @param password The <code>LoginCredentialsDTO</code>'s entered password.
     */
    public LoginCredentialsDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }


    
    
}
