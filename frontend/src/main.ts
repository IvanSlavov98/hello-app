import {bootstrapApplication} from '@angular/platform-browser';
import {appConfig} from './app/app.config';
import {App} from './app/app';
import {provideHttpClient, withInterceptors} from '@angular/common/http';
import {provideRouter} from '@angular/router';
import {routes} from './app/app.routes';
import {inject, provideAppInitializer} from '@angular/core';

bootstrapApplication(App, appConfig).catch(err => console.error(err));
