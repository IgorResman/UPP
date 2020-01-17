import { OnInit, Component } from '@angular/core';
import { MagazineService } from '../services/magazineService';
import { Router } from '@angular/router';

@Component({
    selector: 'app-reviewersAndEditors',
    templateUrl: './reviewersAndEditors.component.html',
    styleUrls:['./reviewersAndEditors.component.css']
})
export class ReviewersAndEditors implements OnInit{

    private formFieldsDto = null;
    private formFields = [];
    private processInstance = "";
    private enumValues=[];

    constructor(private magazineService:MagazineService, private router:Router)
    {
        let x = magazineService.getReviewersAndEditorsForm();

        x.subscribe(
            res =>{

                this.formFieldsDto = res;

                this.formFields = res.formFields;
                this.processInstance = res.processInstanceId;
                this.formFields.forEach( (field) =>{
                    if( field.type.name=='enum'){
                      this.enumValues = Object.keys(field.type.values);
                    }
                  });
            }
            ,
            err=>{
                console.log("Greska rev and editors kupljenje forme");
            }
        )
    }


    ngOnInit(){}

    onSubmit(value,form)
    {
        let o = new Array();
        for (var property in value) {
            o.push({fieldId : property, fieldValue : value[property]});
        }
        let x = this.magazineService.setReviewersAndEditors(this.formFieldsDto.taskId, o);

        x.subscribe(
            res =>{
                alert("Uspesno dodati editori i recenzenti");
                this.router.navigate(['home']);;
            },
            err=>{
                console.log("Reviewers and editors submit error")
            }
        )

    }

}