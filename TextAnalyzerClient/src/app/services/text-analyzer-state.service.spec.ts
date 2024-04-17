import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TextAnalyzerStateService } from './text-analyzer-state.service';

describe('TextAnalyzerStateService', () => {
  let service: TextAnalyzerStateService;
  let http: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule],
      providers:[TextAnalyzerStateService]
    });
    service = TestBed.inject(TextAnalyzerStateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
