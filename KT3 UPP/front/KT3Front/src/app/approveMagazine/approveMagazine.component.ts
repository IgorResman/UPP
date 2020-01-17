import { OnInit, Component } from '@angular/core';
import { MagazineService } from '../services/magazineService';
import { Router } from '@angular/router';
import { ValueConverter } from '@angular/compiler/src/render3/view/template';


@Component({
    selector: 'app-approveMagazine',
    templateUrl: './approveMagazine.component.html',
    styleUrls: ['./approveMagazine.component.css']
})
export class ApproveMagazine implements OnInit{

    private formFieldsDto = null;
    private formFields = [];
    private processInstance = "";
    private enumValues = [];

    constructor(private magazineService:MagazineService,private router:Router)
    {
        let x = magazineService.getApproveMagazineForm();

        x.subscribe(
            res=>{
                this.formFieldsDto = res;
                this.formFields = res.formFields;
                this.processInstance = res.processInstance;
                this.formFields.forEach( (field) =>{
          
                    if( field.type.name=='enum'){
                      this.enumValues = Object.keys(field.type.values);
                    }
                  });
            },
            err=>{
                console.log("Greska app magazine ts")
            }
        );
    }


    ngOnInit(){}

    onSubmit(value,from)
    {
        let o = new Array();
        for(var property in value)
        {
            if(property =="check")
            {
                if(value[property] != true)
                {
                    value[property] = false;
                }
            }
            o.push({fieldId : property, fieldValue : value[property]});

        }
        console.log(this.formFieldsDto);

        let x = this.magazineService.setMagazineActivity(this.formFieldsDto.taskId,o);

        x.subscribe(
            res=>{
                alert("Successfully finished activation/deactivation of magazine");
                this.router.navigate(['home']);
            },
            err=>
            {
                console.log("Approve magazine ts error on subscribe");
            }
        )
    }
}