package se.kth.id1212.globalapps.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import se.kth.id1212.globalapps.integration.DBAO;
import se.kth.id1212.globalapps.view.LoginCredentialsDTO;
import se.kth.id1212.globalapps.view.RegistrationDTO;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
@Stateless
public class Controller {
    
    @EJB
    DBAO dbao;
    
    public void register(RegistrationDTO registrationDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void login(LoginCredentialsDTO loginCredentialsDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
