import { TestBed } from '@angular/core/testing';

import { TextAnalyzerStateService } from './text-analyzer-state.service';

describe('TextAnalyzerStateService', () => {
  let service: TextAnalyzerStateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TextAnalyzerStateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
