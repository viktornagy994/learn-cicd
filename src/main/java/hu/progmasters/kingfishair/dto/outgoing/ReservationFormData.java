package hu.progmasters.kingfishair.dto.outgoing;

import hu.progmasters.kingfishair.domain.ExtraAddon;

import java.util.List;

public class ReservationFormData {
    private List<GenderOption> genders;
    private List<ExtraAddonOption> extraAddons;

    public ReservationFormData(List<GenderOption> genders, List<ExtraAddonOption> extraAddons) {
        this.genders = genders;
        this.extraAddons = extraAddons;
    }

    public List<GenderOption> getGenders() {
        return genders;
    }

    public void setGenders(List<GenderOption> genders) {
        this.genders = genders;
    }

    public List<ExtraAddonOption> getExtraAddons() {
        return extraAddons;
    }

    public void setExtraAddons(List<ExtraAddonOption> extraAddons) {
        this.extraAddons = extraAddons;
    }
}
