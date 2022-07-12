import { IComponentProperty } from './../model/componentProperty';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ComponentService {
  private componentUrl = `${environment.apiUrl}/component`;

  constructor(private http: HttpClient) {}

  getComponent(component: string): Observable<any> {
    return this.http
      .post<any>(`${this.componentUrl}`, component)
      .pipe(catchError(this.handleError));
  }

  getComponentPropery(componentProperty: IComponentProperty): Observable<any> {
    return this.http
      .post<any>(`${this.componentUrl}/property`, componentProperty)
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
