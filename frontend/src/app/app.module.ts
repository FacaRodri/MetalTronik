import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {MatListModule} from '@angular/material/list';
import {MatSidenavModule} from '@angular/material/sidenav';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CoreModule} from './core/core.module';
import {MainModule} from './main/main.module';
import {MaquinaModule} from './maquina/maquina.module';
import {MantenimientoCorrectivoModule} from './matenimiento-correctivo/mantenimiento-correctivo.module';
import {OrdenesTrabajoModule} from './ordenes-trabajo/ordenes-trabajo.module';
import {OrdenestrabajoService} from './ordenes-trabajo/services/ordenestrabajo.service';
import {UserService} from './usuarios/services/user.service';
import {UsuariosModule} from './usuarios/usuarios.module';
import {ConfiguracionModule} from "./configuracion/configuracion.module";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    CoreModule,
    OrdenesTrabajoModule,
    MainModule,
    MatSidenavModule,
    MatListModule,
    UsuariosModule,
    MaquinaModule,
    MantenimientoCorrectivoModule,
    ConfiguracionModule
  ],
  providers: [UserService,
    OrdenestrabajoService, ],
  bootstrap: [AppComponent]
})


export class AppModule { }
