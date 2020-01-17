import { ResidentComponent } from "../resident/resident.component"
import { ComponentFixture, async, TestBed } from '@angular/core/testing';

describe('residentComponent',() =>{
    let component: ResidentComponent;
    let fixture: ComponentFixture<ResidentComponent>;

    beforeEach(async(()=>{
        TestBed.configureTestingModule({
            declarations: [ResidentComponent]
        })
        .compileComponents();
    }));


    beforeEach(()=>{
        fixture = TestBed.createComponent(ResidentComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', ()=> {
        expect(component).toBeTruthy();
    })
})