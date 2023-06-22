package com.example.examenbanco.services;

import com.example.examenbanco.domains.Cliente;
import com.example.examenbanco.domains.Mensaje;
import com.example.examenbanco.repos.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    ClienteRepository repository;

    @Override
    public List<Cliente> listAllClientes() {
        return repository.findAll();
    }

    @Override
    public Cliente createClinete(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Cliente findClienteById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public void updateClinete(Cliente cliente) {

        repository.save(cliente);
    }

    @Override
    public void deleteClinete(long id) {
        repository.deleteById(id);
    }
    @Override
    public Mensaje depositar(long id,double cantidad){
        Cliente cliente = repository.findById(id).get();
        double saldoclinete=cliente.getSaldo();
        cliente.setSaldo(saldoclinete+cantidad);
        repository.save(cliente);
        return new Mensaje(id,"Se depesito ="+cantidad+" y ahora el saldo es "+cliente.getSaldo());
    }

    @Override
    public Mensaje retirar(long id, double cantidad) {
        Cliente cliente=repository.findById(id).get();
        double saldoActual=cliente.getSaldo();
        cliente.setSaldo(saldoActual-cantidad);
        repository.save(cliente);
        return new Mensaje(id,"Se retiro = "+cantidad+" de "+saldoActual);
    }


    @Override
    public Mensaje transferencia(long idCliente1, long idCliente2, double cantidad) {
        String msg1=repository.findById(idCliente1).get().getNombre();
        String msg2=repository.findById(idCliente2).get().getNombre();
        retirar(idCliente1,cantidad);
        depositar(idCliente2,cantidad);
        return new Mensaje(idCliente1,"El usuario "+msg1+" ha transferido "+cantidad+" a "+msg2);
    }


}
