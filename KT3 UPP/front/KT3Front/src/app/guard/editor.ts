import { CanActivate } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class Editor implements CanActivate{
    constructor(){}

    canActivate()
    {
        console.log("Editor activate guard role:"+localStorage.role);
        return localStorage.role == 'editor';
    }
}