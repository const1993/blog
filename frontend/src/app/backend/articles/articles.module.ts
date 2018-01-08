import { NgModule } from '@angular/core';
import {ArticlesRoutingModule} from "./articles-routing.component";
import {ArticlesComponent} from "./articles.component";

@NgModule({
  imports: [
    ArticlesRoutingModule,
  ],
      declarations: [ArticlesComponent]
})
export class  ArticlesModule {}
