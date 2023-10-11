import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ReservationListItemForUserModel} from "../models/reservation-list-item-for-user-model";
import {AdminContactMessageFromRegisteredUserModel} from "../models/admin-contact-message-from-registered-user-model";
import {AdminContactMessageFromVisitorModel} from "../models/admin-contact-message-from-visitor-model";
import {AdminContactUsMessageReplyModel} from "../models/admin-contact-us-message-reply-model";
const BASE_URL = environment.BASE_URL + '/api/messages';
@Injectable({
  providedIn: 'root'
})
export class AdminContactMessageService {

  constructor(private http:HttpClient) { }

  getUnhandledRequestForRegistered():Observable<AdminContactMessageFromRegisteredUserModel[]>{
    return this.http.get<AdminContactMessageFromRegisteredUserModel[]>(BASE_URL+"/registered-unhandled")
  }

  getHandledRequestForRegistered():Observable<AdminContactMessageFromRegisteredUserModel[]>{
    return this.http.get<AdminContactMessageFromRegisteredUserModel[]>(BASE_URL+"/registered-handled")
  }


  getUnhandledRequestForVisitor():Observable<AdminContactMessageFromVisitorModel[]>{
    return this.http.get<AdminContactMessageFromVisitorModel[]>(BASE_URL+"/visitor-unhandled")
  }

  getHandledRequestForVisitor():Observable<AdminContactMessageFromVisitorModel[]>{
    return this.http.get<AdminContactMessageFromVisitorModel[]>(BASE_URL+"/visitor-handled")
  }


  saveReply(data:AdminContactUsMessageReplyModel):Observable<any> {
    return this.http.put(BASE_URL+"/save-reply",data)

  }
}
