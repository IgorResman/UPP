import { OnInit, Component } from '@angular/core';
import { RegistrationService } from '../services/registrationService';
import { RepositoryService } from '../services/repositoryService';
import { Router } from '@angular/router';



@Component({
    selector:'app-registration',
    templateUrl:'./registration.component.html',
    styleUrls:['./registration.component.css']
})
export class RegistrationComponent implements OnInit{
    private pass = false;
    private formFieldsDto = null;
    private formFields = [];
    private processInstance = "";
    private enumValues = [];


    constructor(private regService: RegistrationService, private repositoryService: RepositoryService, private router: Router)
    {
        let x = regService.startProcess();


        x.subscribe(
            res => {
                console.log(res);
                this.formFieldsDto = res;
                this.formFields = res.formFields;
                this.processInstance = res.processInstanceId;
                this.formFields.forEach((field)=>{
                    if(field.type.name == 'enum'){
                        this.enumValues = Object.keys(field.type.values);
                    }
                })
            },
            err => {
                console.log('greska reg component');
            }
        )
    }





    ngOnInit(){}



    onSubmit(value,form)
    {
        let o = new Array();
        for(var property in value)
        {
            o.push({fieldId: property, fieldValue: value[property]});
        }

        let x = this.regService.register(this.formFieldsDto.taskId,o);

        x.subscribe(
            res =>{
                console.log("Registered successfully");
                this.router.navigate(['/home'])
            },
            err => {
                console.log("Registered unsuccessfully");
            }
        )
    }



}