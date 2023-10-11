import {Injectable} from '@angular/core';
import {HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse} from '@angular/common/http';
import {Observable, throwError, of} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';
import {Router} from '@angular/router';

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {
  constructor(private router: Router) {
  };

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        const handled = this.handleServerSideError(error);

        if (!handled) {
          this.router.navigate(['/server-side-error-page']);
        }
        throw error;
      })
    );
  }


  private handleServerSideError(error: HttpErrorResponse):boolean {
    let handled: boolean = false;

    switch (error.status) {
      case 404:
        this.router.navigate(['/error-page']);
        return handled = true;
      case 400:
        return handled = true;
    }

    let errorCode: string = error.status.toString();

    if (errorCode.at(0) === '5' || error.status == 0) {
      this.router.navigate(['/home']);
      handled = true;
    }
    return handled;
  }
}
