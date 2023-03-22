package api.genderize.genders;
import java.io.Serializable;

public class GenderData implements Serializable{
    private Integer count;
    private String gender;
    private String name;
    private Float probability;

    public GenderData(Integer count, String gender, String name, Float probability) {
        this.count = count;
        this.gender = gender;
        this.name = name;
        this.probability = probability;
    }

    public GenderData(){
        super();
    }

    // All Getters
    public Integer getCount() {
        return count;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public Float getProbability() {
        return probability;
    }


    // All Setters
    public void setCount(Integer count) {
        this.count = count;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProbability(Float probability) {
        this.probability = probability;
    }

    public boolean isMale(){
        return "male".equals(this.getGender());
    }
    public boolean isFemale(){
        return "female".equals(this.getGender());
    }

    public boolean isVladislavName(String alphabet){
        if (alphabet.equals("latin")){
            return QueryParameters.valueLatinName.equals(this.getName());
        }

        if (alphabet.equals("cyrillic")){
            return QueryParameters.valueCyrillicName.equals(this.getName());
        }
        else {return false;}
    }

    public boolean isProbabilityForVladislav(String alphabet){
        if (alphabet.equals("latin")) {
            return ResponseValues.valueProbabilityForVladislav.equals(this.getProbability());
        }
        if (alphabet.equals("cyrillic")){
            return ResponseValues.valueProbabilityForCyrillicVladislav.equals(this.getProbability());
        }
        else {return false;}
    }

    public boolean isCountForVladislav(String alphabet){
        if (alphabet.equals("latin")) {
            return ResponseValues.valueCountForVladislav.equals(this.getCount());
        }
        if (alphabet.equals("cyrillic")){
            return ResponseValues.valueCountForCyrillicVladislav.equals(this.getCount());
        }
        else {return false;}
    }
}
