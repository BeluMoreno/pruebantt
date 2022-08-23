import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Client } from '../model/client';
import {Router } from '@angular/router';
import { IdentityDocument } from '../model/identity-document';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private clientUrl: string;
  private backupUrl = 'api/getClient/client.json'
  clientResponse!: Client;
  

  constructor(private http: HttpClient,
    private router: Router,) {
    this.clientUrl = 'http://localhost:8090/get-client';
   }

   private setSearchResults(clientRes : Client){
    this.clientResponse = clientRes;
    this.router.navigate(['details']);
   }

   public search(id : IdentityDocument){

    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const options = { headers: headers };
    this.http.post<Client>(this.clientUrl,id, options).subscribe(
      results=> this.setSearchResults(results),
      error => this.backUpSearch(id)
    );
   }

   private backUpSearch(id : IdentityDocument){
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const options = { headers: headers };
    this.http.get<Client>(this.backupUrl).subscribe(
      results=> this.setSearchResults(results));
   }
}
