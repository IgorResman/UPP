import { ComponentFixture, async, TestBed } from '@angular/core/testing';
import { HomeComponent } from './home.component';

describe('residentComponent',() =>{
    let component: HomeComponent;
    let fixture: ComponentFixture<HomeComponent>;

    beforeEach(async(()=>{
        TestBed.configureTestingModule({
            declarations: [HomeComponent]
        })
        .compileComponents();
    }));


    beforeEach(()=>{
        fixture = TestBed.createComponent(HomeComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', ()=> {
        expect(component).toBeTruthy();
    })
})