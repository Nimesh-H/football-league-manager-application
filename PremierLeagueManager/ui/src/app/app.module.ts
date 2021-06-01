import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PointsTableComponent } from './points-table/points-table.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { SortByWinsComponent } from './sort-by-wins/sort-by-wins.component';
import { SortByScoredgoalsComponent } from './sort-by-scoredgoals/sort-by-scoredgoals.component';
import { SearchMatchComponent } from './search-match/search-match.component';
import { ViewAllMatchesComponent } from './view-all-matches/view-all-matches.component';
import { RandomMatchComponent } from './random-match/random-match.component';
import {PointsTableService} from "./points-table/points-table.service";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {AppHttpInterceptorService} from "./points-table/http-interceptor.service";
import {HttpClientModule} from '@angular/common/http';
import {SortByScoredgoalsService} from "./sort-by-scoredgoals/sort-by-scoredgoals.service";
import {SortByWinsService} from "./sort-by-wins/sort-by-wins.service";
import {MatchService} from "./view-all-matches/view-all-matches.service";
import {RandomMatchService} from "./random-match/random-match.service";


@NgModule({
  declarations: [
    AppComponent,
    PointsTableComponent,
    SortByWinsComponent,
    SortByScoredgoalsComponent,
    SearchMatchComponent,
    ViewAllMatchesComponent,
    RandomMatchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    HttpClientModule
  ],
  providers: [

    SortByWinsService,
    {
      multi: true,
      provide: HTTP_INTERCEPTORS,
      useClass: AppHttpInterceptorService
    },
    SortByScoredgoalsService,
    {
      multi: true,
      provide: HTTP_INTERCEPTORS,
      useClass: AppHttpInterceptorService
    },
    PointsTableService,
    {
      multi: true,
      provide: HTTP_INTERCEPTORS,
      useClass: AppHttpInterceptorService
    },
    MatchService,
    {
      multi: true,
      provide: HTTP_INTERCEPTORS,
      useClass: AppHttpInterceptorService
    },
    RandomMatchService,
    {
      multi: true,
      provide: HTTP_INTERCEPTORS,
      useClass: AppHttpInterceptorService
    },

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
