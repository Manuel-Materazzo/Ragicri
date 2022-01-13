import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PiattiAdminComponent } from './piatti-admin.component';

describe('PiattiAdminComponent', () => {
  let component: PiattiAdminComponent;
  let fixture: ComponentFixture<PiattiAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PiattiAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PiattiAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
