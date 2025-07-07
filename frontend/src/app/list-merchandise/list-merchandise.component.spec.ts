import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListMerchandiseComponent } from './list-merchandise.component';

describe('ListMerchandiseComponent', () => {
  let component: ListMerchandiseComponent;
  let fixture: ComponentFixture<ListMerchandiseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListMerchandiseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListMerchandiseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
