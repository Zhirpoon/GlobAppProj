package se.kth.id1212.globalapps.model;

import se.kth.id1212.globalapps.dtos.YearsWithExpertiseDTO;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
public class YearsWithExpertise {
    private final ExpertiseEntity expertise;
    private final int yearsOfExperience;
    
    public YearsWithExpertise(YearsWithExpertiseDTO expertisesDTO) {
        this.expertise = new ExpertiseEntity(expertisesDTO.getExpertises());
        this.yearsOfExperience = expertisesDTO.getYears();
    }

    public String getExpertise() {
        return expertise.getExpertiseName();
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }    
}
