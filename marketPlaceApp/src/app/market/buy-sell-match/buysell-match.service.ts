import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class BuysellMatchService {

  readonly baseUrl = '/api/match';
  constructor(private http: HttpClient) { }

  getMatches() {
    return this.http.get(`${this.baseUrl}`);
  }
}
