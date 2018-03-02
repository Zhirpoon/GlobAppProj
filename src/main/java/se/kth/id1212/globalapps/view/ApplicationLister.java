package se.kth.id1212.globalapps.view;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import se.kth.id1212.globalapps.common.exception.CodedException;
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
//@RequestScoped
public class ApplicationLister implements Serializable {

    @EJB
    Controller contr;
    ApplicationSearch search;
    Date startDate;
    Date endDate;
    ApplicationDTO[] applications;
    String[] expertises;
    String expertise;

    public ApplicationDTO[] getApplications(){
        return applications;
    }
    
    public String[] getExpertises() {
        return expertises;
    }

    public String getExpertise() {
        return this.expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String[] getCompetences() {
        return this.search.getCompetences();
    }

    /**
     * Creates a new instance of ApplicationLister
     */


    @PostConstruct
    public void init() {
        try {
            this.expertises = contr.getAllExpertises();
            search = new ApplicationSearch();
        } catch (CodedException ex) {
            Logger.getLogger(ApplicationLister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void search() {
        try {
            applications = contr.searchApplications(search);
        } catch (CodedException ex) {
            Logger.getLogger(ApplicationLister.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void addTimePeriod() {
        try {
            this.search.setAvailabiltyPeriod(new TimePeriodDTO(startDate, endDate));
        } catch (TimePeriodDTO.TimePeriodDTOException ex) {
            Logger.getLogger(ApplicationLister.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void addCompetence() {
        this.search.addCompentece(expertise);
    }

    public void setFirstName(String firstname) {
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
