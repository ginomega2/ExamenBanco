package com.example.examenbanco.controllers;

import com.example.examenbanco.domains.Cliente;
import com.example.examenbanco.domains.Mensaje;
import com.example.examenbanco.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


///****     ENDPOINT PARA DEPOSITAR Y RETIRAR   ***************************
//  http://localhost:8063/api/cliente/{id}/depositar/{cantidad}*   RETORNAR  FUE EXITOSO Y REGRESAR SALDO ACTUAL

// MENSAJE CLASE contener id: mensaje:

//  http://localhost:8063/api/cliente/{id}/retirar/{cantidad}*   RETORNAR  FUE EXITOSO Y REGRESAR SALDO ACTUAL

// MENSAJE CLASE contener id: mensaje:


@RestController
@RequestMapping("/api2/cliente") //********* http://localhost:8063/api
public class ClienteRestController2 {
    @Autowired
    ClienteService service;

    @GetMapping("/all")         //********* http://localhost:8063/api2/cliente/all
    public ResponseEntity<List<Cliente>> listarClientes(){
        List<Cliente> clienteList = service.listAllClientes();
        return new ResponseEntity<>(clienteList, HttpStatus.OK);
    }
    @GetMapping("/findCliente/{id}")        //********* http://localhost:8063/api2/cliente/findCliente/
    public ResponseEntity findClienteById(@PathVariable long id){
        Cliente cliente=service.findClienteById(id);
        return new ResponseEntity(cliente,HttpStatus.ACCEPTED);
    }
    @PostMapping("/create")     //********* http://localhost:8063/api2/cliente/create
    public ResponseEntity createCliente(@RequestBody Cliente cliente){
        service.createClinete(cliente);
      return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("/update")     //********* http://localhost:8063/api2/cliente/update
    public ResponseEntity updateCliente(@RequestBody Cliente cliente){
        service.updateClinete(cliente);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete/{id}")      //********* http://localhost:8063/api2/cliente/delete/
    public ResponseEntity delteCliente(@PathVariable long id){
        service.deleteClinete(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    @PostMapping("/{id}/depositar/{cantidad}")      //********* http://localhost:8063/api2/cliente/{id}/depositar/{cantidad}
    public ResponseEntity depositaraCliente(@PathVariable long id,@PathVariable double cantidad){
        Mensaje mensaje= service.depositar(id,cantidad);
        return new ResponseEntity(mensaje,HttpStatus.OK);
    }
    @PostMapping("/{id}/retirar/{cantidad}")            //********* http://localhost:8063/api2/cliente/{id}/retirar/{cantidad}
    public ResponseEntity retirarCliente(@PathVariable long id,@PathVariable double cantidad){
        ////****************   LA LOGICA DEL NEGOCIO DEBE IR EN LA CAPA DE SERVICIOS AQUI NO DEBE HABER NINGUN CALCULO QUE INVOLUCRE MODIFICACIONES
        // O LOGICA COMO LA DE CHECARLOS SALDOS CONTRA CANTIDADES ESO LO DEBE HACER EL SERVICIO Y EL CONTROLADOR SOLO DEBE INFORMAR DE LOS
        // RESULTADOS QUE OBTUVO DEL SERVICIO , EN ESTE CASO service.retirar ES LA QUE DEBE HACER LA BUSQUEDA , Y REALIZAR TODAS LAS OPERACIONES
        // PERTINENETES SOBRE LOS DATOS COMO VALIDACIONES , O LLAMADAS A FUNCIONES  TRATA DE CAMBIAR LA LOGICA DE   ransferirCliente AL SERVICIO
        Mensaje mensaje = service.retirar(id, cantidad);
        return (mensaje.getMensaje().equals("IMPOSIBLE")?new ResponseEntity(mensaje,HttpStatus.CONFLICT):new ResponseEntity(mensaje, HttpStatus.ACCEPTED));

    }
    @PostMapping("/{idCliente1}/transferencia/{idCliente2}/{cantidad}")//********* http://localhost:8063/api2/cliente/{idCliente1}/transferencia/{idCliente2}/{cantidad}
    public ResponseEntity transferirCliente(@PathVariable long idCliente1,@PathVariable long idCliente2,@PathVariable double cantidad){
        if (cantidad>service.findClienteById(idCliente1).getSaldo()) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }else {
            Mensaje mensaje=service.transferencia(idCliente1,idCliente2,cantidad);
            return new ResponseEntity(mensaje,HttpStatus.ACCEPTED);
        }
    }
}
