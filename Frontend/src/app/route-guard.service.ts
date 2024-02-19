import { Injectable } from '@angular/core';
import {AuthService} from "./auth.service";
import {ActivatedRouteSnapshot, Router} from "@angular/router";
import {SnackbarService} from "./services/snackbar.service";
import {GlobalConstants} from "./shared/global-constants";
import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService {

  constructor(
    public auth:AuthService,
    public router:Router,
    private snackbarService:SnackbarService
  ) { }
  canActivate(route:ActivatedRouteSnapshot):boolean{
    let expectedRoleArray = route.data;
    expectedRoleArray = expectedRoleArray.expatedRole;
    const token:any = localStorage.getItem('token');

    var tokenPayload:any;
    try{
      tokenPayload = jwt_decode(token);
    }
    catch (err) {
      localStorage.clear();
      this.router.navigate(['/']);
    }
    let expectedRole = '';
    for(let i = 0; i < expectedRoleArray.length; i++){
      if(expectedRoleArray[i] == tokenPayload.role){
        expectedRole = tokenPayload.role;
      }
    }
    if(tokenPayload.role == 'role' || tokenPayload.role == 'admin'){
      if(this.auth.isAuthticated() && tokenPayload.role == expectedRole){
        return true;
      }
      this.snackbarService.openSnackBar(GlobalConstants.unauthorized, GlobalConstants.error);
      this.router.navigate(['/cafe/dashboard']);
      return false;
    }else {
      this.router.navigate(['/']);
      localStorage.clear()
      return false;
    }
  }
}
