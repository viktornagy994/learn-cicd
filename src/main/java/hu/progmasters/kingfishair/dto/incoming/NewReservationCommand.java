package hu.progmasters.kingfishair.dto.incoming;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class NewReservationCommand {
    @NotNull(message = "Name cannot be null")
    @Pattern(regexp = "(?:[a-zA-Z].*){6,}", message = "The name field must have at least 6 letter!" )
    private String name;

    @NotNull(message = "The identificationNumber field must not be empty!")
    @Size(min = 5, message = "The identificationNumber filed must be at least five character!")
    @Size(max = 20, message = "The identificationNumber filed must be less than twenty character!")
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "The identificationNumber field can only contain letters & numbers!" )
    private String identificationNumber;
    @NotNull
    private String gender;
    private List<String> extraAddons;

    private Long seatNumber;

    public NewReservationCommand(String name, String identificationNumber, String gender, List<String> extraAddons, Long seatNumber) {

        this.name = name;
        this.identificationNumber = identificationNumber;
        this.gender = gender;
        this.extraAddons = extraAddons;
        this.seatNumber = seatNumber;
    }

    public NewReservationCommand() {
    }

    public String getName() {
        return name;
    }

    public Long getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Long seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getExtraAddons() {
        return extraAddons;
    }

    public void setExtraAddons(List<String> extraAddons) {
        this.extraAddons = extraAddons;
    }

}

