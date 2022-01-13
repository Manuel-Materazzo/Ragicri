import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdiniCamerieriComponent } from './ordini-camerieri.component';

describe('OrdiniCamerieriComponent', () => {
  let component: OrdiniCamerieriComponent;
  let fixture: ComponentFixture<OrdiniCamerieriComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrdiniCamerieriComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdiniCamerieriComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
