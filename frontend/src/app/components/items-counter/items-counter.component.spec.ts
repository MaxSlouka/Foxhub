import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemsCounterComponent } from './items-counter.component';

describe('ItemsCounterComponent', () => {
  let component: ItemsCounterComponent;
  let fixture: ComponentFixture<ItemsCounterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ItemsCounterComponent]
    });
    fixture = TestBed.createComponent(ItemsCounterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
