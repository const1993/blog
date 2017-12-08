import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BackendComponent} from "./backend.component";
import {AuthGuard} from "./auth-guard/auth-guard.service";

const routes: Routes = [
  {
    path: '',
    component: BackendComponent,
    children: [
      {
        path: '',
        loadChildren: './articles/articles.module#ArticlesModule',
        // canActivate: [AuthGuard]
      },
      {
        path: 'articles',
        loadChildren: './articles/articles.module#ArticlesModule',
        // canActivate: [AuthGuard]
      },
      {
        path: 'settings',
        loadChildren: './settings/settings.module#SettingsModule',
        // canActivate: [AuthGuard]
      },
      {
        path: 'editor/:id',
        loadChildren: './editor/editor.module#EditorModule',
        // canActivate: [AuthGuard]
      }

    ]
  },
  {
    path: '/backend/*',  redirectTo: "/backend"
  },
  {
    path: '**',  redirectTo: "/home"
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BackendRoutingModule { }
