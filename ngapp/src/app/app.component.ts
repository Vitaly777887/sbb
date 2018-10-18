import {Component} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {HttpService} from "./HttpService";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ngapp';

  stationName: string;
  data;

  constructor(private httpService: HttpService) {
  }

  submit(form: FormGroup) {
    this.httpService.getTimetable(form.value.stationName).subscribe((name) => {
      console.log(name);
      this.data = name;
    }, (error => console.log(error)));
  }
}
