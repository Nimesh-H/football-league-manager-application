import {Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {RandomMatchService} from "./random-match.service";

@Component({
  selector: 'app-random-match',
  templateUrl: './random-match.component.html',
  styleUrls: ['./random-match.component.css']
})
export class RandomMatchComponent implements OnInit {

  @ViewChild(MatSort) sort: MatSort;

  dataSource: MatTableDataSource<TableHeaders>;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['playingDate', 'playingClub01', 'playingClub02', 'score01', 'score02', 'receivedPoints01', 'receivedPoints02'];

  constructor(private service: RandomMatchService) {

  }

  ngOnInit() {
    this.dataSource = new MatTableDataSource<TableHeaders>();
    this.service.getMatches().subscribe((data) => {
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
