import { ComponentFixture, async, TestBed } from '@angular/core/testing';
import { ApproveMagazine } from './approveMagazine.component';

describe('ApproveMagazine',() =>{
    let component: ApproveMagazine;
    let fixture: ComponentFixture<ApproveMagazine>;

    beforeEach(async(()=>{
        TestBed.configureTestingModule({
            declarations: [ApproveMagazine]
        })
        .compileComponents();
    }));


    beforeEach(()=>{
        fixture = TestBed.createComponent(ApproveMagazine);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', ()=> {
        expect(component).toBeTruthy();
    })
})