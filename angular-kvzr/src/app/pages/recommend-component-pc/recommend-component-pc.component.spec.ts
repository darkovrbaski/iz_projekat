import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommendComponentPcComponent } from './recommend-component-pc.component';

describe('RecommendComponentPcComponent', () => {
  let component: RecommendComponentPcComponent;
  let fixture: ComponentFixture<RecommendComponentPcComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecommendComponentPcComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommendComponentPcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
