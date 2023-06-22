package com.example.examenbanco.services;

import com.example.examenbanco.domains.Cliente;
import com.example.examenbanco.domains.Mensaje;

import java.util.List;

public interface ClienteService {
    List<Cliente> listAllClientes();
    Cliente createClinete(Cliente cliente);
    Cliente findClienteById(long id);
    void updateClinete(Cliente cliente);
    void deleteClinete(long id);
    Mensaje depositar(long id, double cantidad);

    Mensaje retirar(long id,double cantidad);
}
