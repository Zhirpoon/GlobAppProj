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
import se.kth.id1212.globalapps.common.exception.ExceptionEnumerator;
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
    DateUtil startDate = new DateUtil();
    DateUtil endDate = new DateUtil();
    ApplicationDTO[] applications;
    String[] expertises;
    String expertise;
    private final FailureNotifier failureNotifier = new FailureNotifier();

    
    
    public boolean getSuccess() {
        return this.failureNotifier.getSuccess();
    }

    public ApplicationDTO[] getApplications() {
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
            failureNotifier.notifyClient();
        }
    }

    
    
    public void search() {
        try {
            applications = contr.searchApplications(search);
        } catch (CodedException ex) {
            if(ex.getErrorCode() == ExceptionEnumerator.NULL){
                failureNotifier.notifyClient("No mathing applications found");
            }
            else failureNotifier.notifyClient();
        }

    }

    public void addTimePeriod() {
        try {
            this.search.setAvailabiltyPeriod(new TimePeriodDTO(startDate.getDate(), endDate.getDate()));
        } catch (TimePeriodDTO.TimePeriodDTOException ex) {
            failureNotifier.notifyClient(ex.getMessage(), "addTimePeriod");
        }
    }

    public String getStartDate() {
        return startDate.getDateString();
    }

    public void setStartDate(String startDate) {
        if (startDate.length() < 1) {
            return;
        }
        try {
            this.startDate.setDatefromString(startDate);
        } catch (DateUtil.DateObjectParsingError ex) {
            failureNotifier.notifyClient(ex.getMessage(), "startDate");
        }
    }

    public String getEndDate() {
        return endDate.getDateString();
    }

    public void setEndDate(String endDate) {
        if (endDate.length() < 1) {
            return;
        }
        try {
            this.endDate.setDatefromString(endDate);
        } catch (DateUtil.DateObjectParsingError ex) {
            failureNotifier.notifyClient(ex.getMessage(), "endDate");
        }
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
