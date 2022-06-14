import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { IComputerSpec } from '../model/computerSpec';

@Injectable({
  providedIn: 'root',
})
export class PurposeEvaluationService {
  private purposeEvaluationUrl = `${environment.apiUrl}/purposeEvaluation`;

  constructor(private http: HttpClient) {}

  getPurposeEvaluation(computerSpec: IComputerSpec): Observable<IComputerSpec> {
    return this.http
      .post<IComputerSpec>(`${this.purposeEvaluationUrl}`, computerSpec)
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
