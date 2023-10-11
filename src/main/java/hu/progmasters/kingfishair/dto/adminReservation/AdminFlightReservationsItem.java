package hu.progmasters.kingfishair.dto.adminReservation;

import java.util.List;

public class AdminFlightReservationsItem {

    private String userName;
    private String userEmail;
    private String userPhone;
    private String userAddress;
    private List<AdminReservationItem> reservations;

    public AdminFlightReservationsItem() {
    }

    public AdminFlightReservationsItem(String userName, String userEmail, String userPhone, String userAddress, List<AdminReservationItem> reservations) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.reservations = reservations;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public List<AdminReservationItem> getReservations() {
        return reservations;
    }

    public void setReservations(List<AdminReservationItem> reservations) {
        this.reservations = reservations;
    }

}
