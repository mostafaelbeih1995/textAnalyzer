import { Component, Input, Output, EventEmitter, inject } from '@angular/core';
import {MatSlideToggleChange} from "@angular/material/slide-toggle";
import { TextAnalyzerStateService } from '../../services/text-analyzer-state.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  title = 'Text Analyzer';
  @Input() isOnlineMode: boolean = false;

  private textAnalyzerService = inject(TextAnalyzerStateService);

  onChangeToggle(event: MatSlideToggleChange){
    this.textAnalyzerService.setState(event.checked);
  }
}
