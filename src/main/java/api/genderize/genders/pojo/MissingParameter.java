package api.genderize.genders.pojo;

public class MissingParameter {
    public String error;

    public MissingParameter() { super(); }

    public MissingParameter(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
