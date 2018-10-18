import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class HttpService {

  constructor(private http: HttpClient) {
  }

  getTimetable(stationName: string) {
    return this.http.get('http://localhost:8080/api/buyer/timetable/' + stationName);
  }
}
