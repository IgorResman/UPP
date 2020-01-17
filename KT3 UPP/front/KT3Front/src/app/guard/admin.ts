import { CanActivate } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class Admin implements CanActivate{
    constructor(){}


    canActivate()
    {
        return localStorage.role == 'admin';
    }


}