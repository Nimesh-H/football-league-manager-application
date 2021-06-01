import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from "@angular/material/table";
import { SortByWinsService } from "./sort-by-wins.service";

@Component({
  selector: 'app-sort-by-wins',
  templateUrl: './sort-by-wins.component.html',
  styleUrls: ['./sort-by-wins.component.css']
})
export class SortByWinsComponent implements OnInit {

  @ViewChild(MatSort) sort: MatSort;

  dataSource: MatTableDataSource<TableHeaders>;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['clubCode', 'clubName', 'location', 'points', 'numberOfMatchesPlayed', 'goalsScored', 'goalsReceived', 'wins', 'defeats', 'draws'];

  constructor(private service: SortByWinsService) {

  }

  ngOnInit() {
    this.dataSource = new MatTableDataSource<TableHeaders>();
    this.service.getListOfClubs().subscribe((data) => {
      this.dataSource = new MatTableDataSource<TableHeaders>(data['body'])

    })
  }
}

export interface TableHeaders{
  clubCode: string;
  clubName: string;
  location: string;
  points: number;
  numberOfMatchesPlayed: number;
  goalsScored: number;
  goalsReceived: number;
  wins: number;
  defeats: number;
  draws: number;
}
