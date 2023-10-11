import {Component, OnInit} from '@angular/core';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {MatRadioModule} from "@angular/material/radio";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {MatIconModule} from "@angular/material/icon";
import {MatMenuModule} from "@angular/material/menu";
import {MatListModule} from "@angular/material/list";
import {MatSidenavModule} from "@angular/material/sidenav";
import {Router, RouterOutlet} from "@angular/router";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatToolbarModule} from "@angular/material/toolbar";
import {AuthenticationService} from "../../../services/authentication.service";
import {UserService} from "../../../services/user.service";
import {MatTabsModule} from "@angular/material/tabs";
import {UserListItemModel} from "../../../models/user-list-item-model";
import {EditProfileModel} from "../../../models/edit-profile-model";
import {ReservationListItemForUserModel} from "../../../models/reservation-list-item-for-user-model";
import {ReservationService} from "../../../services/reservation.service";
import {DatePipe, NgForOf, NgIf, NgStyle} from "@angular/common";
import {MatTableModule} from "@angular/material/table";
import {MatDialog} from "@angular/material/dialog";
import {
  ArrivalCityMapForAccomodationComponent
} from "../../arrival-city-map-for-accomodation/arrival-city-map-for-accomodation.component";
import {ReservationDetialsComponent} from "../../reservation-detials/reservation-detials.component";
import {QRCodeComponent} from "../../qr-code/qr-code.component";
import {saveAs} from "@progress/kendo-file-saver";


@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css'],
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatRadioModule,
    MatCardModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule, ReactiveFormsModule, MatIconModule, MatMenuModule, MatListModule, MatSidenavModule, RouterOutlet, MatExpansionModule, MatToolbarModule, MatTabsModule, NgForOf, DatePipe, MatTableModule, NgIf, NgStyle]
})
export class MyProfileComponent implements OnInit {

  saveUserForm: FormGroup;
  editable: boolean = false;
  name!: string;

  reservationsForFuture: ReservationListItemForUserModel[] = [];
  reservationsForPast: ReservationListItemForUserModel[] = [];
  reservationsForPending: ReservationListItemForUserModel[] = [];

  displayedColumns: string[] = ['passengerName', 'identificationNumber', 'passengerGender', 'extraAddons', 'seatNumber'];
  displayedColumnsForFuture: string[] = ['passengerName', 'identificationNumber', 'passengerGender', 'extraAddons', 'seatNumber', 'downloadPDF'];
  selectedPlanelId!: number;

  constructor(private authenticationService: AuthenticationService, private formBuilder: FormBuilder,
              private userService: UserService,
              private router: Router,
              private reservationService: ReservationService,
              private dialogRef: MatDialog) {

    this.saveUserForm = this.formBuilder.group({
      email: [''],
      name: [''],
      address: [''],
      phoneNumber: [''],
    })

  }

  ngOnInit() {
    this.authenticationService.findIfAlreadyAuthorized().subscribe(
      response => {
        localStorage.setItem('user', JSON.stringify(response));
        this.name = JSON.parse(localStorage.getItem('user') || '').name;

      },
      () => {
        localStorage.removeItem('user');

      }
    )
    this.getUserDetails();
    this.getPastReservations();
    this.getPendingReservations();
    this.getFutureReservations();
  }

  modifyUser() {
    const data: EditProfileModel = this.saveUserForm.value
    this.userService.updateProfile(data).subscribe();
  }

  getPastReservations() {
    this.reservationService.getMyPastReservations().subscribe({
      next: data => this.reservationsForPast = data,
      error: err => console.log(err)
    })
  }

  getPendingReservations() {
    this.reservationService.getMyPendingReservations().subscribe({
      next: data => this.reservationsForPending = data,
      error: err => console.log(err)
    })
  }

  getFutureReservations() {
    this.reservationService.getMyFutureReservations().subscribe({
      next: data => this.reservationsForFuture = data,
      error: err => console.log(err)
    })

  }


  getUserDetails = () => {
    this.userService.fetchUserDetails().subscribe(
      (response: UserListItemModel) => {
        this.saveUserForm.patchValue({
          email: response.email,
          name: response.name,
          address: response.address,
          phoneNumber: response.phoneNumber,

        });
      },
    );
  }

  getInitials(): string {
    let initialsName: string = '';

    if (this.name != null) {

      let names: string[] = this.name.split(' ');
      let numberOfNames: number = names.length
      if (numberOfNames > 2) {
        numberOfNames = 2;
      }
      for (let i = 0; i < numberOfNames; i++) {
        initialsName = initialsName.concat(names[i].charAt(0))
      }
    }

    return initialsName;
  }

  navigateToMap(stArrival: string) {
    if (stArrival == 'Oslo Gardermoen') {
      this.dialogRef.open(ArrivalCityMapForAccomodationComponent, {
        data: {
          myLat: 60.197057,
          myLong: 11.096218,
        }
      })
    }
    if (stArrival == 'Keflavik International') {
      this.dialogRef.open(ArrivalCityMapForAccomodationComponent, {
        data: {
          myLat: 63.995562,
          myLong: -22.623748,
        }
      })
    }
    if (stArrival == 'Helsinki-Vantaa') {
      this.dialogRef.open(ArrivalCityMapForAccomodationComponent, {
        data: {
          myLat: 60.317114,
          myLong: 24.949618,
        }
      })
    }
    if (stArrival == 'Arlanda Airport') {
      this.dialogRef.open(ArrivalCityMapForAccomodationComponent, {
        data: {
          myLat: 59.643485,
          myLong: 17.928190,
        }
      })
    }
  }

  pay(id: number) {
    this.dialogRef.open(ReservationDetialsComponent, {
      data: {
        id: id
      }
    })
    this.getPendingReservations();
    this.getFutureReservations();
  }

  getQRCode(id: number) {
    const dialogReference = this.dialogRef.open(QRCodeComponent, {
      data: {
        id: id
      }
    })
    dialogReference.afterClosed().subscribe((res) => {
      this.selectedPlanelId = res;
      this.getFutureReservations();

    })
  }

  checkExpandedPanel(panelFromArray: number): boolean {
    if (this.selectedPlanelId == panelFromArray) {
      return true
    } else return false;

  }

  checkReservationStatus(reservation: ReservationListItemForUserModel) {

    if (reservation.checkedinStatus == true) {
      return '#DCECD7'
    } else return 'white'
  }

  downloadTicket(reservationId: number, seatId: number, seatNumber: number) {

    this.reservationService.getTicketPDF(reservationId, seatId,).subscribe({
      next: data => {

        let blob = new Blob([data], {type: 'application/pdf'});
        saveAs(blob, 'ticket_for_seat_number_' + seatNumber + '.pdf')

      },
      error: err => console.log(err)

    })


  }
}
