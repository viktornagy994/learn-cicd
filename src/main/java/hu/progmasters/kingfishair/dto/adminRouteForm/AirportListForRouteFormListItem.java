package hu.progmasters.kingfishair.dto.adminRouteForm;

import hu.progmasters.kingfishair.domain.Airport;

public class AirportListForRouteFormListItem {

    private Long id;

    private String iata;

    private String name;

    private String city;

    private String country;



    public AirportListForRouteFormListItem() {
    }

    public AirportListForRouteFormListItem(Airport airport) {
        this.id      = airport.getId();
        this.iata    = airport.getIata();
        this.name    = airport.getName();
        this.city    = airport.getCity();
        this.country = airport.getCountry();
    }



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIata() {
        return this.iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
