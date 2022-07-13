import { IPC, emptyPC } from './../../model/pc';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-base-pc-recommend',
  templateUrl: './base-pc-recommend.component.html',
  styleUrls: ['./base-pc-recommend.component.css'],
})
export class BasePcRecommendComponent implements OnInit {
  @Input() pc: IPC = emptyPC;
  @Input() id: number = 0;
  constructor() {}

  ngOnInit(): void {}
}
