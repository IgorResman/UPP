import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { Notauthorized } from './guard/notauthorized';
import { RegistrationComponent } from './registration/registration.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule }   from '@angular/forms';
import { ResidentComponent } from './resident/resident.component';
import { NewMagazine } from './newMagazine/newMagazine.component';
import { Editor } from './guard/editor';
import { Admin } from './guard/admin';
import { ReviewersAndEditors } from './reviewersAndEditors/reviewersAndEditors.component';
import { ApproveMagazine } from './approveMagazine/approveMagazine.component';

const routes: Routes = [
  {
    path:"",
    component:HomeComponent,
    canActivate:[Notauthorized]
  },

  {
    path:"home",
    component:HomeComponent,
    canActivate:[Notauthorized]
  },

  {
    path:"registration",
    component:RegistrationComponent,
    canActivate:[Notauthorized]
  },
  {
    path:"resident",
    component: ResidentComponent,
    canActivate:[Admin]
  },
  {
    path:"newMagazine",
    component: NewMagazine,
    canActivate:[Editor]
  },
  {
    path:"reviewersAndEditors",
    component: ReviewersAndEditors,
    canActivate:[Editor]
  },
  {
    path:"approveMagazine",
    component: ApproveMagazine,
    canActivate:[Admin]
  },
  {path: "**" , redirectTo:"home"}

]

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegistrationComponent,
    ReviewersAndEditors,
    ResidentComponent,
    ApproveMagazine,
    NewMagazine
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule
  ],
  providers: [Notauthorized,Admin,Editor],
  bootstrap: [AppComponent]
})
export class AppModule { }
