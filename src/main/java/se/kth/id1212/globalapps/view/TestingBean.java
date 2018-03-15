package se.kth.id1212.globalapps.view;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
@Named(value = "testingBean")
@RequestScoped
public class TestingBean {

    /**
     * Creates a new instance of TestingBean
     */
    public TestingBean() {
    }
    
    /**
     * @return A test String.
     */
    public String getText(){
        return "TextfromTestBean";
    }
    
}
