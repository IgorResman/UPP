import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { rendererTypeName } from '@angular/compiler';

@Injectable({
    providedIn:'root'
})
export class MagazineService{
    constructor(private httpClient:HttpClient){

    }

    getMagazineForm(){
        return this.httpClient.get('http://localhost:8082/magazine/getFormNewMagazine') as Observable<any>
    }

    createMagazine(taskId,magazine,username){
        return this.httpClient.post('http://localhost:8082/magazine/postForm/' + taskId + '/' + username, magazine) as Observable<any>
    }

    getReviewersAndEditorsForm()
    {
        return this.httpClient.get('http://localhost:8082/magazine/getReviewersAndEditorsForm') as Observable<any>
    }

    setReviewersAndEditors(taskId,user)
    {
        return this.httpClient.post('http://localhost:8082/magazine/postFormReviewersAndEditors/'.concat(taskId),user) as Observable<any>
    }

    getApproveMagazineForm()
    {
        return this.httpClient.get('http://localhost:8082/magazine/getApproveMagazineForm') as Observable<any>
    }

    setMagazineActivity(taskId,user)
    {
        return this.httpClient.post('http://localhost:8082/magazine/setMagazineActivity/'.concat(taskId),user) as Observable<any>
    }
}