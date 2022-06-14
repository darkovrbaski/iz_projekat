
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { IBayesEvaluation } from '../model/bayesEvaluation';
import { IMalfunction } from '../model/malfunction';

@Injectable({
  providedIn: 'root',
})
export class MalfunctionService {
  private malfunctionUrl = `${environment.apiUrl}/malfunctionEvaluation`;

  constructor(private http: HttpClient) {}

  getMalfunctionEvaluations(
    malfunctionSpec: IMalfunction
  ): Observable<IBayesEvaluation[]> {
    return this.http
      .post<any>(`${this. malfunctionUrl}`, malfunctionSpec)
      .pipe(catchError(this.handleError));
  }

  handleError(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(() => {
      return errorMessage;
    });
  }
}
