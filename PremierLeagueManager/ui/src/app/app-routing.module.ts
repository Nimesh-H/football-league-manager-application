import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PointsTableComponent } from './points-table/points-table.component';
import { RandomMatchComponent } from './random-match/random-match.component';
import { SearchMatchComponent } from './search-match/search-match.component';
import { SortByScoredgoalsComponent } from './sort-by-scoredgoals/sort-by-scoredgoals.component';
import { SortByWinsComponent } from './sort-by-wins/sort-by-wins.component';
import { ViewAllMatchesComponent } from './view-all-matches/view-all-matches.component';


const routes: Routes = [
  {  path:'pointsTable',component: PointsTableComponent},
  {  path:'randomMatch',component: RandomMatchComponent},
  {  path:'searchMatch',component: SearchMatchComponent},
  {  path:'sortGoals',component: SortByScoredgoalsComponent},
  {  path:'sortWins',component: SortByWinsComponent},
  {  path:'viewAllMatches',component: ViewAllMatchesComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
