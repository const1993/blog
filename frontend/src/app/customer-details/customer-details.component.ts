
import { Component, OnInit, Input } from '@angular/core';

import { Customer } from '../customer';
import { DataService } from '../data.service';

import { ActivatedRoute, Params } from '@angular/router';

import { Location } from '@angular/common';

import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-customer-detail',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css'],
})

export class CustomerDetailsComponent implements OnInit {

  customer: Customer;

  constructor(
    private dataService: DataService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) => this.dataService.getCustomer(+params['id']))
      .subscribe(customer => this.customer = customer);
  }

  goBack(): void {
    this.location.back();
  }
}
