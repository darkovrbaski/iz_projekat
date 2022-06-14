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
    numberOfCores: 0,
    singleCoreClock: 0,
    ramSize: 0,
    vRamSize: 0,
    gpuHashRate: 0,
  };
  purposeEvaluation: IPurposeEvaluation = {
    homeUse: 0,
    gaming: 0,
    mining: 0,
    hosting: 0,
  };

  constructor(private purposeEvaluationService: PurposeEvaluationService) {}

  ngOnInit(): void {}

  getPurposeEvaluation() {
    this.purposeEvaluationService
      .getPurposeEvaluation(this.computerSpec)
      .subscribe((purposeEvaluation) => {
        this.purposeEvaluation = purposeEvaluation;
      });
  }
}
