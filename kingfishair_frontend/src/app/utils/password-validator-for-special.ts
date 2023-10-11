import {AbstractControl, ValidatorFn} from "@angular/forms";

export function PasswordValidatorForSpecial(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: boolean } | null => {
    let numbersAndPlus = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–:;',?~$^=<>]).{8,20}$/
    if (!control.value.match(numbersAndPlus)) {
      return {'PasswordNotAllowedSpecial': true};
    }
    return null;
  };
}
