import {Component, OnInit} from '@angular/core';
import {HttpService} from "../HttpService";
import {Train} from "./train.model";

@Component({
  selector: 'train-list',
  templateUrl: './train-list.component.html',
  styleUrls: ['./train-list.component.css']
})
export class TrainListComponent implements OnInit {
  trains: Array<Train>;

  constructor(private service: HttpService) {
  }

  ngOnInit() {
    this.service.getTrains().subscribe(data => {
      this.trains = data;
    });
  }
}
