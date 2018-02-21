package se.kth.id1212.globalapps.view;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import se.kth.id1212.globalapps.controller.Controller;
import se.kth.id1212.globalapps.dtos.ApplicationDTO;
import se.kth.id1212.globalapps.view.DTOs.ApplicationSearch;
import se.kth.id1212.globalapps.view.DTOs.TimePeriodDTO;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
@Named(value = "applicationLister")
@SessionScoped
public class ApplicationLister implements Serializable{

    @EJB 
    Controller contr;
    ApplicationSearch search;
    Date startDate;
    Date endDate;
    ApplicationDTO[] applications;

    /**
     * Creates a new instance of ApplicationLister
     */
    public ApplicationLister() {
        search = new ApplicationSearch();
    }
    
    public void johansDummyFunction(){
        contr.johansDummyFunction();
    }
    
    public void search(){
        applications = contr.searchApplications(search);
        search = null;
    }

    public void addTimePeriod(){
        this.search.setAvailabiltyPeriod(new TimePeriodDTO(startDate, endDate));
    }
    
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setCompetence(String competence) {
        this.search.addCompentece(competence);
    }

    public void setFirstname(String firstname) {
        this.search.setApplicantFirstName(firstname);
    }

    public String getFirstName() {
        return this.search.getApplicantFirstname();
    }

    public void setLastName(String lastname) {
        this.search.setApplicantLastName(lastname);
    }

    public String getLastName() {
        return this.search.getApplicantLastname();
    }

}
