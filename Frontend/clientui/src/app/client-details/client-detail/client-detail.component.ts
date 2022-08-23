import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client';
import { ClientService } from 'src/app/service/client-service.service';

@Component({
  selector: 'app-client-detail',
  templateUrl: './client-detail.component.html',
  styleUrls: ['./client-detail.component.css']
})
export class ClientDetailComponent implements OnInit {

  client: Client = new Client();
  success:boolean = false;

  validateClient(client: Client){
    if(client == null){
      client = new Client();
      return client;
    }
    this.success = true;
    return client;
  }

  constructor(private clientService: ClientService) { 
    this.client = this.validateClient(clientService.clientResponse);
  }

  ngOnInit(){
    }
}
