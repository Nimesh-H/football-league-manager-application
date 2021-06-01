import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class MatchService {
  private matches = '/matches';

  constructor(private http: HttpClient) {}

  public getMatches(): any{
    return this.http.get(this.matches)
  }

}
