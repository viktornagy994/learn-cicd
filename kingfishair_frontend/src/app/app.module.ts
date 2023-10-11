import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {NavbarComponent} from './components/navbar/navbar.component';
import {HomeComponent} from './components/home/home.component';
import {ErrorPageComponent} from './components/error-page/error-page.component';
import {HttpErrorInterceptor} from "./interceptors/http-error-interceptor";
import {VisibleFlightListComponent} from './components/visible-flight-list/visible-flight-list.component';
import {FlightDetailsComponent} from './components/flight-details/flight-details.component';
import {MatDialogModule} from "@angular/material/dialog";
import { ServerSideErrorPageComponent } from './components/server-side-error-page/server-side-error-page.component';
import { RegistrationComponent } from './components/user.handler.components/registration/registration.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { LoginComponent } from './components/user.handler.components/login/login.component';
import {CommonModule} from "@angular/common";
import {HttpRequestInterceptor} from "./interceptors/httpRequestInterceptor";
import { MyProfileComponent } from './components/user.handler.components/my-profile/my-profile.component';
import { MatDividerModule } from "@angular/material/divider";
import { MatIconModule } from "@angular/material/icon";
import { MatMenuModule } from "@angular/material/menu";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ChooseDepartureArrivalAirportFormComponent } from './components/choose-departure-arrival-airport-form/choose-departure-arrival-airport-form.component';
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import { ReservationFormComponent } from './components/reservation-form/reservation-form.component';
import { ReservationsComponent } from './components/reservation-form/reservations/reservations.component';
import { AdminReservationListComponent } from './components/admin/admin-reservation-list/admin-reservation-list.component';
import { NewsAndDealsComponent } from './components/news-and-deals/news-and-deals.component';
import { CarouselModule } from "@coreui/angular";
import { AdminRouteFormComponent} from "./components/admin/admin-route-form/admin-route-form.component";
import { AdminFlightFormComponent} from "./components/admin/admin-flight-form/admin-flight-form.component";
import { AdminRouteListComponent} from "./components/admin/admin-route-list/admin-route-list.component";
import {MatCheckboxModule} from "@angular/material/checkbox";
import { ReservationDetialsComponent } from './components/reservation-detials/reservation-detials.component';
import {RECAPTCHA_LANGUAGE, RecaptchaModule} from "ng-recaptcha";
import {GoogleMap, GoogleMapsModule} from "@angular/google-maps";
import { CompletedRegisterDialogComponent } from './components/user.handler.components/completed-register-dialog/completed-register-dialog.component';
import {MatCardModule} from "@angular/material/card";
import { ForgottenPasswordComponent } from './components/user.handler.components/forgotten-password/forgotten-password.component';
import { ForgottenPasswordSendDialogComponent } from './components/user.handler.components/forgotten-password-send-dialog/forgotten-password-send-dialog.component';
import { SetNewPasswordComponent } from './components/user.handler.components/set-new-password/set-new-password.component';
import { AdminFlightListComponent } from './components/admin/admin-flight-list/admin-flight-list.component';
import { FooterComponent } from './components/footer/footer.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { ChooseSeatComponent } from './components/choose-seat/choose-seat.component';
import {NgxPayPalModule} from "ngx-paypal";
import { ContactUsComponent } from './components/contact-us/contact-us.component';
import { ContactUsDialogComponentComponent } from './components/contact-us-dialog-component/contact-us-dialog-component.component';
import { QRCodeComponent } from './components/qr-code/qr-code.component';
import { AdminContactUsMessagesComponent } from './components/admin/admin-contact-us-messages/admin-contact-us-messages.component';
import {MatTabsModule} from "@angular/material/tabs";
import { AdminContactUsMessageHandleComponent } from './components/admin/admin-contact-us-message-handle/admin-contact-us-message-handle.component';
import { ServicesComponent } from './components/services/services.component';

@NgModule({
  declarations: [
    AppComponent,
    ReservationFormComponent,
    ReservationsComponent,
    NavbarComponent,
    HomeComponent,
    ErrorPageComponent,
    ServerSideErrorPageComponent,
    VisibleFlightListComponent,
    FlightDetailsComponent,
    AdminReservationListComponent,
    AdminRouteFormComponent,
    AdminFlightFormComponent,
    AdminRouteListComponent,
    NewsAndDealsComponent,
    ContactUsComponent,
    AdminRouteListComponent,
    ReservationDetialsComponent,
    CompletedRegisterDialogComponent,
    ForgottenPasswordSendDialogComponent,
    AdminFlightListComponent,
    FooterComponent,
    AboutUsComponent,
    ContactUsDialogComponentComponent,
    QRCodeComponent,
    AdminContactUsMessagesComponent,
    AdminContactUsMessageHandleComponent,
    ServicesComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ChooseDepartureArrivalAirportFormComponent,
    MatButtonModule,
    MatFormFieldModule,
    MatOptionModule,
    MatSelectModule,
    MatTableModule,
    MatInputModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    RegistrationComponent,
    HttpClientModule,
    ReactiveFormsModule,
    MatDialogModule,
    FormsModule,
    LoginComponent,
    CommonModule,
    MatButtonModule,
    MatTableModule,
    MyProfileComponent,
    MatDividerModule,
    MatIconModule,
    MatMenuModule,
    CarouselModule,
    MatCheckboxModule,
    RecaptchaModule,
    GoogleMapsModule,
    MatCardModule,
    ForgottenPasswordComponent,
    ChooseSeatComponent,
    SetNewPasswordComponent,

    NgxPayPalModule,
    MatTabsModule,
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true},
    { provide: HTTP_INTERCEPTORS, useClass: HttpRequestInterceptor, multi: true},
    { provide: RECAPTCHA_LANGUAGE, useValue: "en",  }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
