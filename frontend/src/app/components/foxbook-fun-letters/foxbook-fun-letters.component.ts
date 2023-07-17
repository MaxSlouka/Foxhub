import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-foxbook-fun-letters',
  templateUrl: './foxbook-fun-letters.component.html',
  styleUrls: ['./foxbook-fun-letters.component.css']
})
export class FoxbookFunLettersComponent implements OnInit {
  ngOnInit() {
    const spans = document.querySelectorAll('.word span');

    spans.forEach((span, idx) => {
      span.addEventListener('click', (e: Event) => {
        const target = e.target as HTMLElement;
        target.classList.add('active');
      });
      span.addEventListener('animationend', (e: Event) => {
        const target = e.target as HTMLElement;
        target.classList.remove('active');
      });

      // Initial animation
      setTimeout(() => {
        span.classList.add('active');
      }, 750 * (idx + 1));
    });
  }
}







