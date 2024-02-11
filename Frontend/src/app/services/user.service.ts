import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url = environment.apiUrl
  constructor(private httpClient:HttpClient) {}
  signup(data:any){
    return this.httpClient.post(this.url+"/user/singup", data, {
      headers:new HttpHeaders().set('Content-Type', 'application/json')
    })
  }
}
