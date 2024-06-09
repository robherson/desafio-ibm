import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessOperationModalComponent } from './process-operation-modal.component';

describe('ProcessOperationModalComponent', () => {
  let component: ProcessOperationModalComponent;
  let fixture: ComponentFixture<ProcessOperationModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProcessOperationModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProcessOperationModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
