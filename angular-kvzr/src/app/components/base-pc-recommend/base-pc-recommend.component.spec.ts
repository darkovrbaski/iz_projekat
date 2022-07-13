import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BasePcRecommendComponent } from './base-pc-recommend.component';

describe('BasePcRecommendComponent', () => {
  let component: BasePcRecommendComponent;
  let fixture: ComponentFixture<BasePcRecommendComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BasePcRecommendComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BasePcRecommendComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
