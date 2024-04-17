import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {HttpClient} from "@angular/common/http";
import { TextAnalyzerStateService } from '../../services/text-analyzer-state.service';
import { AnalysisType } from '../../enums/AnalysisType';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-text-analyzer',
  templateUrl: './text-analyzer.component.html',
  styleUrl: './text-analyzer.component.css'
})
export class TextAnalyzerComponent implements OnInit, OnDestroy{
  state: boolean = true;
  textForm!: FormGroup;
  analysisList: any[] = [];
  type = AnalysisType;
  stateSubscriber!: Subscription;
  
  constructor(private textAnalyzerService: TextAnalyzerStateService, private fb: FormBuilder, private http: HttpClient){
  }
  ngOnInit(): void {
    this.getState();
    this.createForm();
  }

  createForm(): void {
    this.textForm = this.fb.group({
      text: [null, [Validators.required]],
      type: [this.type.AllLetters, [Validators.required]],
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
        this.analysisList.push(result);
    });;
  }

  ngOnDestroy() {
    this.stateSubscriber.unsubscribe();
  }
}
