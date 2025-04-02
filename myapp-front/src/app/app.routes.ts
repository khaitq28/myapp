import { Routes } from '@angular/router';
import {MessagesComponent} from "./components/messages/messages.component";
import {PartnersComponent} from "./components/partners/partners.component";

export const routes: Routes = [

  {
    path: 'messages',
    component: MessagesComponent,
  },
  {
    path: 'partners',
    component: PartnersComponent,
  },
  // { path: '', redirectTo: '/messages', pathMatch: 'full' }

];
