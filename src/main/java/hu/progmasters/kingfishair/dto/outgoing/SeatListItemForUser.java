package hu.progmasters.kingfishair.dto.outgoing;

import hu.progmasters.kingfishair.domain.ExtraAddon;
import hu.progmasters.kingfishair.domain.Seat;

import java.util.List;

public class SeatListItemForUser {

    private String name;

    private String gender;

    private List<ExtraAddon> extraAddons;

    private Long id;

    private Long seatNumber;

    private String identificationNumber;

    public SeatListItemForUser(Seat seat) {
        this.name = seat.getName();
        this.gender = seat.getGender().getGender();
        this.extraAddons = seat.getExtraAddonList();
        this.id=seat.getId();
        this.seatNumber= seat.getSeatNumber();
        this.identificationNumber= seat.getIdentificationNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Long seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
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

    public List<ExtraAddon> getExtraAddons() {
        return extraAddons;
    }

    public void setExtraAddons(List<ExtraAddon> extraAddons) {
        this.extraAddons = extraAddons;
    }
}
