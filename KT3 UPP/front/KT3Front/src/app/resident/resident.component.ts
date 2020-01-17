import { Component, OnInit } from '@angular/core';
import { RegistrationService } from '../services/registrationService';
import { RepositoryService } from '../services/repositoryService';
import { Router } from '@angular/router';



@Component({
    selector: 'app-resident',
    templateUrl: './resident.component.html',
    styleUrls: ['./resident.component.css']
})
export class ResidentComponent implements OnInit{
    private formFieldsDto = null;
    private formFields= [];
    processInstance = "";
    private enumValues = [];

    constructor(private regService:RegistrationService, private repService:RepositoryService,private router: Router)
    {
        console.log("usao u kontruktor resident.component.ts")
        let x = regService.getResidentConfirmForm();

        x.subscribe(
            res=>{
                this.formFieldsDto = res;
                this.formFields = res.formFields;
                this.processInstance = res.processInstanceId;

                this.formFields.forEach((field)=>{
                    if(field.type.name == 'enum')
                    {
                        this.enumValues = Object.keys(field.type.values);
                    }
                });
            },
            err=>
            {
                console.log("Error resident component");
            }
        )

        console.log("odradio subscribe u resident.component")
    }

    ngOnInit(){}

    onSubmit(value,form)
    {
        console.log("Usao u on submit resident.component")
        let o = new Array();
        for(var property in value)
        {
            o.push({fieldId: property, fieldValue: value[property]});
        }

        let x = this.regService.resident(this.formFieldsDto.taskId,o);

        x.subscribe(
            res =>{
                console.log("Resident confirmed successfully");
                this.router.navigate(['/home'])
            },
            err => {
                console.log("Resident confirmed unsuccessfully");
            }
        )
    }

}