import { Component, ElementRef, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ViewportScroller } from '@angular/common';

@Component({
  selector: 'app-creators',
  templateUrl: './creators.component.html',
  styleUrls: ['./creators.component.css']
})
export class CreatorsComponent implements OnInit {

  constructor(
    private route: ActivatedRoute, 
    private router: Router, 
    private viewportScroller: ViewportScroller, 
    private elementRef: ElementRef) {}

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

  @HostListener('window:scroll', [])
  onWindowScroll() {
    const elementsToReveal = this.elementRef.nativeElement.querySelectorAll('.reveal-section');
    elementsToReveal.forEach((element: HTMLElement) => {
      if (!element.classList.contains('reveal')) {
        if (this.isElementInViewport(element)) {
          element.classList.add('reveal');
        }
      }
    });
  }

  isElementInViewport(element: HTMLElement): boolean {
    const rect = element.getBoundingClientRect();
    return rect.top >= 0 && rect.bottom <= window.innerHeight;
  }
  
}
