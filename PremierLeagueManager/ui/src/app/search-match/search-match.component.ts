import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource} from '@angular/material/table';
import {AppComponent} from "../app.component";


@Component({
  selector: 'app-search-match',
  templateUrl: './search-match.component.html',
  styleUrls: ['./search-match.component.css']
})

export class SearchMatchComponent implements  OnInit {
  @ViewChild(MatSort) sort: MatSort;

  dataSource: MatTableDataSource<TableHeaders>;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['playingDate', 'playingClub01', 'playingClub02', 'score01', 'score02', 'receivedPoints01', 'receivedPoints02'];

  constructor(private service: AppComponent) {}

  ngOnInit() {
    this.service.getUrl().subscribe((data) => {
      this.dataSource = new MatTableDataSource<TableHeaders>(data['body'])
    })
  }

}

export interface TableHeaders{
  playingDate: string;
  playingClub01: string;
  playingClub02: string;
  score01: number;
  score02: number;
  receivedPoints01: number;
  receivedPoints02: number;

}


