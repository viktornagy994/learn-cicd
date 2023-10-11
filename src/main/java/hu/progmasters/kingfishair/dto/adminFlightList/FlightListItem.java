package hu.progmasters.kingfishair.dto.adminFlightList;

import java.time.LocalDateTime;

public class FlightListItem {

    private Long id;

    private String flightNumber;

    private String depIata;
    private String depName;
    private String depCity;
    private String depCountry;

    private String arrIata;
    private String arrName;
    private String arrCity;
    private String arrCountry;

    private LocalDateTime stDeparture;

    private LocalDateTime stArrival;

    private String planeRegistrationNr;

    public FlightListItem() {
    }

    public FlightListItem(Long id, String flightNumber, String depIata, String depName, String depCity, String depCountry, String arrIata, String arrName, String arrCity, String arrCountry, LocalDateTime stDeparture, LocalDateTime stArrival, String planeRegistrationNr) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.depIata = depIata;
        this.depName = depName;
        this.depCity = depCity;
        this.depCountry = depCountry;
        this.arrIata = arrIata;
        this.arrName = arrName;
        this.arrCity = arrCity;
        this.arrCountry = arrCountry;
        this.stDeparture = stDeparture;
        this.stArrival = stArrival;
        this.planeRegistrationNr = planeRegistrationNr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepIata() {
        return depIata;
    }

    public void setDepIata(String depIata) {
        this.depIata = depIata;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepCity() {
        return depCity;
    }

    public void setDepCity(String depCity) {
        this.depCity = depCity;
    }

    public String getDepCountry() {
        return depCountry;
    }

    public void setDepCountry(String depCountry) {
        this.depCountry = depCountry;
    }

    public String getArrIata() {
        return arrIata;
    }

    public void setArrIata(String arrIata) {
        this.arrIata = arrIata;
    }

    public String getArrName() {
        return arrName;
    }

    public void setArrName(String arrName) {
        this.arrName = arrName;
    }

    public String getArrCity() {
        return arrCity;
    }

    public void setArrCity(String arrCity) {
        this.arrCity = arrCity;
    }

    public String getArrCountry() {
        return arrCountry;
    }

    public void setArrCountry(String arrCountry) {
        this.arrCountry = arrCountry;
    }

    public LocalDateTime getStDeparture() {
        return stDeparture;
    }

    public void setStDeparture(LocalDateTime stDeparture) {
        this.stDeparture = stDeparture;
    }

    public LocalDateTime getStArrival() {
        return stArrival;
    }

    public void setStArrival(LocalDateTime stArrival) {
        this.stArrival = stArrival;
    }

    public String getPlaneRegistrationNr() {
        return planeRegistrationNr;
    }

    public void setPlaneRegistrationNr(String planeRegistrationNr) {
        this.planeRegistrationNr = planeRegistrationNr;
    }
}
