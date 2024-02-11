import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../services/user.service";
import {MatDialogRef} from "@angular/material/dialog";
import {NgxUiLoaderService} from "ngx-ui-loader";
import {SnackbarService} from "../services/snackbar.service";
import {GlobalConstants} from "../shared/global-constants";
import {Router} from "@angular/router";

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {

  forgotPasswordForm:any = FormGroup;
  responseMessage:any;

  constructor(private formBuilder:FormBuilder,
              private userService:UserService,
              public dialogRef:MatDialogRef<ForgotPasswordComponent>,
              private ngxService:NgxUiLoaderService,
              private snckbarService:SnackbarService,
              private router: Router) { }

  ngOnInit(): void {

    this.forgotPasswordForm = this.formBuilder.group({
      email: [null, [Validators.required, Validators.pattern(GlobalConstants.emailRegex)]],

    })

  }


  handleSubmit() {
    this.ngxService.start();
    var formData = this.forgotPasswordForm.value;

    var data = {

      email: formData.email,

    }

    this.userService.forgotPassword(data).subscribe((response: any) => {
        this.ngxService.stop();
        this.dialogRef.close();
        this.responseMessage = response?.message;
        this.snckbarService.openSnackBar(this.responseMessage, "");
        this.router.navigate(['/']);

      }, (error) => {
        this.ngxService.stop();
        if (error.error?.message) {
          this.responseMessage = error.error?.message;
        } else {
          this.responseMessage = GlobalConstants.genericError;
        }
        this.snckbarService.openSnackBar(this.responseMessage, GlobalConstants.error)
      }
    )
  }


}
