package se.kth.id1212.globalapps.view.DTOs;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
public class YearsWithExpertiseDTO implements se.kth.id1212.globalapps.dtos.YearsWithExpertiseDTO{
    private final String expertise;
    private final int years;

    /**
     * Constructor of <code>YearsWithExpertiseDTO</code> with specified years of experience and expertise name.
     * @param expertise An <code>ExpertiseEntity</code>'s name taken from the database.
     * @param years The amount of years of experience for said expertise.
     */
    public YearsWithExpertiseDTO(int years, String expertise) {
        this.expertise = expertise;
        this.years = years;
    }
    
    @Override
    public String getExpertise() {
        return expertise;
    }

    @Override
    public int getYears() {
        return years;
    }
    
}
