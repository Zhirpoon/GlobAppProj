package se.kth.id1212.globalapps.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import se.kth.id1212.globalapps.integration.DBAO;
import se.kth.id1212.globalapps.model.UserEntity;
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
        String pass = registrationDTO.getUsername();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            dbao.addUser(new UserEntity(registrationDTO, digest.digest(pass.getBytes(StandardCharsets.UTF_8))));
        } catch (NoSuchAlgorithmException | SQLIntegrityConstraintViolationException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void login(LoginCredentialsDTO loginCredentialsDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
