import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class RandomMatchService {
  private matches = '/randomMatch';

  constructor(private http: HttpClient) {}

  public getMatches(): any{
    return this.http.get(this.matches)
  }

}
