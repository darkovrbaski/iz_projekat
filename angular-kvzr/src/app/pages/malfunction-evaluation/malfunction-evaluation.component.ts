import { emptyMalfunction } from './../../model/malfunction';
import { Component, OnInit } from '@angular/core';
import { IMalfunction } from 'src/app/model/malfunction';
import { IBayesEvaluation } from 'src/app/model/bayesEvaluation';
import { MalfunctionService } from 'src/app/services/malfunction.service';

@Component({
  selector: 'app-malfunction-evaluation',
  templateUrl: './malfunction-evaluation.component.html',
  styleUrls: ['./malfunction-evaluation.component.css']
})
export class MalfunctionEvaluationComponent implements OnInit {

  malfunctionSpec: IMalfunction = emptyMalfunction;
  malfunctionEvaluations!: IBayesEvaluation[];
  symptom1!:string;
  symptom2!:string;
  constructor(private malfunctionService: MalfunctionService) {}

  ngOnInit(): void {}

  getMalfunctionEvaluation() {
    this.malfunctionSpec.symptoms=[];
    if(this.symptom1!=null)
    this.malfunctionSpec.symptoms.push(this.symptom1);
    if(this.symptom2!=null)
    this.malfunctionSpec.symptoms.push(this.symptom2);
    this.malfunctionService
      .getMalfunctionEvaluations(this.malfunctionSpec)
      .subscribe((evaluations) => {
        this.malfunctionEvaluations = evaluations;
      });
  }


}
