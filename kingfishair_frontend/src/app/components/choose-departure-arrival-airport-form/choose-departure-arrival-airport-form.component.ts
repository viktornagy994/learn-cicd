import {Component, OnInit} from '@angular/core';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {ReservationService} from "../../services/reservation.service";
import {AirportOptionForReservationModel} from "../../models/airport-option-for-reservation-model";
import {CommonModule, NgForOf} from "@angular/common";
import {AirportIdsForReservationModel} from "../../models/airport-ids-for-reservation-model";
import {FlightOptionForReservationFormModel} from "../../models/flight-option-for-reservation-form-model";
import {MatTableModule} from "@angular/material/table";
import {MatIconModule} from "@angular/material/icon";
import {isAuthenticatedExp} from "../../utils/isAuthenticatedExp";
import {LoginComponent} from "../user.handler.components/login/login.component";
import {isNotAuthenticated} from "../../utils/isNotAuthenticated";
import {MatDialog} from "@angular/material/dialog";
import {MatDatepickerModule} from "@angular/material/datepicker";


@Component({
  selector: 'app-choose-departure-arrival-airport-form',
  templateUrl: './choose-departure-arrival-airport-form.component.html',
  styleUrls: ['./choose-departure-arrival-airport-form.component.css'],
  standalone: true,
  imports: [MatSelectModule,
    MatInputModule, MatFormFieldModule,
    MatButtonModule, NgForOf, ReactiveFormsModule,
    MatTableModule, CommonModule, MatIconModule, MatDatepickerModule],
})
export class ChooseDepartureArrivalAirportFormComponent implements OnInit {
  chooseAirportForm: FormGroup
  airports!: AirportOptionForReservationModel[];
  flights: FlightOptionForReservationFormModel[] = [];
  displayedColumns: string[] = ['flightNumber', 'stDeparture', 'stArrival', 'planeName', 'freeSeats'];


  constructor(private formBuilder: FormBuilder,
              private reservationService: ReservationService,
              private router: Router,
              private dialog:MatDialog) {
    this.chooseAirportForm = this.formBuilder.group({
      departureAirportId: ['', Validators.required],
      arrivalAirportId: ['', Validators.required],
      datePicker:['', Validators.required]
    })
  }


  ngOnInit(): void {
    this.reservationService.getInitialFormData().subscribe((formInitData) => {
      this.airports = formInitData;
    });
  }

  getFligths() {
      const data: AirportIdsForReservationModel = {...this.chooseAirportForm.value};
      this.reservationService.getFlightListForReservationForm(data).subscribe({
        next: value => {
          this.flights = value;
          /*this.chooseAirportForm.reset();*/
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
        }
      })
    }

  formatDate(date:string){
    return new Date(date).toLocaleDateString()

  }  formatTime(date:string){
    return new Date(date).toLocaleTimeString()

  }
  goToReservationForm(id: number) {
    if(isAuthenticatedExp()) {
      this.router.navigate(['reservation-form', id]);
    }else{
      this.dialog.open(LoginComponent);
    }
  }


  protected readonly isAuthenticatedExp = isAuthenticatedExp;
  protected readonly isNotAuthenticated = isNotAuthenticated;
}
