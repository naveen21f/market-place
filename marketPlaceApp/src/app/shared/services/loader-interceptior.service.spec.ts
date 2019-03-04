import { TestBed } from '@angular/core/testing';

import { LoaderInterceptiorService } from './loader-interceptior.service';

describe('LoaderInterceptiorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LoaderInterceptiorService = TestBed.get(LoaderInterceptiorService);
    expect(service).toBeTruthy();
  });
});
