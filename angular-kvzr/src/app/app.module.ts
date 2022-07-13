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
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
