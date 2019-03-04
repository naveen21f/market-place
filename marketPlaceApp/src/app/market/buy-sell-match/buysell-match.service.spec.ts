import { TestBed } from '@angular/core/testing';

import { BuysellMatchService } from './buysell-match.service';

describe('BuysellMatchService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BuysellMatchService = TestBed.get(BuysellMatchService);
    expect(service).toBeTruthy();
  });
});
