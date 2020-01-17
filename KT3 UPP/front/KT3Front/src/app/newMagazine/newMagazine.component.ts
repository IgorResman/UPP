import { OnInit, Component } from '@angular/core';
import { MagazineService } from '../services/magazineService';
import { Router } from '@angular/router';

@Component({
    selector:'app-newMagazine',
    templateUrl:'./newMagazine.component.html',
    styleUrls:['./newMagazine.component.css']
})
export class NewMagazine implements OnInit{

    private formFieldsDto = null;
    private formFields= [];
    processInstance = "";
    private enumValues = [];

    constructor(private magazineService: MagazineService, private router:Router){
        let x = magazineService.getMagazineForm();

        x.subscribe(
            res => {
                this.formFieldsDto = res;
                this.formFields = res.formFields;
                this.processInstance = res.processInstanceId;

                this.formFields.forEach( (field) =>{
          
                    if( field.type.name=='enum'){
                      this.enumValues = Object.keys(field.type.values);
                    }
                  });
            },
            err =>{
                console.log("Greska get magazine form");
            }
        )
    }

    ngOnInit(){}

    onSubmit(value,form)
    {
        let o = new Array();
        for(var property in value)
        {
            console.log("FieldID: " + property);
            console.log("Value:"+ value[property]);
            o.push({fieldId: property, fieldValue:value[property]})
        }

        let x = this.magazineService.createMagazine(this.formFieldsDto.taskId, o,localStorage.username);

        x.subscribe(
            res=>{
                alert("Uspesno kreiran novi casopis");
                this.router.navigate(['reviewersAndEditors']);
            }
            ,err=>{

                console.log("greska prilikom kreiranja casopisa na frontu")
            }
        )
    }
}