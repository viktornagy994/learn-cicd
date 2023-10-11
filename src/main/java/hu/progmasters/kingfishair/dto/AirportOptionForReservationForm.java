package hu.progmasters.kingfishair.dto;

import hu.progmasters.kingfishair.domain.Airport;

public class AirportOptionForReservationForm {
    private Long id;

    private String iata;

    private String name;

    private String city;

    private String country;

    public AirportOptionForReservationForm() {
    }


    public AirportOptionForReservationForm(Airport airport) {
        this.id = airport.getId();
        this.iata = airport.getIata();
        this.name = airport.getName();
        this.city = airport.getCity();
        this.country = airport.getCountry();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
