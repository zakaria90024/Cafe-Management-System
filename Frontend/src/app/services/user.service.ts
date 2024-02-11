import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url = environment.apiUrl
  constructor(private httpClient:HttpClient) {}
  // signup(data:any){
  //   return this.httpClient.post(this.url+"/user/singup", data, {
  //     headers:new HttpHeaders().set('Content-Type', 'application/json')
  //   })
  // }

  signup(data: any) {
    return this.httpClient.post(this.url + "/user/signup", data, {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
      // You can remove the following line that sets the token
      // headers: new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', 'Bearer ' + token)
    });
  }

  forgotPassword(data: any) {
    return this.httpClient.post(this.url + "/user/forgotPassword", data, {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
      // You can remove the following line that sets the token
      // headers: new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', 'Bearer ' + token)
    });
  }
}
