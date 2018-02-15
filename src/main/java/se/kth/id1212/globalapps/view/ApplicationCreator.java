package se.kth.id1212.globalapps.view;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
    private int years;
    private Application application;
    private Date startDate;
    private Date endDate;
    
    
    /**
             * Creates a new instance of ApplicationCreator
             */

    public ApplicationCreator() {
        expertises = controller.getAllExpertises();
        application = new Application(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
    } 

    public String[] getExpertises(){
        return expertises;
    }
    
    public void setYears(int years){
        this.years = years;
    }
    
    public void setExpertise(String expertise){
        this.expertise = expertise;
    }
    
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }
    
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }
    
    public se.kth.id1212.globalapps.dtos.TimePeriodDTO[] getAvailiabilityPeriods(){
        return this.application.getAvailabilityPeriods();
    }
    
    public se.kth.id1212.globalapps.dtos.YearsWithExpertiseDTO[] getYearswithExpertises(){
        return this.application.getExpertises();
    } 
    
    public void addAvailabilityPeriod(){
        TimePeriodDTO availabilityPeriod = new TimePeriodDTO(startDate, endDate);
        this.application.addAvailabilityPeriod(availabilityPeriod);
    }
    
    public void addYearsWithExpertise(){
        YearsWithExpertiseDTO yearsWithExpertise = new YearsWithExpertiseDTO(years, expertise);
        application.addExpertises(yearsWithExpertise);
    }
    
   
}
