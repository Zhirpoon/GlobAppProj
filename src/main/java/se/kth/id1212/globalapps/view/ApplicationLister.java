package se.kth.id1212.globalapps.view;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
@Named(value = "applicationLister")
@Dependent
public class ApplicationLister {

    /**
     * Creates a new instance of ApplicationLister
     */
    public ApplicationLister() {
    }
    
}
