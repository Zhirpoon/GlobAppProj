package se.kth.id1212.globalapps.model;

import se.kth.id1212.globalapps.dtos.YearsWithExpertiseDTO;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
public class YearsWithExpertise implements YearsWithExpertiseDTO{
    private final ExpertiseEntity expertise;
    private final int yearsOfExperience;
    
    public YearsWithExpertise(YearsWithExpertiseDTO expertisesDTO) {
        this.expertise = new ExpertiseEntity(expertisesDTO.getExpertise());
        this.yearsOfExperience = expertisesDTO.getYears();
    }
    
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
