import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-base-component',
  templateUrl: './base-component.component.html',
  styleUrls: ['./base-component.component.css'],
})
export class BaseComponentComponent implements OnInit {
  @Input() id: number = 0;
  @Input() text: string = '';
  @Input() componentType: string = '';

  constructor() {}

  ngOnInit(): void {}
}
