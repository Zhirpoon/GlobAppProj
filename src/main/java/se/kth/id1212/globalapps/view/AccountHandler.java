package se.kth.id1212.globalapps.view;


import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import se.kth.id1212.globalapps.controller.Controller;


/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
@Named(value = "accountHandler")
@RequestScoped
public class AccountHandler {

      @EJB
      Controller controller;
    /**
     * Creates a new instance of AccountHandler
     */
      private String username;
      private String password;
      private String firstName;
      private String lastName;
      private String email;
      private Exception failure;

    public void register(){
        try{
        controller.register(new RegistrationDTO(firstName, lastName, email, username, password));
        System.out.println(firstName + lastName + email + username + password);
        }
        catch(Exception e){
            handleException(e);
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
      
    public void login(){
        try{
        controller.login(new LoginCredentialsDTO(username, password));
        }
        catch(Exception e){
            handleException(e);
        }
    }

 

    public void setUsername(String username) {
        this.username = username;
        System.out.println("Username: " + username);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private void handleException(Exception e) {
       failure = e;
    }
          
   public boolean getSuccess(){
       return failure == null;
   }   
   
   public String getFailureReason(){
       return failure.getMessage();
   }
   
}
