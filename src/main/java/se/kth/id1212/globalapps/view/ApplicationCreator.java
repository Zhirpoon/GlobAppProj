package se.kth.id1212.globalapps.view;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
public class ApplicationCreator implements Serializable {

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
     */
    @PostConstruct
    public void init() {
        expertises = controller.getAllExpertises();
        application = new Application(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
    }

    /**
     * @param startDate The start date of an availability period.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @param endDate The end date of an availability period.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return The start date of an availability period.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return The end date of an availability period.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Creates an availability period, <code>TimePeriodDTO</code>, by getting
     * the set start and end dates and adding them to the
     *  <code>Application</code>.
     */
    public void addAvailabilityPeriod() {
        TimePeriodDTO availabilityPeriod = new TimePeriodDTO(startDate, endDate);
        this.application.addAvailabilityPeriod(availabilityPeriod);
        startDate = null;
        endDate = null;
    }

    /**
     * @param years The amount of years for an expertise to be added to
     * <code>YearsWithExpertiseDTO</code>.
     */
    public void setYears(Integer years) {
        this.years = years;
    }

    /**
     * @return The amount of years for an expertise to be added to
     * <code>YearsWithExpertiseDTO</code>.
     */
    public Integer getYears() {

        return years;
    }

    /**
     * @return An expertise to be added to <code>YearsWithExpertiseDTO</code>.
     */
    public String getExpertise() {
        return expertise;
    }

    /**
     * @param expertise An expertise to be added to
     * <code>YearsWithExpertiseDTO</code>.
     */
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    /**
     * Creates <code>YearsWithExpertiseDTO</code> by getting the set expertise
     * and the set years of experience and adding them to the
     *  <code>Application</code>.
     */
    public void addYearsWithExpertise() {
        YearsWithExpertiseDTO yearsWithExpertise = new YearsWithExpertiseDTO(years, expertise);
        application.addExpertises(yearsWithExpertise);
        expertise = null;
        years = null;
    }

    /**
     * Sends the <code>ApplicationDTO</code> to the controller to be saved in
     * the database.
     */
    public void sendApplication() {
        try {
            controller.saveApplication(application);
        } catch (Exception sendApplicationException) {
            System.out.println(sendApplicationException.getMessage());
        }
    }

    /**
     * @return An array of all <code>ExpertiseEntity</code> names stored in the
     * database.
     */
    public String[] getExpertises() {
        return expertises;
    }

    /**
     * @return An array of all the added <code>TimePeriod</code>s, ie all
     * periods of availability, to the application to save..
     */
    public TimePeriodDTO[] getAvailiabilityPeriods() {
        return this.application.getAvailabilityPeriods();
    }

    /**
     * @return An array of all the added <code>YearsWithExpertiseDTO</code> to
     * the application to save.
     */
    public YearsWithExpertiseDTO[] getYearswithExpertises() {
        return this.application.getExpertises();
    }

}
