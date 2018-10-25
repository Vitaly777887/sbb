import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Station} from "./station/station.model";
import {Train} from "./train-list/train.model";

@Injectable()
export class HttpService {

  private adminUrl: string = "http://localhost:8080/api/admin";
  private buyerUrl: string = "http://localhost:8080/api/buyer";

  constructor(private http: HttpClient) {
  }

  getTimetable(stationName: string) {
    return this.http.get('http://localhost:8080/api/buyer/timetable/' + stationName);
  }


  //admin
  addStation(station: Station) {
    let data = {"stationName": station.name};
    this.http.post(this.adminUrl + '/stations', data);
  }

  addTrains(numberTrain: number, countPassengers: number) {
    let data = {"numberTrain": numberTrain, "countPassengers": countPassengers};
    this.http.post(this.adminUrl + '/trains', data);
  }

  getPassengersOnTrain(numberTrain: number) {
    return this.http.get(this.adminUrl + '/passengersontrain/' + numberTrain);
  }

  getTrains() {
    return this.http.get<Train[]>(this.adminUrl + '/trains');
  }
}
