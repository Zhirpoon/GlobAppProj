package se.kth.id1212.globalapps.view.DTOs;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
public class LoginCredentialsDTO implements se.kth.id1212.globalapps.dtos.LoginCredentialsDTO{
    protected final String username;
    protected final String password;

    
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
