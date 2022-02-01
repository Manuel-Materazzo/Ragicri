import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UtenteAdminComponent } from './utente-admin.component';

describe('UtenteAdminComponent', () => {
  let component: UtenteAdminComponent;
  let fixture: ComponentFixture<UtenteAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UtenteAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UtenteAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
