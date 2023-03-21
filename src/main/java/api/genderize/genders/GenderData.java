package api.genderize.genders;

public class GenderData {
    private Integer count;
    private String gender;
    private String name;
    private Integer probability;

    public GenderData(Integer count, String gender, String name, Integer probability) {
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

    public Integer getProbability() {
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

    public void setProbability(Integer probability) {
        this.probability = probability;
    }
}
