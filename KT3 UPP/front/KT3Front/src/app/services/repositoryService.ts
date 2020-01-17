import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class RepositoryService{
    categories = [];
    languages = [];
    books = [];


    constructor(private httpClient: HttpClient){}

    startProcess(){
        return this.httpClient.get('http://localhost:8082/registration/getFormRegistration') as Observable<any>
    }
}