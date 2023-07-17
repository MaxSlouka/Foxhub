import {Component, OnInit} from '@angular/core';
import {DataService} from "../../_services/api/data.service";


@Component({
  selector: 'app-people-page',
  templateUrl: './people-page.component.html',
  styleUrls: ['./people-page.component.css']
})
export class PeoplePageComponent implements OnInit {

constructor(public dataService: DataService) {
}

  ngOnInit(): void {
  }
}
