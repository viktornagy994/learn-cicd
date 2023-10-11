import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {SimpleIdResponse} from "../models/SimpleIdResponse";
import {environment} from "../../environments/environment";
import {Router} from "@angular/router";

const BASE_URL = environment.BASE_URL + '/api/users';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  isUsedOAuth:boolean=false;
  constructor(private http: HttpClient, private router:Router) {
  }

  authenticate(credentials: { username: string, password: string },captchaToken: string): Observable<any> {
    const headers = new HttpHeaders( credentials ?{Authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)}
  : {});

    const params = new HttpParams({fromString: `captchaToken=${captchaToken}`});

    return this.http.get<any>(BASE_URL + '/login', {headers: headers,params: params});

  }


  logout() {
    return this.http.get(BASE_URL + '/logout');


  }

  findIfAlreadyAuthorized() {
    return this.http.get(BASE_URL + '/me-oauth2')
  }


}
