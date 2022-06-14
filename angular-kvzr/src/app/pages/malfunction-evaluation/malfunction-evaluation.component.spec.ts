import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MalfunctionEvaluationComponent } from './malfunction-evaluation.component';

describe('MalfunctionEvaluationComponent', () => {
  let component: MalfunctionEvaluationComponent;
  let fixture: ComponentFixture<MalfunctionEvaluationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MalfunctionEvaluationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MalfunctionEvaluationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
