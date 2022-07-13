import { Component, OnInit } from '@angular/core';
import {
  emptyPersonalComponent,
  IPersonalComponent,
} from 'src/app/model/computerSpec';
import { RecommendComponentService } from 'src/app/services/recommend-component.service';

@Component({
  selector: 'app-recommend-component-pc',
  templateUrl: './recommend-component-pc.component.html',
  styleUrls: ['./recommend-component-pc.component.css'],
})
export class RecommendComponentPcComponent implements OnInit {
  myPCComponents: IPersonalComponent = emptyPersonalComponent;

  recommendedCPU = [];
  recommendedGPU = [];
  recommendedRAM = [];
  recommendedMotherboard = [];

  constructor(private recommendComponentService: RecommendComponentService) {}

  ngOnInit(): void {}

  search() {}
}
