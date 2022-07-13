import { ComponentService } from './../../services/component.service';
import { IComponentsPurposeEvaluation } from './../../model/componentsPurposeEvaluation';
import { Component, OnInit } from '@angular/core';
import { IComputerSpec } from 'src/app/model/computerSpec';
import { IPurposeEvaluation } from 'src/app/model/purposeEvaluation';
import { PurposeEvaluationService } from 'src/app/services/purpose-evaluation.service';

@Component({
  selector: 'app-purpose-evaluation',
  templateUrl: './purpose-evaluation.component.html',
  styleUrls: ['./purpose-evaluation.component.css'],
})
export class PurposeEvaluationComponent implements OnInit {
  computerSpec: IComputerSpec = {
    numberOfCores: 1,
    singleCoreClock: 1,
    ramSize: 1,
    vRamSize: 0.5,
    gpuHashRate: 0,
  };
  purposeEvaluation: IPurposeEvaluation = {
    homeUse: 0,
    gaming: 0,
    mining: 0,
    hosting: 0,
  };
  purposeEvaluationComponent: IPurposeEvaluation = {
    homeUse: 0,
    gaming: 0,
    mining: 0,
    hosting: 0,
  };
  components: IComponentsPurposeEvaluation = {
    cpuName: '',
    gpuName: '',
    ramName: '',
    caseName: '',
    storageName: '',
    motherboardName: '',
    powerSupplyName: '',
  };
  cpuList: string[] = [];
  gpuList: string[] = [];
  ramList: string[] = [];

  constructor(
    private purposeEvaluationService: PurposeEvaluationService,
    private componentService: ComponentService
  ) {}

  ngOnInit(): void {
    this.componentService
      .getComponent('Processor')
      .subscribe((data) => (this.cpuList = data));
    this.componentService
      .getComponent('GraphicsCard')
      .subscribe((data) => (this.gpuList = data));
    this.componentService
      .getComponent('Memory')
      .subscribe((data) => (this.ramList = data));
  }

  getPurposeEvaluation() {
    this.purposeEvaluationService
      .getPurposeEvaluation(this.computerSpec)
      .subscribe((purposeEvaluation) => {
        this.purposeEvaluation = purposeEvaluation;
      });
  }

  getPurposeEvaluationComponent() {
    this.purposeEvaluationService
      .getPurposeEvaluationComponent(this.components)
      .subscribe((purposeEvaluation) => {
        this.purposeEvaluationComponent = purposeEvaluation;
      });
  }
}
