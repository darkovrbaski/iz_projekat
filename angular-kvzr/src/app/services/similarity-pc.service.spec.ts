import { TestBed } from '@angular/core/testing';

import { SimilarityPCService } from './similarity-pc.service';

describe('SimilarityPCService', () => {
  let service: SimilarityPCService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SimilarityPCService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
