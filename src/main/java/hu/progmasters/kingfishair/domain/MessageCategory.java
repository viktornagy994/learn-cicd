package hu.progmasters.kingfishair.domain;

public enum MessageCategory {


    COMPENSATION("Compensation" ),
    DATAMODIFY("Personal data modify request"),

    RESERVATIONMODIFY("Reservation modify request"),

    BAGCOMPLAINT("Baggage complaint"),
    PAYMENTCOMPLAINT("Payment complaint"),
    GENERALINFORMATION("General questions"),
    DATAPROTECTION("Data protection related requests"),
    SCHEDULECHANGE("Schedule change");


    private String displayName;

    MessageCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
