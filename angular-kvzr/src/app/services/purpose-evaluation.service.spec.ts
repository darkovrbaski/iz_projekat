import { TestBed } from '@angular/core/testing';

import { PurposeEvaluationService } from './purpose-evaluation.service';

describe('PurposeEvaluationService', () => {
  let service: PurposeEvaluationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PurposeEvaluationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
