import {AbstractControl, ValidatorFn} from "@angular/forms";

export function NameValidatorForSpace(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: boolean } | null => {
    if (!control.value.includes(' ')) {
      return {'NameNotAllowed': true};
    }
    return null;
  };
}
