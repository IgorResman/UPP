import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class HomeService{
    categories = [];
    languages = [];
    books = [];


    constructor(private httpClient:HttpClient)
    {

    }

    login(username,password)
    {
        return this.httpClient.get('http://localhost:8082/registration/login/' + username + "/" + password) as Observable<any>
    }
}