package hu.progmasters.kingfishair.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "airport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @NotNull
    private String iata;

    @Column
    @NotNull
    private String name;
    @Column
    @NotNull
    private String city;
    @Column
    @NotNull
    private String country;

    @OneToMany(mappedBy = "airport")
    private List<Image> imageList;

    @OneToMany(mappedBy = "departureAirport")
    private List<Route> departureRouteList;

    @OneToMany(mappedBy = "arrivalAirport")
    private List<Route> arrivalRouteList;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String airportUrl;


    public Airport(Long id, String iata, String name, String city, String country, List<Image> imageList, List<Route> departureRouteList, List<Route> arrivalRouteList, String airportUrl) {
        this.id = id;
        this.iata = iata;
        this.name = name;
        this.city = city;
        this.country = country;
        this.imageList = imageList;
        this.departureRouteList = departureRouteList;
        this.arrivalRouteList = arrivalRouteList;
        this.airportUrl = airportUrl;
    }

    public Airport() {

    }

    public String getAirportUrl() {
        return airportUrl;
    }

    public void setAirportUrl(String airportUrl) {
        this.airportUrl = airportUrl;
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

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public List<Route> getDepartureRouteList() {
        return departureRouteList;
    }

    public void setDepartureRouteList(List<Route> departureRouteList) {
        this.departureRouteList = departureRouteList;
    }

    public List<Route> getArrivalRouteList() {
        return arrivalRouteList;
    }

    public void setArrivalRouteList(List<Route> arrivalRouteList) {
        this.arrivalRouteList = arrivalRouteList;
    }
}
