import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoxbookFunLettersComponent } from './foxbook-fun-letters.component';

describe('FoxbookFunLettersComponent', () => {
  let component: FoxbookFunLettersComponent;
  let fixture: ComponentFixture<FoxbookFunLettersComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FoxbookFunLettersComponent]
    });
    fixture = TestBed.createComponent(FoxbookFunLettersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
