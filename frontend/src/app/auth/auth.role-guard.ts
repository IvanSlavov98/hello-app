import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {AuthService} from '../core/auth-service/auth-service';
import {Roles} from '../core/auth-service/entity';

export function canActivateRoles(required: Roles[]): CanActivateFn {
  return () => {
    const auth = inject(AuthService)
    const router = inject(Router)

    if (!auth.isLoggedIn()) { router.navigate(['/login']); return false; }
    if (auth.hasAnyRole(required)) return true;

    router.navigate(['/'])
    return false
  }
}
