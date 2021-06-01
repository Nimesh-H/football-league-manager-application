import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class PointsTableService {
  private listClubsUrl = '/pointTable';

  constructor(private http: HttpClient) {}

  public getListOfClubs(): any{
    return this.http.get(this.listClubsUrl)
  }

}
