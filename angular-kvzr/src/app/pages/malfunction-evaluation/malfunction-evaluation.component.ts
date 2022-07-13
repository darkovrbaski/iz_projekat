import { ComponentService } from './../../services/component.service';
import { emptyMalfunction } from './../../model/malfunction';
import { Component, OnInit } from '@angular/core';
import { IMalfunction } from 'src/app/model/malfunction';
import { IBayesEvaluation } from 'src/app/model/bayesEvaluation';
import { MalfunctionService } from 'src/app/services/malfunction.service';

@Component({
  selector: 'app-malfunction-evaluation',
  templateUrl: './malfunction-evaluation.component.html',
  styleUrls: ['./malfunction-evaluation.component.css'],
})
export class MalfunctionEvaluationComponent implements OnInit {
  malfunctionSpec: IMalfunction = emptyMalfunction;
  malfunctionEvaluations!: IBayesEvaluation[];
  symptom1!: string;
  symptom2!: string;
  cpuList: string[] = [];
  gpuList: string[] = [];
  ramList: string[] = [];
  caseList: string[] = [];
  storageList: string[] = [];
  motherboardList: string[] = [];
  powerSupplyList: string[] = [];

  constructor(
    private malfunctionService: MalfunctionService,
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
    this.componentService
      .getComponent('Case')
      .subscribe((data) => (this.caseList = data));
    this.componentService
      .getComponent('Storage')
      .subscribe((data) => (this.storageList = data));
    this.componentService
      .getComponent('Motherboard')
      .subscribe((data) => (this.motherboardList = data));
    this.componentService
      .getComponent('PowerSupply')
      .subscribe((data) => (this.powerSupplyList = data));
  }

  getMalfunctionEvaluation() {
    this.malfunctionSpec.symptoms = [];
    if (this.symptom1 != null)
      this.malfunctionSpec.symptoms.push(this.symptom1);
    if (this.symptom2 != null)
      this.malfunctionSpec.symptoms.push(this.symptom2);
    this.malfunctionService
      .getMalfunctionEvaluations(this.malfunctionSpec)
      .subscribe((evaluations) => {
        this.malfunctionEvaluations = evaluations;
      });
  }
}
