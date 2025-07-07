import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchMerchandiseComponent } from './search-merchandise.component';

describe('SearchMerchandiseComponent', () => {
  let component: SearchMerchandiseComponent;
  let fixture: ComponentFixture<SearchMerchandiseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchMerchandiseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchMerchandiseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
