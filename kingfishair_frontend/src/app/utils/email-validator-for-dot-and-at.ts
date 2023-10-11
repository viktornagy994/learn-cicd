import {AbstractControl, ValidatorFn} from "@angular/forms";

export function EmailValidatorForDotAndAt(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: boolean } | null => {
    if (!control.value.includes('@', '.')) {
      return {'EmailNotAllowed': true};
    }
    return null;
  };
}
