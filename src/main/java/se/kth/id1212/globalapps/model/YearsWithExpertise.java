package se.kth.id1212.globalapps.model;

import se.kth.id1212.globalapps.dtos.YearsWithExpertiseDTO;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
public class YearsWithExpertise implements YearsWithExpertiseDTO{
    private final ExpertiseEntity expertise;
    private final int yearsOfExperience;
    
    /**
     * Constructor of <code>YearsWithExpertise</code> for the specific expertise with a certain amount of experience.
     * @param expertise The newly created <code>YearsWithExpertise</code>'s expertise.
     * @param yearsOfExperience The newly created <code>YearsWithExpertise</code>'s experience in years for the specific expertise.
     */
    public YearsWithExpertise(String expertise, int yearsOfExperience) {
        this.expertise = new ExpertiseEntity(expertise);
        this.yearsOfExperience = yearsOfExperience;
    }
    
    @Override
    public String getExpertise() {
        return expertise.getExpertiseName();
    }
    
    @Override
    public int getYears() {
        return yearsOfExperience;
    }    
}
