package se.kth.id1212.globalapps.view;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import javax.inject.Named;
import se.kth.id1212.globalapps.controller.Controller;
import se.kth.id1212.globalapps.view.DTOs.Application;
import se.kth.id1212.globalapps.view.DTOs.TimePeriodDTO;
import se.kth.id1212.globalapps.view.DTOs.YearsWithExpertiseDTO;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
@Named(value = "applicationCreator")
@SessionScoped
public class ApplicationCreator implements Serializable{

    @EJB
    private Controller controller;
    private String[] expertises;
    private String expertise;
    private Integer years;
    private Application application;
    private Date startDate;
    private Date endDate;

    /**
     * Creates a new instance of ApplicationCreator
     * @return 
     */

    
    @PostConstruct
    public void init(){
                expertises = controller.getAllExpertises();
        application = new Application(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
    }
    
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void addAvailabilityPeriod() {
        TimePeriodDTO availabilityPeriod = new TimePeriodDTO(startDate, endDate);
        this.application.addAvailabilityPeriod(availabilityPeriod);
        startDate=null;
        endDate=null;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public Integer getYears() {
        return years;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public void addYearsWithExpertise() {
        YearsWithExpertiseDTO yearsWithExpertise = new YearsWithExpertiseDTO(years, expertise);
        application.addExpertises(yearsWithExpertise);
        expertise= null;
        years = null;
    }

    public void sendApplication() {
        controller.saveApplication(application);
    }

    public String[] getExpertises() {
        return expertises;
    }

    public TimePeriodDTO[] getAvailiabilityPeriods() {
        return this.application.getAvailabilityPeriods();
    }

    public YearsWithExpertiseDTO[] getYearswithExpertises() {
        return this.application.getExpertises();
    }

}
