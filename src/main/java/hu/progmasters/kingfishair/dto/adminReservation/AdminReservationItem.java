package hu.progmasters.kingfishair.dto.adminReservation;

import hu.progmasters.kingfishair.domain.ExtraAddon;
import hu.progmasters.kingfishair.domain.Seat;

import java.util.ArrayList;
import java.util.List;

public class AdminReservationItem {

    private Long id;
    private String name;
    private String identificationNumber;
    private String gender;
    private List<String> extraAddons;
    private Long seatNumber;

    public AdminReservationItem() {
    }

    public AdminReservationItem(Long id, String name, String identificationNumber, String gender, List<String> extraAddons, Long seatNumber) {
        this.id = id;
        this.name = name;
        this.identificationNumber = identificationNumber;
        this.gender = gender;
        this.extraAddons = extraAddons;
        this.seatNumber = seatNumber;
    }

    public AdminReservationItem(Seat seat) {
        this.id = seat.getId();
        this.name = seat.getName();
        this.identificationNumber = seat.getIdentificationNumber();
        this.gender = seat.getGender().getGender();
        this.seatNumber = seat.getSeatNumber();
        this.extraAddons = new ArrayList<>();
        for (ExtraAddon extraAddon : seat.getExtraAddonList()) {
            this.extraAddons.add(extraAddon.getDisplayName());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public Long getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Long seatNumber) {
        this.seatNumber = seatNumber;
    }

}
