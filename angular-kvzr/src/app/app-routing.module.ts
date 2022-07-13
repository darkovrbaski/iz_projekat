import { SimilarityPCComponent } from './pages/similarity-pc/similarity-pc.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { MalfunctionEvaluationComponent } from './pages/malfunction-evaluation/malfunction-evaluation.component';
import { PurposeEvaluationComponent } from './pages/purpose-evaluation/purpose-evaluation.component';

const routes: Routes = [
  {
    path: '',
    component: HomepageComponent,
  },
  {
    path: 'purposeEvaluation',
    component: PurposeEvaluationComponent,
  },
  {
    path: 'malfunctionEvaluation',
    component: MalfunctionEvaluationComponent,
  },
  {
    path: 'similarityPC',
    component: SimilarityPCComponent,
  },
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
