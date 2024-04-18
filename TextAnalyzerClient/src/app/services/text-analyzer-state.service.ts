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
    
    const charactersToCheck = this.getRelevantCharacters(type);

     const result: {[key: string]: number} = {};
    for(let i = 0; i < text.length; i++){
      const character = text.charAt(i).toLowerCase();
      if(charactersToCheck.includes(character)){
        result[character] = (result[character] || 0) + 1;
      }
    }
    return result;
  }

  private getRelevantCharacters(type: AnalysisType): string {
    switch(type) {
      case AnalysisType.Vowels:
        return TypeRelatedCharacter.Vowels;
      case AnalysisType.Consonants:
        return TypeRelatedCharacter.Consonants;
      default:
        return TypeRelatedCharacter.Vowels;
    }
  }
}
