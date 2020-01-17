import { ReviewersAndEditors } from "./reviewersAndEditors.component";
import { ComponentFixture, async, TestBed } from '@angular/core/testing';


describe('ReviewersAndEditors',() =>{
    let component: ReviewersAndEditors;
    let fixture: ComponentFixture<ReviewersAndEditors>;

    beforeEach(async(()=>{
        TestBed.configureTestingModule({
            declarations: [ReviewersAndEditors]
        })
        .compileComponents();
    }));


    beforeEach(()=>{
        fixture = TestBed.createComponent(ReviewersAndEditors);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', ()=> {
        expect(component).toBeTruthy();
    })
})