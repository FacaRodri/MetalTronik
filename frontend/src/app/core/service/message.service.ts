import { Injectable } from '@angular/core';
import { ToastrService } from "ngx-toastr";

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private time = 5000;
  private extendedTime = 2500;
  private positionClass =  'toast-bottom-full-width';

  constructor(private toastr: ToastrService) { }

  showError(message){
    console.log(message);
    this.toastr.error(
      message.body , message.title , {
        timeOut: this.time,
        extendedTimeOut: this.extendedTime,
        progressBar: true,
        positionClass: this.positionClass
      }
    )
  }

  showSuccess(message){
    console.log(message);
    
    this.toastr.success(
      message.body, message.title , {
        timeOut: this.time,
        extendedTimeOut: this.extendedTime,
        progressBar: true,
        positionClass: this.positionClass
      }
    )
  }

  showInfo(message){
    this.toastr.info(
      message.body, message.title , {
        timeOut: this.time,
        extendedTimeOut: this.extendedTime,
        progressBar: true,
        positionClass: this.positionClass
      }
    )
  }


}