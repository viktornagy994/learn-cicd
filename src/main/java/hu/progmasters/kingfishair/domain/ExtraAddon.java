package hu.progmasters.kingfishair.domain;

public enum ExtraAddon {

    BAGGAGE("checked-in baggage", 100L ),
    PET("pet", 200L),

    CATERING("food & drinks", 50L)    ;


    private String displayName;

    private Long price;

    ExtraAddon(String displayname, Long price) {
        this.displayName = displayname;
        this.price = price;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Long getPrice() {
        return price;
    }
}
