import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';

@Injectable()
export class Notauthorized implements CanActivate {

  constructor() {}

  canActivate() {
    console.log(localStorage)
    if(!localStorage.user){
      return true;
    }else{
      return false;
    }
  }
}