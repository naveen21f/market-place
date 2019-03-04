import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuySellMatchComponent } from './buy-sell-match.component';

describe('BuySellMatchComponent', () => {
  let component: BuySellMatchComponent;
  let fixture: ComponentFixture<BuySellMatchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuySellMatchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuySellMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
