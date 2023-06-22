package com.example.examenbanco;

import com.example.examenbanco.domains.Cliente;
import com.example.examenbanco.services.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExamenBancoApplicationTests {
    @Autowired
    ClienteService service;

//    @Test
//    void create() {
//        Cliente cliente=new Cliente(12,"paco",5000);
//        service.createClinete(cliente);
//        cliente=new Cliente(12,"paco21121",5000);
//        service.createClinete(cliente);
//        cliente=new Cliente(12,"paco25432141121",5000);
//        service.createClinete(cliente);
//        cliente=new Cliente(12,"paco23213121",5000);
//        service.createClinete(cliente);
//        cliente=new Cliente(12,"paco321321121",5000);
//        service.createClinete(cliente);
//
//    }
//    @Test
//    void read(){
//        Cliente cliente= service.findClienteById(3);
//        System.out.println(cliente);
//    }
//    @Test
//    void update(){
//        Cliente cliente1= service.findClienteById(4);
//        System.out.println(cliente1);
//        Cliente cliente=new Cliente(4,"paco",300000);
//        service.updateClinete(cliente);
//    }
//
//    @Test
//    void delete(){
//        Cliente cliente1= service.findClienteById(4);
//        System.out.println(cliente1);
//        service.deleteClinete(4);
//    }

}
