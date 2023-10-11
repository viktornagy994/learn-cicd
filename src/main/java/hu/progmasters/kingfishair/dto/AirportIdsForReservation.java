package hu.progmasters.kingfishair.dto;

import java.time.LocalDateTime;

public class AirportIdsForReservation {
    private Long departureAirportId;
    private Long arrivalAirportId;

    private LocalDateTime datePicker;

    public AirportIdsForReservation() {
    }

    public Long getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(Long departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public Long getArrivalAirportId() {
        return arrivalAirportId;
    }

    public void setArrivalAirportId(Long arrivalAirportId) {
        this.arrivalAirportId = arrivalAirportId;
    }

    public LocalDateTime getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(LocalDateTime datePicker) {
        this.datePicker = datePicker;
    }
}
