import { Component, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-creators',
  templateUrl: './creators.component.html',
  styleUrls: ['./creators.component.css']
})

export class CreatorsComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private elementRef: ElementRef) { }

  ngOnInit(): void {
    this.route.fragment.subscribe(fragment => {
      if (fragment) {
        this.scrollToElement(fragment);
      }
    });
  }

  scrollToElement(targetID: string) {
    const targetElement = this.elementRef.nativeElement.querySelector(`#${targetID}`);
    if (targetElement) {
      targetElement.scrollIntoView({ behavior: 'smooth' });
    }
  }

  handleClick(event: Event, targetID: string) {
    event.preventDefault();
    this.scrollToElement(targetID);
    this.router.navigate([], { relativeTo: this.route, queryParamsHandling: 'merge' });
  }

}
