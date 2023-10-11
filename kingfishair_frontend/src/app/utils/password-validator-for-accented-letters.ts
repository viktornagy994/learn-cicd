import {AbstractControl, ValidatorFn} from "@angular/forms";

export function PasswordValidatorForRegex(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: boolean } | null => {
    let numbersAndPlus = /[\u00C0-\u00FF]/;
    if (control.value.match(numbersAndPlus)) {
      return {'PasswordNotAllowed': true};
    }
    return null;
  };
}
