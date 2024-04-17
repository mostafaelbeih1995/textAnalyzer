import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {HttpClient} from "@angular/common/http";
import { TextAnalyzerStateService } from '../../services/text-analyzer-state.service';
// import { AnalysisType} from '../../enums/AnalysisType';
// import { TypeRelatedCharacter} from '../../enums/TypeRelatedCharacter';
// import { environment } from '../../../environments/environment';
// import { Observable, from, of } from 'rxjs';

@Component({
  selector: 'app-text-analyzer',
  templateUrl: './text-analyzer.component.html',
  styleUrl: './text-analyzer.component.css'
})
export class TextAnalyzerComponent implements OnInit{
  state: boolean = true;
  textForm!: FormGroup;
  analyzeList: any[] = [];
  private textAnalyzerService = inject(TextAnalyzerStateService);
  private fb = inject(FormBuilder);
  private http = inject(HttpClient);

  
  ngOnInit(): void {
    this.getState();
    this.createForm();
  }

  createForm(): void {
    this.textForm = this.fb.group({
      text: [null, [Validators.required]],
      type: ['ALLLETTERS', [Validators.required]],
    })
  }

  getState(): boolean {
    this.textAnalyzerService.getState().subscribe(res => {
      this.state = res;
    })
    return this.state;
  }

  onSubmit(): void {
    if (!this.textForm.valid) {
      return;
    }
    this.textAnalyzerService.analyzeText(this.textForm.value['text'], this.textForm.value['type'], this.getState()).subscribe(result => {
      if(result && Object.keys(result).length === 0)
        console.log('error');
        //handle error
       else
        this.analyzeList.push(result);
    });;
  }
}
