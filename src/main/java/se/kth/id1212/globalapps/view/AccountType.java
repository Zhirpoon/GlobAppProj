package se.kth.id1212.globalapps.view;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import se.kth.id1212.globalapps.common.exception.CodedException;
import se.kth.id1212.globalapps.common.exception.ExceptionEnumerator;
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
    private boolean dbTimeOut = false;

    private void setUsername() {
        username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    }

    private void setAccountType() {
        if (isLoggedIn() && (accounttype == null)) {
            try {
                accounttype = contr.getUsergroup(username);
                dbTimeOut = false;
            } catch (CodedException ex) {
                if(ex.getErrorCode() == ExceptionEnumerator.TIMEOUT){
                    dbTimeOut = true;
                }
//Logger.getLogger(AccountType.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void setDbTimeOut(boolean dbTimeOut){
       this.dbTimeOut = dbTimeOut;
    }
    
    public boolean getDbTimeOut(){
        return dbTimeOut;
    }
    
    /**
     * Checks if the logged in user has the <code>AccountTypeEntity</code> name "APPLICANT".
     * @return If the user is an applicant or not.
     */
    public boolean getIsApplicant() {
        setUsername();
        setAccountType();
        return "APPLICANT".equals(accounttype);
    }

    /**
     * Checks if the logged in user has the <code>AccountTypeEntity</code> name "RECRUITER".
     * @return If the user is an recruiter or not.
     */
    public boolean getIsRecruiter() {
        setUsername();
        setAccountType();
        return "RECRUITER".equals(accounttype);
    }

    /**
     * @return If the user is logged in or not.
     */
    public boolean isLoggedIn() {
        return username != null;
    }

    /**
     * @return The username of the logged in user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Logs out a logged out user.
     * @return A message saying that logging out was successful.
     */
    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
       // System.out.println("Line 65");
        return "Success";
    }
}
