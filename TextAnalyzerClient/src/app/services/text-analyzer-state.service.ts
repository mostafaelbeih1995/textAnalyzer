import { HttpClient } from '@angular/common/http';
import { Injectable} from '@angular/core';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { environment } from '../../environments/environment';
import { AnalysisType } from '../enums/AnalysisType';
import {TypeRelatedCharacter} from '../enums/TypeRelatedCharacter';

@Injectable({
  providedIn: 'root'
})

export class TextAnalyzerStateService {

  private onlineMode: BehaviorSubject<boolean> = new BehaviorSubject(false);
  constructor(private http: HttpClient) { }

  getState(): Observable<boolean> {
    return this.onlineMode.asObservable();
  }

  setState(value: boolean): void {
    this.onlineMode.next(value)
  }

  public analyzeText(text: string, type: AnalysisType, onlineMode: boolean): Observable<any> {
    if(!onlineMode){
      return of(this.analyzeTextOffline(text, type));
    }
    else{
    return this.http.post<any>(environment.apiUrl, {text, type});
    }

  }

  private analyzeTextOffline(text: string, type: AnalysisType): any{
    
    let analysisMap = new Map<string, number>();
    const charactersToCheck = type === AnalysisType.Vowels ? TypeRelatedCharacter.Vowels :
                              type === AnalysisType.Consonants ? TypeRelatedCharacter.Consonants :
                              TypeRelatedCharacter.AllLetters;

    text.toLowerCase().split('').forEach(char => {
      if(charactersToCheck.includes(char)) {
        let count = analysisMap.get(char) || 0;
        count++;
        analysisMap.set(char, count);
      }
    });
    return Object.fromEntries(analysisMap);
  }
}
