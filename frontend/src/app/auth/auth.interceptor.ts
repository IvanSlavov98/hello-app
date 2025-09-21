import {HttpErrorResponse, HttpEvent, HttpRequest} from '@angular/common/http';
import {HttpHandlerFn, HttpInterceptorFn} from '@angular/common/http';
import {inject} from '@angular/core';
import {AuthService} from '../core/auth-service/auth-service';
import {catchError, filter, from, Observable, switchMap, throwError} from 'rxjs';


function withBearer(req: HttpRequest<any>, token: string) {
  return req.clone({ setHeaders: { Authorization: `Bearer ${token}` } });
}

export const authInterceptor: HttpInterceptorFn = (
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
): Observable<HttpEvent<unknown>> => {
  const auth = inject(AuthService);
  const token = auth.getToken();
  const authedReq = token ? withBearer(req, token) : req;

  return next(authedReq).pipe(
    catchError((error: HttpErrorResponse) => {
      const alreadyRetried = req.headers.has('X-Retry');
      const isAuthEndpoint = req.url.startsWith('/auth/');
      if (error.status === 401 && !alreadyRetried && !isAuthEndpoint) {
        return from(auth.refreshAccessToken()).pipe(
          filter((t): t is string => typeof t === 'string' && t.length > 0),
          switchMap((newToken: string) => {
            const retried = withBearer(
              req.clone({setHeaders: {'X-Retry': '1'}}),
              newToken
            );
            return next(retried);
          }),
          catchError(() => throwError(() => error))
        );
      }
      return throwError(() => error);
    })
  );
}
