import { Component, Input, OnDestroy } from '@angular/core';
import {MatSlideToggleChange} from "@angular/material/slide-toggle";
import { TextAnalyzerStateService } from '../../services/text-analyzer-state.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnDestroy{
  title = 'Text Analyzer';
  @Input() isOnlineMode: boolean = false;
  stateSubscriber!: Subscription;

  constructor(private textAnalyzerService: TextAnalyzerStateService){  
  }


  getState(): void {
    this.textAnalyzerService.getState().subscribe(res => {
      this.isOnlineMode = res;
    });
  }
  
  onChangeToggle(event: MatSlideToggleChange){
    this.textAnalyzerService.setState(event.checked);
  }

  ngOnDestroy() {
    this.stateSubscriber.unsubscribe();
  }
}
