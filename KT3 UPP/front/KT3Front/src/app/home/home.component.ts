import { OnInit, Component } from '@angular/core';
import { HomeService } from '../services/homeService';
import { Router } from '@angular/router';


@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['/home.component.css']
})

export class HomeComponent implements OnInit{
    constructor(private homeService: HomeService,private router:Router){}

    ngOnInit(){}

    onSubmit(value,form)
    {
        let x = this.homeService.login(value.username, value.password);

        x.subscribe(
            res => {
                alert("You logged in successfully");

                console.log("ispis uloge u home.ts   "+res.uloga);
                localStorage.setItem("role",res.uloga);
                localStorage.setItem("username", value.username);

                console.log("local storage role: " + localStorage.role)
                console.log("local storage username: " + localStorage.username)
                
                if(localStorage.role == "editor")
                    this.router.navigate(['newMagazine'])
                else if(localStorage.role == "admin")
                    this.router.navigate(["approveMagazine"])
                else
                    this.router.navigate(["home"])
            }
        )
    }
}