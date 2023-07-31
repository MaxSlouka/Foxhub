import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminBoardUserComponent } from './admin-board-user.component';

describe('AdminBoardUserComponent', () => {
  let component: AdminBoardUserComponent;
  let fixture: ComponentFixture<AdminBoardUserComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminBoardUserComponent]
    });
    fixture = TestBed.createComponent(AdminBoardUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
