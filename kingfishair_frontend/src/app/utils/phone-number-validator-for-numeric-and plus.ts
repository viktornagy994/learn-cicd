import {AbstractControl, ValidatorFn} from "@angular/forms";

export function PhoneNumberValidatorForNumericAndPlus(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: boolean } | null => {
    let numbersAndPlus = /^[0-9+]+$/;
    if (!control.value.match(numbersAndPlus)) {
      return {'PhoneNumberNotAllowed': true};
    }
    return null;
  };
}
