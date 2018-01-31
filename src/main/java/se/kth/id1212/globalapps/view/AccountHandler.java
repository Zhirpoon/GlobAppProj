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

    public void register(){
        controller.register(new RegistrationDTO(firstName, lastName, email, username, password));
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
          
      
    
}
