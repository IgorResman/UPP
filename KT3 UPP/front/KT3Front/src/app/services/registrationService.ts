import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class RegistrationService{
    categories = [];
    languages = [];
    books = [];


    constructor(private httpClient: HttpClient){}

    startProcess(){
        return this.httpClient.get('http://localhost:8082/registration/getFormRegistration') as Observable<any>
    }

    register(taskId,user){
        return this.httpClient.post('http://localhost:8082/registration/postForm/'.concat(taskId),user) as Observable<any>
    }

    getResidentConfirmForm(){
        console.log("usao u get resident na servisu")
        return this.httpClient.get('http://localhost:8082/registration/getResidentConfirmForm') as Observable<any>
    }

    resident(taskId,user)
    {
        return this.httpClient.post('http://localhost:8082/registration/postFormResident/'.concat(taskId),user) as Observable<any>
    }
    
}

