package api.genderize.genders;
import api.genderize.helpers.HelperData;

import java.io.Serializable;

public class GenderData{
    private Integer count;
    private String gender;
    private String name;
    private Float probability;

    public GenderData(int count, String gender, String name, float probability) {
        this.count = count;
        this.gender = gender;
        this.name = name;
        this.probability = probability;
    }
    public GenderData(){
        super();
    }
    // All Getters
    public int getCount() {
        return count;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public float getProbability() {
        return probability;
    }

    public boolean isMale(){
        return ResponseValues.valueGenderMale.equals(this.getGender());
    }
    public boolean isFemale(){
        return ResponseValues.valueGenderFemale.equals(this.getGender());
    }

    public boolean isVladislavName(String alphabet){
        if (alphabet.equals(HelperData.latinName)){
            return QueryParameters.valueLatinName.equals(this.getName());
        }

        if (alphabet.equals(HelperData.cyrillicName)){
            return QueryParameters.valueCyrillicName.equals(this.getName());
        }
        else {return false;}
    }

    public boolean isProbabilityForVladislav(String alphabet){
        if (alphabet.equals(HelperData.latinName)) {
            return ResponseValues.valueProbabilityForVladislav.equals(this.getProbability());
        }
        if (alphabet.equals(HelperData.cyrillicName)){
            return ResponseValues.valueProbabilityForCyrillicVladislav.equals(this.getProbability());
        }
        else {return false;}
    }

    public boolean isCountForVladislav(String alphabet){
        if (alphabet.equals(HelperData.latinName)) {
            return ResponseValues.valueCountForVladislav.equals(this.getCount());
        }
        if (alphabet.equals(HelperData.cyrillicName)){
            return ResponseValues.valueCountForCyrillicVladislav.equals(this.getCount());
        }
        else {return false;}
    }
}
