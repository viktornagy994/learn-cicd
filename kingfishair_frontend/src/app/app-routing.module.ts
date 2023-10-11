import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {ErrorPageComponent} from "./components/error-page/error-page.component";
import {ServerSideErrorPageComponent} from "./components/server-side-error-page/server-side-error-page.component";
import {VisibleFlightListComponent} from "./components/visible-flight-list/visible-flight-list.component";
import {FlightDetailsComponent} from "./components/flight-details/flight-details.component";
import {RegistrationComponent} from "./components/user.handler.components/registration/registration.component";
import {MyProfileComponent} from "./components/user.handler.components/my-profile/my-profile.component";
import {
  ChooseDepartureArrivalAirportFormComponent
} from "./components/choose-departure-arrival-airport-form/choose-departure-arrival-airport-form.component";
import {ReservationFormComponent} from "./components/reservation-form/reservation-form.component";
import {
  AdminReservationListComponent
} from "./components/admin/admin-reservation-list/admin-reservation-list.component";
import {AdminRouteListComponent} from "./components/admin/admin-route-list/admin-route-list.component";
import {AdminRouteFormComponent} from "./components/admin/admin-route-form/admin-route-form.component";
import {AdminFlightFormComponent} from "./components/admin/admin-flight-form/admin-flight-form.component";
import {ReservationDetialsComponent} from "./components/reservation-detials/reservation-detials.component";
import {
  ArrivalCityMapForAccomodationComponent
} from "./components/arrival-city-map-for-accomodation/arrival-city-map-for-accomodation.component";
import {
  SetNewPasswordComponent
} from "./components/user.handler.components/set-new-password/set-new-password.component";
import {ChooseSeatComponent} from "./components/choose-seat/choose-seat.component";
import {AdminFlightListComponent} from "./components/admin/admin-flight-list/admin-flight-list.component";
import {AboutUsComponent} from "./components/about-us/about-us.component";
import {ContactUsComponent} from "./components/contact-us/contact-us.component";
import {
  AdminContactUsMessagesComponent
} from "./components/admin/admin-contact-us-messages/admin-contact-us-messages.component";
import {HasRoleGuard} from "./guards/has-role.guard";
import {AuthorizationGuard} from "./guards/authorization.guard";
import {ServicesComponent} from "./components/services/services.component";


const routes: Routes = [
  // expectedRoles:Permit all----------------------------------------------
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'visible-flight-list', component: VisibleFlightListComponent},
  {path: 'our-services', component: ServicesComponent},
  {path: 'login', component: HomeComponent},
  {path: 'server-side-error-page', component: ServerSideErrorPageComponent},
  {path: 'set-new-password/:id', component: SetNewPasswordComponent},
  {path: 'contact-us', component: ContactUsComponent},
  {path: 'error-page', component: ErrorPageComponent},
  {path: 'about-us', component: AboutUsComponent},
  {path: 'flight-details/:id', component: FlightDetailsComponent},
  {path: 'my-profile', component: MyProfileComponent},
  {path: 'choose-airports', component: ChooseDepartureArrivalAirportFormComponent},

  // expectedRoles:ROLE_ADMIN----------------------------------------------


  {
    path: 'admin-route-list', component: AdminRouteListComponent,
    canActivate: [AuthorizationGuard, HasRoleGuard],
    data: {expectedRoles: ['ROLE_ADMIN']}
  },
  {
    path: 'admin-route-form', component: AdminRouteFormComponent,
    canActivate: [AuthorizationGuard, HasRoleGuard],
    data: {expectedRoles: ['ROLE_ADMIN']}
  },
  {
    path: 'admin-flight-list', component: AdminFlightListComponent,
    canActivate: [AuthorizationGuard, HasRoleGuard],
    data: {expectedRoles: ['ROLE_ADMIN']}
  },
  {
    path: 'admin-flight-form', component: AdminFlightFormComponent,
    canActivate: [AuthorizationGuard, HasRoleGuard],
    data: {expectedRoles: ['ROLE_ADMIN']}
  },
  {
    path: 'admin-contact-us-messages', component: AdminContactUsMessagesComponent,
    canActivate: [AuthorizationGuard, HasRoleGuard],
    data: {expectedRoles: ['ROLE_ADMIN']}
  },
  {
    path: 'admin-reservation-list', component: AdminReservationListComponent,
    canActivate: [AuthorizationGuard, HasRoleGuard],
    data: {expectedRoles: ['ROLE_ADMIN']}
  },

// expectedRoles:ROLE_USER/ROLE_ADMIN----------------------------------------------

  {
    path: 'choose-seat', component: ChooseSeatComponent,
    canActivate: [AuthorizationGuard, HasRoleGuard],
    data: {expectedRoles: ['ROLE_ADMIN', 'ROLE_USER']}
  },
  {
    path: 'reservation-form/:id', component: ReservationFormComponent,
    canActivate: [AuthorizationGuard, HasRoleGuard],
    data: {expectedRoles: ['ROLE_ADMIN', 'ROLE_USER']}
  },
  {
    path: 'reservation-details/:id', component: ReservationDetialsComponent,
    canActivate: [AuthorizationGuard, HasRoleGuard],
    data: {expectedRoles: ['ROLE_ADMIN', 'ROLE_USER']}
  },
  {
    path: 'map/:id', component: ArrivalCityMapForAccomodationComponent,
    canActivate: [AuthorizationGuard, HasRoleGuard],
    data: {expectedRoles: ['ROLE_ADMIN', 'ROLE_USER']}
  },


  {path: '**', redirectTo: 'error-page'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
