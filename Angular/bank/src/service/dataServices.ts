import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

private baseUrl = 'http://localhost:8091/bank/v1';
  constructor(private http: HttpClient) { }
  

  getCardByPan(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/card/${id}`);
  }
  
 

}