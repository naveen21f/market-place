import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketCommonComponent } from './market-common.component';

describe('MarketCommonComponent', () => {
  let component: MarketCommonComponent;
  let fixture: ComponentFixture<MarketCommonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarketCommonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketCommonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
