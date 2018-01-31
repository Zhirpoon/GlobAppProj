package se.kth.id1212.globalapps.view;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
public class YearsWithExpertiseDTO implements se.kth.id1212.globalapps.dtos.YearsWithExpertiseDTO{
    private final String expertise;
    private final int years;

    public YearsWithExpertiseDTO(int years, String expertise) {
        this.expertise = expertise;
        this.years = years;
    }
    
    @Override
    public String getExpertises() {
        return expertise;
    }

    @Override
    public int getYears() {
        return years;
    }
    
}
