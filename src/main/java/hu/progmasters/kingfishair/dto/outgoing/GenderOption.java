package hu.progmasters.kingfishair.dto.outgoing;

import hu.progmasters.kingfishair.domain.Gender;

public class GenderOption {

    private String name;
    private String gender;

    public GenderOption(Gender gender) {
        this.name = gender.toString();
        this.gender = gender.getGender();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
