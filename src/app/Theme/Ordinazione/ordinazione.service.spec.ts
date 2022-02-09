import { TestBed } from '@angular/core/testing';

import { OrdinazioneService } from './ordinazione.service';

describe('OrdinazioneService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: OrdinazioneService = TestBed.get(OrdinazioneService);
    expect(service).toBeTruthy();
  });
});
