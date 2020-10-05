import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { ENDPOINTS } from "../../core/constants/constants";

@Injectable({
  providedIn: 'root'
})

export class MaquinaService {

  private path = ENDPOINTS;

  constructor(protected http: HttpClient) {
  }

  getMaquina(id){
    return this.http.get(this.path.SERVER.serve + this.path.MAQUINAS.GETID + id);
  }

  getMaquinas() {
    return this.http.get(this.path.SERVER.serve + this.path.MAQUINAS.GET);
  }


  postMaquina(machinesForm){
    return this.http.post<any>(this.path.SERVER.serve + this.path.MAQUINAS.POST, machinesForm.value);
  }


  updateMaquina(id, maquina){
    return this.http.put<any>(this.path.SERVER.serve + this.path.MAQUINAS.PUT + id, maquina.value);
  }

  

}
