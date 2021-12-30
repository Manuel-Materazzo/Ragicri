import { TestBed } from '@angular/core/testing';

import { PiattoService } from './piatto.service';

describe('PiattoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PiattoService = TestBed.get(PiattoService);
    expect(service).toBeTruthy();
  });
});
