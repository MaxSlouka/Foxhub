import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { DataService } from "../../_services/api/data.service";

@Component({
  selector: 'app-people-page',
  templateUrl: './people-page.component.html',
  styleUrls: ['./people-page.component.css']
})

export class PeoplePageComponent implements OnInit, AfterViewInit {
  @ViewChild('customRange3', { static: true }) rangeInputRef!: ElementRef<HTMLInputElement>;
  @ViewChild('rangeValue', { static: true }) rangeValueRef!: ElementRef<HTMLSpanElement>;
  filterContentExpanded: boolean = true;

  constructor(public dataService: DataService) {
  }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    const rangeInput = this.rangeInputRef.nativeElement;
    const rangeValue = this.rangeValueRef.nativeElement;

    rangeInput.addEventListener('input', (event) => {
      const target = event.target as HTMLInputElement;
      rangeValue.textContent = target.value;
    });
  }

  toggleFilterContent(): void {
    this.filterContentExpanded = !this.filterContentExpanded;
  }
}
