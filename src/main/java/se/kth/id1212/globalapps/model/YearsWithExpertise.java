package se.kth.id1212.globalapps.model;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
public class YearsWithExpertise {
    private final ExpertiseEntity expertise;
    private final int yearsOfExperience;
    
    public YearsWithExpertise(ExpertiseEntity expertise, int yearsOfExperience) {
        this.expertise = expertise;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getExpertise() {
        return expertise.getExpertiseName();
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }    
}
