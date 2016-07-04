import { Component } from '@angular/core';
import { Routes , ROUTER_DIRECTIVES} from '@angular/router';
import { ROUTES } from './routes';
import 'rxjs/Rx';

// Test for 
/* test again */

/*** test yet again */

@Component({
  selector: 'my-app',
  template: `
    <router-outlet></router-outlet>
  `,
  directives: [ ROUTER_DIRECTIVES ]

})
@Routes(ROUTES)
export class AppComponent {
  public title = 'Reporting Modules';
}
