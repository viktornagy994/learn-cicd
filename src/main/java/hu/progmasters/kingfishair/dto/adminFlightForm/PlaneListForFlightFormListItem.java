package hu.progmasters.kingfishair.dto.adminFlightForm;

import hu.progmasters.kingfishair.domain.Plane;

public class PlaneListForFlightFormListItem {

    private Long id;

    private String registrationNumber;

    private String name;

    private Long numberOfSeats;


    public PlaneListForFlightFormListItem() {
    }

    public PlaneListForFlightFormListItem(Plane plane) {
        this.id                 = plane.getId();
        this.registrationNumber = plane.getRegistrationNumber();
        this.name               = plane.getName();
        this.numberOfSeats      = plane.getNumberOfSeats();
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumberOfSeats() {
        return this.numberOfSeats;
    }

    public void setNumberOfSeats(Long numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
