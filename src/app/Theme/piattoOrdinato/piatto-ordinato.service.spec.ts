import { TestBed } from '@angular/core/testing';

import { PiattoOrdinatoService } from './piatto-ordinato.service';

describe('PiattoOrdinatoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PiattoOrdinatoService = TestBed.get(PiattoOrdinatoService);
    expect(service).toBeTruthy();
  });
});
