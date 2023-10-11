import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import {ReservationService} from "../../../services/reservation.service";
import {ReservationFormDataModel} from "../../../models/reservation-form-data-model";
import {GenderOptionModel} from "../../../models/gender-option-model";
import {ExtraAddonOptionModel} from "../../../models/extra-addon-option-model";

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  @Input()
  public childForm!: FormGroup;
  @Input()
  public arrayIndex!: number;
  @Input()
  public totalReservations!: number;
  @Output()
  public deleteReservationEvent: EventEmitter<number> = new EventEmitter<number>();
  genderOptions!: GenderOptionModel[];
  extraAddonOptions!: ExtraAddonOptionModel[];


  constructor(private reservationService: ReservationService) {
  }

  ngOnInit(): void {
    this.reservationService.getReservationFormData().subscribe((reservationFormDataModel: ReservationFormDataModel) => {
      this.genderOptions = reservationFormDataModel.genders;
      this.extraAddonOptions = reservationFormDataModel.extraAddons;
    });
  }

  static addNewReservation(): FormGroup {
    return new FormGroup({
      name: new FormControl('', {validators: [Validators.required, Validators.pattern('(?:[a-zA-Z].*){6,}')]}),

      identificationNumber: new FormControl('', {
        validators: [Validators.required, Validators.minLength(5)
          , Validators.maxLength(20), Validators.pattern('^[A-Za-z0-9]*$')]
      }),
      gender: new FormControl('', {validators: [Validators.required]}),
      extraAddons: new FormControl([]),
      seatNumber: new FormControl(0)
    })
  }

  public deleteReservation(index: number): void {
    this.deleteReservationEvent.next(index);
  }

}
