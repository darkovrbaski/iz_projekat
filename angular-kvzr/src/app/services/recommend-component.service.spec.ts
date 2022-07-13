import { TestBed } from '@angular/core/testing';

import { RecommendComponentService } from './recommend-component.service';

describe('RecommendComponentService', () => {
  let service: RecommendComponentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecommendComponentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
