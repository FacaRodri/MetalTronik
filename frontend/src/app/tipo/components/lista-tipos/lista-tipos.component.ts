import { Component, OnInit } from '@angular/core';
import { TipoService } from "../../services/tipo.service";
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-lista-tipos',
  templateUrl: './lista-tipos.component.html',
  styleUrls: ['./lista-tipos.component.scss']
})
export class ListaTiposComponent implements OnInit {

  dataSourceTipos: any;

  form: FormGroup;

  createFormGroup() {
    return new FormGroup({
      nombre: new FormControl(''),
      estado: new FormControl(30)
    })
  }

  constructor(private TipoService: TipoService) 
  {
    this.form = this.createFormGroup();
   }

  ngOnInit(): void {

    this.TipoService.getTipos().subscribe(
      (data: any) => {
        this.dataSourceTipos = data;
        console.log(this.dataSourceTipos)
      },
      (error) => {
        console.log(error);
      }
    );


  }

  addTipo() {
    console.log(this.form.value);
    this.TipoService.postTipo(this.form).subscribe(
      prioridad =>  location.reload()
      
    );
  }

}