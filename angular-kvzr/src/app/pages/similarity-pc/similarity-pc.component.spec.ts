import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SimilarityPCComponent } from './similarity-pc.component';

describe('SimilarityPCComponent', () => {
  let component: SimilarityPCComponent;
  let fixture: ComponentFixture<SimilarityPCComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SimilarityPCComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SimilarityPCComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
