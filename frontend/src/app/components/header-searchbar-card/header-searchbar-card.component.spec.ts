import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderSearchbarCardComponent } from './header-searchbar-card.component';

describe('HeaderSearchbarCardComponent', () => {
  let component: HeaderSearchbarCardComponent;
  let fixture: ComponentFixture<HeaderSearchbarCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HeaderSearchbarCardComponent]
    });
    fixture = TestBed.createComponent(HeaderSearchbarCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
