import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ball } from '../models/ball';

@Injectable({
  providedIn: 'root'
})
export class BallService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  endpoint:string="http://localhost:8080";
  constructor(private httpClient:HttpClient) { 
  }
    getBalls():Observable<any>{
      return this.httpClient.get<any>(this.endpoint);
    }
    getBallById(id:number):Observable<Ball>{
      return this.httpClient.get<Ball>(this.endpoint+"/"+id);
    }
    addBall(ball:Ball){
      return this.httpClient.post(this.endpoint, JSON.stringify(ball), this.httpOptions);
    }
    updateBall(ball:Ball, id:number){
      return this.httpClient.put(this.endpoint+"/"+id, JSON.stringify(ball), this.httpOptions);
    }
    deleteBallById(id:number){
      return this.httpClient.delete(this.endpoint+"/"+id);
    }
  
}

