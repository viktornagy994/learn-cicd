import {GenderOptionModel} from "./gender-option-model";
import {ExtraAddonOptionModel} from "./extra-addon-option-model";

export interface ReservationFormDataModel {
  genders: GenderOptionModel[];
  extraAddons: ExtraAddonOptionModel[];
}
