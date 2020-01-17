import { NewMagazine } from "./newMagazine.component";
import { TestBed, ComponentFixture, async } from '@angular/core/testing';

describe('NewMagazine',() =>{
    let component: NewMagazine;
    let fixture: ComponentFixture<NewMagazine>;

    beforeEach(async(()=>{
        TestBed.configureTestingModule({
            declarations: [NewMagazine]
        })
        .compileComponents();
    }));


    beforeEach(()=>{
        fixture = TestBed.createComponent(NewMagazine);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', ()=> {
        expect(component).toBeTruthy();
    })
})