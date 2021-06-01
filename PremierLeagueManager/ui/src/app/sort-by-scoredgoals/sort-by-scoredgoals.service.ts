import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class SortByScoredgoalsService {
  private listClubsUrl = '/sortGoals';

  constructor(private http: HttpClient) {}

  public getListOfClubs(): any{
    return this.http.get(this.listClubsUrl)
  }

}
