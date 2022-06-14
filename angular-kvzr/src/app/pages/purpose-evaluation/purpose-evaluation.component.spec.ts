import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurposeEvaluationComponent } from './purpose-evaluation.component';

describe('PurposeEvaluationComponent', () => {
  let component: PurposeEvaluationComponent;
  let fixture: ComponentFixture<PurposeEvaluationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PurposeEvaluationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PurposeEvaluationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
