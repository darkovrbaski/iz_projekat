import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { PurposeEvaluationComponent } from './pages/purpose-evaluation/purpose-evaluation.component';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { FormsModule } from '@angular/forms';
import { MalfunctionEvaluationComponent } from './pages/malfunction-evaluation/malfunction-evaluation.component';
import { SimilarityPCComponent } from './pages/similarity-pc/similarity-pc.component';
import { BasePcRecommendComponent } from './components/base-pc-recommend/base-pc-recommend.component';
import { RecommendComponentPcComponent } from './pages/recommend-component-pc/recommend-component-pc.component';
import { BaseComponentComponent } from './components/base-component/base-component.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    PurposeEvaluationComponent,
    HomepageComponent,
    MalfunctionEvaluationComponent,
    SimilarityPCComponent,
    BasePcRecommendComponent,
    RecommendComponentPcComponent,
    BaseComponentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatProgressSpinnerModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
