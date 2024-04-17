import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title: string = 'TextAnalyzer';
  isOnlineMode: boolean = false;

  // toggleMode(isOnlineMode: boolean) {
  //   console.log("app main " + isOnlineMode );
  //   this.isOnlineMode = isOnlineMode;
  // }
}


