import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientDetailComponent } from './client-details/client-detail/client-detail.component';
import { ClientSearchComponent } from './client-search/client-search/client-search.component';


const routes: Routes = [
  {path:'', component: ClientSearchComponent},
  {path:'details', component: ClientDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
