import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {UserRegisterModel} from "../models/user-register-model";
import {LoginModel} from "../models/login-model";
import {UserListItemModel} from "../models/user-list-item-model";
import {environment} from "../../environments/environment";
import {EditProfileModel} from "../models/edit-profile-model";
import {UsernameForPasswordResetModel} from "../models/username-for-password-reset-model";
import {NewPasswordModel} from "../models/new-password-model";
import {PasswordResetTokenCheckModel} from "../models/password-reset-token-check-model";
import {ReservationFormDataModel} from "../models/reservation-form-data-model";
import {ContactMessageFormDataModel} from "../models/contact-message-form-data-model";
import {ContactMessageCommandModel} from "../models/contact-message-command-model";


const BASE_URL = environment.BASE_URL + '/api/users';
@Injectable({
  providedIn: 'root'
})
export class UserService {



  constructor(private http:HttpClient) { }

  saveUser(data: UserRegisterModel):Observable<any> {
    return this.http.post(BASE_URL+"/registration", data);

  }

  login():Observable<LoginModel>{
    return this.http.get<LoginModel>(BASE_URL+"/login")
  }


  fetchUserDetails():Observable<UserListItemModel> {
    return this.http.get<UserListItemModel>(BASE_URL + '/my-profile');
  }

  updateProfile(data: EditProfileModel): Observable<any> {
    return this.http.put(BASE_URL + '/update-profile', data);
  }

  getEmailForPasswordReset(data: UsernameForPasswordResetModel):Observable<any> {
    return this.http.post(BASE_URL+"/forgotten-password-email", data);

  }

  savePassword(data:NewPasswordModel, id:string): Observable<any> {
    return this.http.put(BASE_URL+'/set-new-password/'+id, data);
  }

  getPasswordResetTokenStatus(token:string):Observable<PasswordResetTokenCheckModel> {
    return this.http.get<PasswordResetTokenCheckModel>(BASE_URL+"/password-reset-check/"+token);

  }

  getContactFormData(): Observable<ContactMessageFormDataModel> {
    return this.http.get<ContactMessageFormDataModel>(BASE_URL + '/get-contact-form-data-categories')
  }
  saveMessage(data: ContactMessageCommandModel):Observable<any> {
    return this.http.post(BASE_URL+"/contact-us", data);

  }
}
