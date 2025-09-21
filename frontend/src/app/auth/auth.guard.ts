import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {AuthService} from '../core/auth-service/auth-service';

export const canActivateWith: CanActivateFn = () => {
  const auth = inject(AuthService)
  const router = inject(Router)

  if(auth.isLoggedIn()) return true

  router.navigate(['/login'])
  return false
}
