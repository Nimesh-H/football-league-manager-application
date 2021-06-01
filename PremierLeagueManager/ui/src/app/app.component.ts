import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ui1';
  date:string
  urlFinal:string

  constructor(private http: HttpClient) {

  }

  findMatch(url: string) {
    this.date = (<HTMLInputElement>document.getElementById("search")).value;
    this.urlFinal = url.concat(this.date)
  }

  getUrl(){
    return this.http.get(this.urlFinal)
  }
}
