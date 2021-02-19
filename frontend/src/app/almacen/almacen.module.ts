import { NgModule } from '@angular/core';
import { CoreModule } from '../core/core.module';
import { CommonModule } from '@angular/common';
import { TablaExistenciaComponent } from './components/tabla-existencia/tabla-existencia.component';
import { AlmacenRoutingModule } from "./almacen-rounting.module";
import { MatTabsModule } from '@angular/material/tabs';
import { FormExistenciaComponent } from './components/form-existencia/form-existencia.component';
import { ReactiveFormsModule } from '@angular/forms';
import { TablaEntradaComponent } from './components/tabla-entrada/tabla-entrada.component';
import { TablaSalidaComponent } from '../almacen/components/tabla-salida/tabla-salida.component';
import { FormEntradaComponent } from './components/form-entrada/form-entrada.component';
import { FormSalidaComponent } from "./components/form-salida/form-salida.component";
import { TablaAjusteComponent } from "./components/tabla-ajuste/tabla-ajuste.component";
import { FormAjusteComponent } from './components/form-ajuste/form-ajuste.component';
import { NavComponent } from './components/nav/nav.component';


@NgModule({
  declarations: [TablaExistenciaComponent, FormExistenciaComponent, TablaEntradaComponent, TablaSalidaComponent, FormEntradaComponent, FormSalidaComponent,TablaAjusteComponent, FormAjusteComponent, NavComponent],
  imports: [
    CommonModule,
    AlmacenRoutingModule,
    MatTabsModule,
    CoreModule,
    ReactiveFormsModule
  ]
})
export class AlmacenModule { }
