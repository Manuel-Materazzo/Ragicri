import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdiniAsportoComponent } from './ordini-asporto.component';

describe('OrdiniAsportoComponent', () => {
  let component: OrdiniAsportoComponent;
  let fixture: ComponentFixture<OrdiniAsportoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrdiniAsportoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdiniAsportoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
