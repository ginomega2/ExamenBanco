package com.example.examenbanco.controllers;

import com.example.examenbanco.domains.Cliente;
import com.example.examenbanco.domains.Mensaje;
import com.example.examenbanco.repos.ClienteRepository;
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
@RequestMapping("/api/cliente") //********* http://localhost:8063/api
public class ClienteRestController {
    @Autowired
    ClienteService service;

    @GetMapping("/all")         //********* http://localhost:8063/api/cliente/all
    public ResponseEntity<List<Cliente>> listarClientes(){
        List<Cliente> clienteList = service.listAllClientes();
        return new ResponseEntity<>(clienteList, HttpStatus.OK);
    }
    @GetMapping("/findCliente/{id}")        //********* http://localhost:8063/api/cliente/findCliente/
    public ResponseEntity findClienteById(@PathVariable long id){
        Cliente cliente=service.findClienteById(id);
        return new ResponseEntity(cliente,HttpStatus.ACCEPTED);
    }
    @PostMapping("/create")     //********* http://localhost:8063/api/cliente/create
    public ResponseEntity createCliente(@RequestBody Cliente cliente){
        service.createClinete(cliente);
      return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("/update")     //********* http://localhost:8063/api/cliente/update
    public ResponseEntity updateCliente(@RequestBody Cliente cliente){
        service.updateClinete(cliente);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete/{id}")      //********* http://localhost:8063/api/cliente/delete/
    public ResponseEntity delteCliente(@PathVariable long id){
        service.deleteClinete(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    @PostMapping("/{id}/depositar/{cantidad}")      //********* http://localhost:8063/api/cliente/{id}/depositar/{cantidad}
    public ResponseEntity depositaraCliente(@PathVariable long id,@PathVariable double cantidad){
        Mensaje mensaje= service.depositar(id,cantidad);
        return new ResponseEntity(mensaje,HttpStatus.OK);
    }
    @PostMapping("/{id}/retirar/{cantidad}")            //********* http://localhost:8063/api/cliente/{id}/retirar/{cantidad}
    public ResponseEntity retirarCliente(@PathVariable long id,@PathVariable double cantidad){
        if (cantidad>service.findClienteById(id).getSaldo()) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }else {
            Mensaje mensaje = service.retirar(id, cantidad);
            return new ResponseEntity(mensaje, HttpStatus.ACCEPTED);
        }
    }
    @PostMapping("/{idCliente1}/transferencia/{idCliente2}/{cantidad}")//********* http://localhost:8063/api/cliente/{idCliente1}/transferencia/{idCliente2}/{cantidad}
    public ResponseEntity transferirCliente(@PathVariable long idCliente1,@PathVariable long idCliente2,@PathVariable double cantidad){
        if (cantidad>service.findClienteById(idCliente1).getSaldo()) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }else {
            Mensaje mensaje=service.transferencia(idCliente1,idCliente2,cantidad);
            return new ResponseEntity(mensaje,HttpStatus.ACCEPTED);
        }
    }
}
