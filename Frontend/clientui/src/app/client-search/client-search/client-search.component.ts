import { Component, OnInit, Input, ElementRef, HostListener } from '@angular/core';
import { ClientService } from 'src/app/service/client-service.service';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import { IdentityDocument } from 'src/app/model/identity-document';

@Component({
  selector: 'app-client-search',
  templateUrl: './client-search.component.html',
  styleUrls: ['./client-search.component.css']
})
export class ClientSearchComponent implements OnInit{

  id : IdentityDocument;
  previousValue: string = '';
  
  idForm = new FormGroup({
    documentType: new FormControl('',[
      Validators.required,
  
    ]),
    documentNumber: new FormControl('', [
      Validators.required,
      Validators.pattern('^[0-9]{8,11}$'),
      
    ]),
  });

  selected = new FormControl('',[
    Validators.required,
  ])

  constructor(private clientService : ClientService, 
    private formBuilder: FormBuilder,
    private hostElement: ElementRef) {
    this.id = new IdentityDocument();
    /*this.idForm = this.formBuilder.group({
      DocumentType: [''],
      documentNumber: [null],
    });*/

  }
 

  get formControls() {
    return this.idForm.controls;
  }


  onSubmit() {
    if (this.idForm.invalid) {
      return;
    }

    this.clientService.search(this.id);
  }
 
    ngOnInit(): void{
      /*this.idForm = this.formBuilder.group({
        documentNumber: ["",Validators.required]
      });*/
    }

    matcher = new ErrorStateMatcher();

}
