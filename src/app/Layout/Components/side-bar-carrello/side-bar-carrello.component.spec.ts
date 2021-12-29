import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SideBarCarrelloComponent } from './side-bar-carrello.component';

describe('SideBarCarrelloComponent', () => {
  let component: SideBarCarrelloComponent;
  let fixture: ComponentFixture<SideBarCarrelloComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SideBarCarrelloComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SideBarCarrelloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
