package se.kth.id1212.globalapps.view;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
@Named(value = "accountHandler")
@RequestScoped
public class AccountHandler {

    /**
     * Creates a new instance of AccountHandler
     */
    public AccountHandler() {
    }
    
}
