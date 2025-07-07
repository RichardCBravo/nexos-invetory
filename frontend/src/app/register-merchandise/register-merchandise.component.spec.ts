import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterMerchandiseComponent } from './register-merchandise.component';

describe('RegisterMerchandiseComponent', () => {
  let component: RegisterMerchandiseComponent;
  let fixture: ComponentFixture<RegisterMerchandiseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterMerchandiseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterMerchandiseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
