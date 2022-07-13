import { IPC } from './../model/pc';
import { IComputerSpec, IPersonalComponent } from './../model/computerSpec';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class SimilarityPCService {
  private similarityPCUrl = `${environment.apiUrl}/similarity`;

  constructor(private http: HttpClient) {}

  getSimilarityPCs(computerSpec: IPC): Observable<IPC[]> {
    return this.http
      .post<IPC[]>(`${this.similarityPCUrl}/pc`, computerSpec)
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
