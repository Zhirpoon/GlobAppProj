package se.kth.id1212.globalapps.view;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import javax.inject.Named;
import se.kth.id1212.globalapps.common.exception.CodedException;
import se.kth.id1212.globalapps.common.validation.PositiveTwoDigitInteger;
import se.kth.id1212.globalapps.controller.Controller;
import se.kth.id1212.globalapps.view.DTOs.ApplicationNew;
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

    @PositiveTwoDigitInteger(message = "Enter years between 1 and 99")
    private Integer years;
    private ApplicationNew application;
    private DateUtil startDate = new DateUtil();
    private DateUtil endDate = new DateUtil();
    private FailureNotifier failureNotifier;
//private Date startDate;
    //private Date endDate;

    /**
     * Creates a new instance of ApplicationCreator
     */
    @PostConstruct
    public void init() {
        try {
            expertises = controller.getAllExpertises();
            application = new ApplicationNew(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
            failureNotifier = new FailureNotifier();
        } catch (CodedException ex) {
            Logger.getLogger(ApplicationCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param startDate The start date of an availability period.
     */
    public void setStartDate(String startDate) {

        if (startDate.length() < 1) {
            return;
        }
        try {
            this.startDate.setDatefromString(startDate);
        } catch (DateUtil.DateObjectParsingError ex) {
            failureNotifier.notifyClient(ex.getMessage(), "startDate");
        }
//        System.out.println("----------------------------------------");
//        System.out.println("----------------------------------------");
//        System.out.println("----------------------------------------");
//        System.out.println("StartDate: " + this.startDate.getDateString());
    }

    /**
     * @param endDate The end date of an availability period.
     */
    public void setEndDate(String endDate) {
        if (endDate.length() < 1) {
            return;
        }
        try {
            this.endDate.setDatefromString(endDate);
        } catch (DateUtil.DateObjectParsingError ex) {
            failureNotifier.notifyClient(ex.getMessage(), "endDate");
        }
//        System.out.println("EndDate: " + this.endDate.getDateString());
    }

    /**
     * @return The start date of an availability period.
     */
    public String getStartDate() {
        return startDate.getDateString();
    }

    /**
     * @return The end date of an availability period.
     */
    public String getEndDate() {
        return endDate.getDateString();
    }

    /**
     * Creates an availability period, <code>TimePeriodDTO</code>, by getting
     * the set start and end dates and adding them to the
     * <code>Application</code>.
     */
    public void addAvailabilityPeriod() {
        TimePeriodDTO availabilityPeriod;
        try {
            availabilityPeriod = new TimePeriodDTO(startDate.getDate(), endDate.getDate());
            this.application.addAvailabilityPeriod(availabilityPeriod);
            System.out.println("StartDate: " + startDate.getDateString());
            System.out.println("EndDate: " + endDate.getDateString());
            startDate = new DateUtil();
            endDate = new DateUtil();
        } catch (TimePeriodDTO.TimePeriodDTOException ex) {
            failureNotifier.notifyClient(ex.getMessage(), "addAvailabilityPeriod");
        }

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
     * <code>Application</code>.
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
            failureNotifier.notifyClient();
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
        for (TimePeriodDTO period : application.getAvailabilityPeriods()) {
            System.out.println(period.getStartdate().toString());
            System.out.println(period.getStartdate().getDay());
        }
        return this.application.getAvailabilityPeriods();
    }

    /**
     * @return An array of all the added <code>YearsWithExpertiseDTO</code> to
     * the application to save.
     */
    public YearsWithExpertiseDTO[] getYearswithExpertises() {
        return this.application.getExpertises();
    }

    public boolean getSuccess() {
        return this.failureNotifier.getSuccess();
    }
}
