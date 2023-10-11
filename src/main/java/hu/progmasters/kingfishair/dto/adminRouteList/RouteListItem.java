package hu.progmasters.kingfishair.dto.adminRouteList;

public class RouteListItem {

    private Long id;

    private String depIata;
    private String depName;
    private String depCity;
    private String depCountry;

    private String arrIata;
    private String arrName;
    private String arrCity;
    private String arrCountry;

    private Long distance;

    private Long flightTime;

    private Long ticketFare;

    
    public RouteListItem() {
    }

    public RouteListItem(Long id, String depIata, String depName, String depCity, String depCountry, String arrIata, String arrName, String arrCity, String arrCountry, Long distance, Long flightTime, Long ticketFare) {
        this.id = id;
        this.depIata = depIata;
        this.depName = depName;
        this.depCity = depCity;
        this.depCountry = depCountry;
        this.arrIata = arrIata;
        this.arrName = arrName;
        this.arrCity = arrCity;
        this.arrCountry = arrCountry;
        this.distance = distance;
        this.flightTime = flightTime;
        this.ticketFare = ticketFare;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Long getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Long flightTime) {
        this.flightTime = flightTime;
    }

    public Long getTicketFare() {
        return ticketFare;
    }

    public void setTicketFare(Long ticketFare) {
        this.ticketFare = ticketFare;
    }

}
