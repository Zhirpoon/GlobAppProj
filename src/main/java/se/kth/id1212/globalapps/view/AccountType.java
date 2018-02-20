package se.kth.id1212.globalapps.view;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import se.kth.id1212.globalapps.controller.Controller;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
@Named(value = "accountType")
@SessionScoped
public class AccountType implements Serializable {
    
    @EJB
    Controller contr;
    /**
     * Creates a new instance of AccountType
     */
    private String accounttype;
    private String username;
    
    public AccountType() {
        username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        if (isLoggedIn()) accounttype = contr.getAccounttype(username);
    }
    
    public boolean getIsApplicant(){
        return "APPLICANT".equals(accounttype);
    }
    public boolean getIsRecruiter(){
        return "RECRUITER".equals(accounttype);
    }
    public boolean isLoggedIn(){
        return username != null;
    }
    public String getUsername(){
        return username;
    }
    
}