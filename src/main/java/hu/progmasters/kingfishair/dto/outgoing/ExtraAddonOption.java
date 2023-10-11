package hu.progmasters.kingfishair.dto.outgoing;

import hu.progmasters.kingfishair.domain.ExtraAddon;

public class ExtraAddonOption {
    private String name;
    private String displayName;
    private Long price;

    public ExtraAddonOption(ExtraAddon extraAddon) {
        this.name = extraAddon.toString();
        this.displayName = extraAddon.getDisplayName();
        this.price = extraAddon.getPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
