package se.kth.id1212.globalapps.view;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import se.kth.id1212.globalapps.controller.Controller;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
@Named(value = "applicationCreator")
public class ApplicationCreator {

    @EJB
    private Controller controller;
    String[] expertises;
    
    
    /**
             * Creates a new instance of ApplicationCreator
             */

    public ApplicationCreator() {
        expertises = controller.getAllExpertises();
    } 

    public String[] getExpertises(){
        return expertises;
    }
    
    
}
