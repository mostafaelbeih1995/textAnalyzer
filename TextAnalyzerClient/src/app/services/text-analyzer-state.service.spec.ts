import { TestBed, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TextAnalyzerStateService } from './text-analyzer-state.service';
import { AnalysisType } from '../enums/AnalysisType';
import { environment } from '../../environments/environment';

describe('TextAnalyzerStateService', () => {
  let service: TextAnalyzerStateService;
  let http: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule],
      providers:[TextAnalyzerStateService]
    });
    service = TestBed.inject(TextAnalyzerStateService);
    http = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    http.verify();
  });

  it('should analyze text offline mode positive scenario'), () => {
    const expectedOutput = { o: 1, a: 2 };
    const text = 'mostafa';
    const type = AnalysisType.Vowels;
    const isOnlineMode = false;

    service.analyzeText(text, type, isOnlineMode).subscribe(result => expect(result).toEqual(expectedOutput));
  }

  it('should analyze text online mode positive scenario', inject(
    [HttpTestingController, TextAnalyzerStateService],
    (httpMock: HttpTestingController, service: TextAnalyzerStateService) => {
    
    const expectedOutput = { o: 1, a: 2 };
    const text = 'mostafa';
    const type = AnalysisType.Vowels;
    const isOnlineMode = true;


    service.analyzeText(text, type, isOnlineMode).subscribe(data => {
      expect(data).toEqual(expectedOutput);
    });

    const req = http.expectOne(`${environment.apiUrl}`);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual({text, type});
  }
));

});
