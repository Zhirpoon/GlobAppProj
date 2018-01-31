package se.kth.id1212.globalapps.view;


import java.io.Serializable;
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
      Controller controler;
    /**
     * Creates a new instance of AccountHandler
     */

    
}
